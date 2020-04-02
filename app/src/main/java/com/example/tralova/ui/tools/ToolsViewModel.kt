package com.example.tralova.ui.tools

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tralova.ui.share.Group

class ToolsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is tools Fragment"
    }
    val text: LiveData<String> = _text
    private var listArticle: MutableLiveData<ArrayList<Article>>? = null
    fun getAllDataArticle(article: ArrayList<Article>): LiveData<ArrayList<Article>>? {
        if (listArticle == null) {
            getDataArticle(article)
        }
        return listArticle
    }
    private fun getDataArticle(article: ArrayList<Article>): LiveData<ArrayList<Article>> {
        listArticle = MutableLiveData()
        listArticle?.postValue(article)
        return listArticle as LiveData<ArrayList<Article>>
    }
}