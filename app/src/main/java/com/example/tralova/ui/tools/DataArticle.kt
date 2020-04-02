package com.example.tralova.ui.tools

import android.net.Uri
import com.example.tralova.ui.share.DataGroup
import com.example.tralova.ui.share.Group

object DataArticle {
    var ArtJudul: ArrayList<String> = arrayListOf()
    var ArtImage: ArrayList<String> = arrayListOf()
    var ArtSumber: ArrayList<String> = arrayListOf()
    var ArtDesk: ArrayList<String> = arrayListOf()

    var listDataArt: ArrayList<Article>
        get() {
            val list = arrayListOf<Article>()
            for (i in DataArticle.ArtJudul.indices) {
                val Contact = Article()
                Contact.Judul = DataArticle.ArtJudul[i]
                Contact.Sumber = DataArticle.ArtSumber[i]
                Contact.DeskArt = DataArticle.ArtDesk[i]
                Contact.Img_Art = Uri.parse(DataArticle.ArtImage[i])
                list.add(Contact)
            }
            return list
        }
        set(data) {
            for (i in data) {
                DataArticle.ArtJudul.add(i.Judul.toString())
                DataArticle.ArtSumber.add(i.Sumber.toString())
                DataArticle.ArtDesk.add(i.DeskArt.toString())
                DataArticle.ArtImage.add(i.Img_Art.toString())
            }
        }

}