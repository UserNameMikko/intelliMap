package com.mikko.intellimap.ui.idols

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mikko.intellimap.R
import com.mikko.intellimap.databinding.FragmentIdolsListBinding

class IdolsListFragment : Fragment(R.layout.fragment_idols_list) {
    private val binding : FragmentIdolsListBinding by lazy {
        FragmentIdolsListBinding.bind(requireView())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}