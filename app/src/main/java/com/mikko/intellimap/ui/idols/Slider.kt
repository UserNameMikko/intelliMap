package com.mikko.intellimap.ui.idols

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mikko.intellimap.R
import com.smarteist.autoimageslider.SliderViewAdapter
import com.mikko.intellimap.databinding.ItemIdolPhotoBinding


class Slider(
    var images :MutableList<String> = mutableListOf(),
    val showImage: (List<Int>) -> Unit
) :
    SliderViewAdapter<Slider.SliderViewHolder>() {

    override fun getCount(): Int {
        return images.size
    }

    override fun onCreateViewHolder(p0: ViewGroup): SliderViewHolder {
        return SliderViewHolder(
            LayoutInflater.from(p0.context).inflate(R.layout.item_idol_photo, p0, false)
        )
    }

    override fun onBindViewHolder(viewHolder: SliderViewHolder, position: Int) {
        viewHolder.bind(images[position])
    }

    inner class SliderViewHolder(view: View) : ViewHolder(view) {
        private val binding = ItemIdolPhotoBinding.bind(view)

        fun bind(imageName: String) {
            Glide.with(binding.root.context)
                .load(imageName)
                .error(R.drawable.err)
                .into(binding.imageGalleryPhoto)
            //binding.imageGalleryPhoto.setImageResource(imageName)
            /*binding.root.setOnClickListener {
                showImage(images)
            }*/
            binding.imageGalleryPhoto.setOnClickListener {
                //val list = List(images.size) { imageUri }
                //showImage(images)
            }
        }
    }
}