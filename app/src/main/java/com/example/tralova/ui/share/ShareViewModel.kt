package com.example.tralova.ui.share

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShareViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Group Fragment"
    }
    val text: LiveData<String> = _text
    private var listGroup: MutableLiveData<ArrayList<Group>>? = null
    fun getAllDataGroup(group: ArrayList<Group>): LiveData<ArrayList<Group>>? {
        if (listGroup == null) {
            getDataGroup(group)
        }
        return listGroup
    }

    private fun getDataGroup(group: ArrayList<Group>): LiveData<ArrayList<Group>> {
        listGroup = MutableLiveData()
        listGroup?.postValue(group)
        return listGroup as LiveData<ArrayList<Group>>
    }
}