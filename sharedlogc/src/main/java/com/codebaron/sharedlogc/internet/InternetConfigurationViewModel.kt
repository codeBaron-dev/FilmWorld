package com.codebaron.sharedlogc.internet

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codebaron.sharedlogc.internet.model.InternetResponseObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InternetConfigurationViewModel @Inject constructor(private val internetConfiguration: InternetConfiguration) :
    ViewModel() {

        companion object {
            var internetStates: MutableLiveData<InternetStates<InternetResponseObject>>? = null
        }

    fun networkStates(context: Context): MutableLiveData<InternetStates<InternetResponseObject>>? {
        viewModelScope.launch {
           internetStates = internetConfiguration.internetConfiguration(context)
        }
        return internetStates
    }
}