package com.mikko.intellimap

import android.Manifest
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.mikko.intellimap.databinding.ActivityMainBinding
import com.mikko.intellimap.viewmodels.IdolsViewModel
import dagger.hilt.android.AndroidEntryPoint

import dev.icerock.moko.mvvm.livedata.addCloseableObserver
import java.io.File

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var navHostController: NavController
    private val idolsViewModel :IdolsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        Dexter.withActivity(this)
            .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : MultiplePermissionsListener{
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }

            }).withErrorListener {
                //Toast.makeText(requireContext(), "Error occurred!\n${it.name}", Toast.LENGTH_SHORT).show()
            }.onSameThread().check()
        val navView: BottomNavigationView = binding.navView
        navHostController =
            supportFragmentManager.findFragmentById(R.id.container)?.findNavController()!!
        setupActionBarWithNavController(
            navHostController,
            AppBarConfiguration(navHostController.graph)
        )
        binding.navView.setupWithNavController(navHostController)
        //val navController = findNavController(R.id.container)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        navHostController.addOnDestinationChangedListener { _, destination, _ ->
            val homeDisplays = listOf(
                R.id.navigation_home, R.id.navigation_camera, R.id.navigation_notifications
            )
            supportActionBar?.setDisplayHomeAsUpEnabled(
                destination.id !in homeDisplays
            )
        }
        idolsViewModel.liveData.addCloseableObserver {
            Log.e("FROM SERV", "$it")
            Toast.makeText(binding.root.context, "$it", Toast.LENGTH_LONG).show()
        }


        /*val needToHideBNV = listOf(R.id.navigation_camera)
            navController.addOnDestinationChangedListener{_, destination, _ ->
                //navView.visibility = if (destination.id in needToHideBNV) View.GONE
                //else View.VISIBLE
         }*/
        //setupActionBarWithNavController(navController, appBarConfiguration)
        //navView.setupWithNavController(navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        return navHostController.navigateUp() || super.onSupportNavigateUp()
    }
    private fun galleryAddPic(imagePath: String?) {
        imagePath?.let { path ->
            val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
            val f = File(path)
            val contentUri: Uri = Uri.fromFile(f)
            mediaScanIntent.data = contentUri
            sendBroadcast(mediaScanIntent)
        }
    }


}