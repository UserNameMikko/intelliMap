package com.mikko.intellimap.ui.camera

import android.Manifest
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
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
import me.dm7.barcodescanner.zxing.ZXingScannerView.ResultHandler

class CameraFragment : Fragment(R.layout.fragment_camera), ResultHandler {

    private lateinit var binding: FragmentCameraBinding
    /*private val binding : FragmentCameraBinding by lazy {
        FragmentCameraBinding.bind(requireView())
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCameraBinding.bind(view)
        startCamera()
    }
    override fun handleResult(p0: Result?) {
        Toast.makeText(binding.root.context, p0?.text?:"no data", Toast.LENGTH_SHORT).show()
        if (p0 != null) {
            val substrings = p0.text.split("/")
            val idolName = if (substrings.size > 3) substrings[4] else ""
            findNavController().navigate(
                CameraFragmentDirections.actionNavigationCameraToNavigationIdol(idolName)
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

                }

            }).check()
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

