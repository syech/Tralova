package com.example.tralova.ui.tools

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.graphics.drawable.toIcon
import androidx.core.net.toFile
import com.bumptech.glide.Glide
import com.example.tralova.MainActivity
import com.example.tralova.R
import com.example.tralova.ui.gallery.DetailHealthCareActivity
import com.example.tralova.ui.gallery.HealthCare
import kotlinx.android.synthetic.main.activity_detail__article.*
import java.io.File

class DetailArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail__article)

        //mengambil data secara Parcelable
        val dataArticle = intent.getParcelableExtra<Article>(DATA_ARTICLE)
        //menampilkan data sesuai dataArticle
        dataArticle?.let {
            tv_JudulArt.text = it.Judul
            tv_SumberArt.text = it.Sumber
            tv_IsiArt.text = it.DeskArt
//            Glide.with(this).load(it.Img_Art.toString()).into(photoArticle)
        }

    }

    companion object {
        const val DATA_ARTICLE = "DATA_ARTICLE"
    }
}
