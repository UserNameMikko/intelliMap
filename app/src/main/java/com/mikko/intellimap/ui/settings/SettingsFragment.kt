package com.mikko.intellimap.ui.settings

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.mikko.intellimap.R
import com.mikko.intellimap.databinding.FragmentSettingsBinding
import com.mikko.intellimap.viewmodels.IdolsViewModel
import com.mikko.intellimap.viewmodels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.icerock.moko.mvvm.livedata.addCloseableObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.lang.NullPointerException

@AndroidEntryPoint
class SettingsFragment: Fragment(R.layout.fragment_settings) {

    private val idolsViewModel : IdolsViewModel by activityViewModels()//viewModels()
    private val settingsViewModel: SettingsViewModel by activityViewModels()
    private lateinit var binding : FragmentSettingsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)
        try {
            settingsViewModel.liveData.addCloseableObserver {
                var imagesOfIdol = mutableListOf<String>()
                Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
                it.forEach {
                    /** downloading images into external storage */
                    val imagesDirPath = ///storage/emulated/0/intelliMap/idols/images
                        Environment.getExternalStorageDirectory().absolutePath +
                                "/idols/images/${it.slug}" //+ "/idols/images"

                    Log.e("images dir path", imagesDirPath)
                    val audioDirPath = /////storage/emulated/0/intelliMap/idols/audio
                        Environment.getExternalStorageDirectory().absolutePath +
                                "/" + /*getString(R.string.app_name) +*/ "Downloads"// "/idols/audio"
                    Log.e("audio dir path", audioDirPath)

                    val fileImages = File(imagesDirPath)
                    if (!fileImages.exists()) {
                        fileImages.mkdirs()
                    }
                    val list = fileImages.listFiles()
                    if (!list.isNullOrEmpty())
                        for (i in list)
                            Log.e("image", "${i.absolutePath}\t${it.slug}")


                    //val images = settingsViewModel.getImagesFromRemote(it.name)
                }
            }
        } catch (n: NullPointerException){
            Log.e("NULL", "${n.message}")
        }

        Toast.makeText(requireContext(), " sfasfa", Toast.LENGTH_SHORT).show()
        binding.getData.setOnClickListener {
            settingsViewModel.getImagesFromRemote("perun")
            settingsViewModel.getImagesFromRemote("svarog")

        }

    }

}