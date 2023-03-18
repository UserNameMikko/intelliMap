/*package com.mikko.mapit.qr

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.mikko.intellimap.R
import com.mikko.intellimap.databinding.BottomSheetBarcodeDataBinding
import org.jsoup.Jsoup
import java.util.concurrent.Executors


class BarcodeResultBottomSheet : BottomSheetDialogFragment() {
    lateinit var binding : BottomSheetBarcodeDataBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottom_sheet_barcode_data, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = BottomSheetBarcodeDataBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    fun updateURL(url: String) {
        fetchUrlMetaData(url) { title, desc ->

            view?.apply {
                binding.textViewTitle.text = title
                binding.textViewDesc.text = desc
                binding.textViewLink.text = url
                binding.textViewVisitLink.setOnClickListener { _ ->
                    Intent(Intent.ACTION_VIEW).also {
                        it.data = Uri.parse(url)
                        startActivity(it)
                    }
                }
                /*findViewById<TextView>(R.id.text_view_title)?.text = title
                findViewById<TextView>(R.id.text_view_desc)?.text = desc
                findViewById<TextView>(R.id.text_view_link)?.text = url
                findViewById<TextView>(R.id.text_view_visit_link).setOnClickListener { _ ->
                    Intent(Intent.ACTION_VIEW).also {
                        it.data = Uri.parse(url)
                        startActivity(it)
                    }
                }*/
            }
        }
    }

    private fun fetchUrlMetaData(
        url: String,
        callback: (title: String, desc: String) -> Unit
    ) {
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        executor.execute {

            val doc = Jsoup.connect(url).get()
            val desc = if (doc.select("meta[name=description]").isNotEmpty())
                 doc.select("meta[name=description]")[0].attr("content")
            else "this code has no description"
            handler.post {
                callback(doc.title(), desc)
            }
        }
    }

}
*/