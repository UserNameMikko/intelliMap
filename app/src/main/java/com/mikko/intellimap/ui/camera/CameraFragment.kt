package com.mikko.intellimap.ui.camera

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.zxing.Result
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.mikko.intellimap.R
import com.mikko.intellimap.databinding.FragmentCameraBinding
import com.mikko.intellimap.viewmodels.CameraViewModel
import dagger.hilt.android.AndroidEntryPoint
import me.dm7.barcodescanner.zxing.ZXingScannerView.ResultHandler

@AndroidEntryPoint
class CameraFragment : Fragment(R.layout.fragment_camera), ResultHandler {

    private lateinit var binding: FragmentCameraBinding
    private val cameraViewModel : CameraViewModel by viewModels()
    /*private val binding : FragmentCameraBinding by lazy {
        FragmentCameraBinding.bind(requireView())
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCameraBinding.bind(view)
        startCamera()
    }
    override fun handleResult(result: Result?) {

        Toast.makeText(binding.root.context, result?.text?:"no data", Toast.LENGTH_SHORT).show()
        if (result != null) {
            var idolUrlWithoutLastSlash = ""
            if (result.text.endsWith("/"))
                idolUrlWithoutLastSlash = result.text.dropLast(1)
            println("RESULT ${result.text.endsWith("/")}\n${idolUrlWithoutLastSlash}")

            Toast.makeText(binding.root.context, result.text?:"no data", Toast.LENGTH_SHORT).show()
            /*val substrings = result.text.split("/")
            val idolName = if (substrings.size > 4) substrings[4] else ""
            findNavController().navigate(
                CameraFragmentDirections.actionNavigationCameraToNavigationIdol(idolName)
            )*/
            val pattern = "([^\\/]+\$)".toRegex()
            //Log.e("TAG", "${idolUrlWithoutLastSlash.matches(pattern)}")
            val substring = pattern.findAll(idolUrlWithoutLastSlash)
            //Log.e("STRING", "${substring.count()}")

            val idol = if (substring.count() == 0) "" else substring.first().value
             findNavController().navigate(
                    CameraFragmentDirections.actionNavigationCameraToNavigationIdol(idol)
               )


        }
    }
    private fun startCamera() {

        Dexter.withActivity(requireActivity())
            .withPermission(Manifest.permission.CAMERA)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    binding.scanner.setResultHandler(this@CameraFragment)
                    binding.scanner.startCamera()
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    Toast.makeText(
                        binding.root.context, "Permission must be enabled!", Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }

            }).withErrorListener {
                Toast.makeText(requireContext(), "Error occurred!\n${it.name}", Toast.LENGTH_SHORT).show()
            }.onSameThread().check()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //binding.scanner.stopCamera()
    }
    override fun onResume() {
        super.onResume()
        //startCamera()
    }

}

