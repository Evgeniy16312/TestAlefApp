package com.example.testalefapp.presentation.gallery.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testalefapp.data.LoadStatus
import com.example.testalefapp.data.NetworkService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GalleryViewModel : ViewModel() {
    val liveStatus = MutableLiveData<LoadStatus>()
    val livePictures = MutableLiveData<List<String>>()
    private val apiService = NetworkService.instance.getData()

    fun loadPictures() {
        viewModelScope.launch {
            liveStatus.postValue(LoadStatus.LOADING)
            delay(1000)
            try {
                val pictures = apiService.getPictures()

                liveStatus.postValue(LoadStatus.SUCCESS)
                livePictures.postValue(pictures)
            } catch (_: Exception) {
                liveStatus.postValue(LoadStatus.ERROR)
            }
        }
    }
}