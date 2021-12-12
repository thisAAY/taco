package com.candybytes.taco.ui.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
     private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val showBottomBar = MutableLiveData(true)
}
