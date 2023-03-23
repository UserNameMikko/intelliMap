package com.mikko.intellimap.viewmodels


import dev.icerock.moko.mvvm.livedata.*
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.cancel

class NotificationsViewModel : ViewModel() {



    init {

    }
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}