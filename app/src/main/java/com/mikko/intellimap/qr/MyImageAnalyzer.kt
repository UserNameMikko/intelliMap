/*package com.mikko.mapit.qr

import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.fragment.app.FragmentManager
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

@ExperimentalGetImage class MyImageAnalyzer(
    private val fragmentManager: FragmentManager
) : ImageAnalysis.Analyzer {

    private var bottomSheet = BarcodeResultBottomSheet()

    override fun analyze(imageProxy: ImageProxy) {
        scanBarcode(imageProxy)
    }

    private fun scanBarcode(imageProxy: ImageProxy) {
        imageProxy.image?.let { image ->
            val inputImage = InputImage.fromMediaImage(image, imageProxy.imageInfo.rotationDegrees)
            val scanner = BarcodeScanning.getClient()
            scanner.process(inputImage)
                .addOnCompleteListener {
                    imageProxy.close()
                    if (it.isSuccessful) {
                        readBarcodeData(it.result as List<Barcode>)
                    } else {
                        it.exception?.printStackTrace()
                    }
                }
        }
    }

    private fun readBarcodeData(barcodes: List<Barcode>) {
        for (barcode in barcodes) {
            when (barcode.valueType) {
                Barcode.TYPE_URL -> {
                    if (!bottomSheet.isAdded)
                        bottomSheet.show(fragmentManager, "")
                    bottomSheet.updateURL(barcode.url?.url.toString())
                }
            }
        }
    }
}*/