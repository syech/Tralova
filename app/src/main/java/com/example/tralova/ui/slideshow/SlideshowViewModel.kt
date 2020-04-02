package com.example.tralova.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tralova.ui.share.Group

class SlideshowViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is slideshow Fragment"
    }
    val text: LiveData<String> = _text

    private var listRemind: MutableLiveData<ArrayList<Reminder>>? = null
    fun getAllDataRemind(remind: ArrayList<Reminder>): LiveData<ArrayList<Reminder>>? {
        if (listRemind == null) {
            getDataGroup(remind)
        }
        return listRemind
    }

    private fun getDataGroup(remind: ArrayList<Reminder>): LiveData<ArrayList<Reminder>> {
        listRemind = MutableLiveData()
        listRemind?.postValue(remind)
        return listRemind as LiveData<ArrayList<Reminder>>
    }
}