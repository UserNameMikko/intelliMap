package com.mikko.intellimap.viewmodels

import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(): ViewModel() {
    init {

    }
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}