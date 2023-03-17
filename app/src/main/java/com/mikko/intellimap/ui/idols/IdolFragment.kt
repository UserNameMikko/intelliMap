package com.mikko.intellimap.ui.idols

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mikko.intellimap.R
import com.mikko.intellimap.databinding.FragmentIdolBinding

class IdolFragment : Fragment(R.layout.fragment_idol) {

    private val binding : FragmentIdolBinding by lazy { FragmentIdolBinding.bind(requireView())}
    private val args : IdolFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val idol = args.idolName
        super.onViewCreated(view, savedInstanceState)
        binding.tv.text = idol
        setHasOptionsMenu(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        activity?.onBackPressed()
        return super.onOptionsItemSelected(item)
    }


}