package com.mikko.intellimap.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mikko.intellimap.R
import com.mikko.intellimap.databinding.FragmentNotificationsBinding
import com.mikko.intellimap.ui.camera.CameraFragmentDirections
import com.mikko.intellimap.viewmodels.NotificationsViewModel
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

    private val notificationsViewModel : NotificationsViewModel by viewModels()
    private lateinit var binding: FragmentNotificationsBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotificationsBinding.bind(view)
        binding.goToSettings.setOnClickListener {
            Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
            findNavController().navigate(
                NotificationsFragmentDirections.actionNavigationNotificationsToSettingsFragment()
            )
        }
    }
}