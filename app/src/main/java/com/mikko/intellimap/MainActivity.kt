package com.mikko.intellimap

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mikko.intellimap.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var navHostController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
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


}