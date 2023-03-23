package com.mikko.intellimap.viewmodels

import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
//import dev.icerock.moko.mvvm.viewmodel.ViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor():ViewModel(){

    init {

    }
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}