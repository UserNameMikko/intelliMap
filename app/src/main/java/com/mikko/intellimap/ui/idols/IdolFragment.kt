package com.mikko.intellimap.ui.idols

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mikko.intellimap.R
import com.mikko.intellimap.databinding.FragmentIdolBinding
import com.mikko.intellimap.viewmodels.IdolsViewModel
import com.mikko.intellimap.viewmodels.SettingsViewModel
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import dagger.hilt.android.AndroidEntryPoint
import dev.icerock.moko.mvvm.livedata.Closeable
import java.io.File

@AndroidEntryPoint
class IdolFragment : Fragment(R.layout.fragment_idol) {

    private val binding : FragmentIdolBinding by lazy { FragmentIdolBinding.bind(requireView()) }
    private val args : IdolFragmentArgs by navArgs()
    private val idolViewModel : IdolsViewModel by activityViewModels()// lifecycle in activity context
    private val settingsViewModel: SettingsViewModel by activityViewModels()
    private val sliderAdapter = Slider { openViewer(it) }
    private lateinit var observerPhotos: Closeable
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val idol = args.idolName
        (activity as AppCompatActivity).supportActionBar?.title = idol
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        //loadData(idol)
        /** downloading images into external storage */
        val imagesDirPath = ///storage/emulated/0/intelliMap/idols/images
            Environment.getExternalStorageDirectory().absolutePath +
                    "/idols/images/$idol" //+ "/idols/images"
        Log.e("images dir path", imagesDirPath)
        val audioDirPath = /////storage/emulated/0/intelliMap/idols/audio
            Environment.getExternalStorageDirectory().absolutePath +
                    "/" + /*getString(R.string.app_name) +*/ "Downloads"// "/idols/audio"
        Log.e("audio dir path", audioDirPath)

        val fileImages = File(imagesDirPath)
        if (!fileImages.exists()) {
            fileImages.mkdirs()
        }
        val list = File(imagesDirPath).listFiles()
        if (!list.isNullOrEmpty())
            for (i in list)
                Log.e("image","${i.absolutePath}\t${i.name}")
        val images =  settingsViewModel.getImagesFromRemote(idol)
        if(!sliderAdapter.images.contains(idol)) {
            binding.imageSlider.setSliderAdapter(Slider(
                /*if (!imagesDirPath.isNullOrEmpty()) {
                    imagesDirPath.
                } else mutableListOf()*/
                //images.toMutableList()
                list.map { it.absolutePath }.toMutableList()
            ){})
            binding.imageSlider.setIndicatorEnabled(true)
            binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM)
            binding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            binding.imageSlider.startAutoCycle()
            binding.progressBar.visibility = View.GONE
        }


        /*Glide.with(binding.root.context)
            .load("${imagesDirPath}/perun_1.jpg")
            //.placeholder(R.drawable.mvvm)
            .error(R.drawable.404)
            //.diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.imageSlider)
*/
    }

   /* private fun loadData(idol: String) {
        observerPhotos = idolViewModel.photosFlow.addCloseableObserver {
            Log.e("IDOL", "${it[idol]}\n${it[idol].isNullOrEmpty()}")
            MainScope().launch {
                if (it[idol].isNullOrEmpty()) {
                    binding.cardImageSlider.visibility = View.GONE
                    binding.imageSlider.visibility = View.GONE
                }
                else {
                    binding.cardImageSlider.visibility = View.VISIBLE
                    binding.imageSlider.visibility = View.VISIBLE
                }
                if (sliderAdapter.images != it[idol]) {
                    binding.imageSlider.setSliderAdapter(
                        Slider(
                            it[idol]?.toMutableList()?: mutableListOf()
                        )
                        { list -> openViewer(list) }
                    )
                    binding.imageSlider.setIndicatorEnabled(true)
                    binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM)
                    binding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
                    binding.imageSlider.startAutoCycle()
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

    }*/
    private fun openViewer(list: List<Int>) {
        /*if (list.isNotEmpty()) {
            binding.imageSlider.stopAutoCycle()
            StfalconImageViewer.Builder(context, list) { view, image ->
                //Glide.with(requireContext()).load(image).into(view)
            }
                .withStartPosition(binding.imageSlider.currentPagePosition)
                .withImageChangeListener { position ->
                    binding.imageSlider.sliderPager.currentItem = position
                }.withDismissListener {
                    binding.imageSlider.startAutoCycle()
                }
                .show()
        }*/
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        activity?.onBackPressed()
        return super.onOptionsItemSelected(item)
    }


}