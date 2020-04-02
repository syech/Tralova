package com.example.tralova.ui.recomended

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.tralova.MainActivity
import com.example.tralova.R
import com.example.tralova.ui.gallery.DetailHealthCareActivity
import com.example.tralova.ui.gallery.HealthCare
import kotlinx.android.synthetic.main.activity_detail_healthcare.*
import kotlinx.android.synthetic.main.activity_detail_recomended.*

class DetailWisata : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_recomended)

        //mengambil data secara Parcelable
        val dataWisata = intent.getParcelableExtra<TempatWisata>(DetailWisata.DATA_TEMPAT_WISATA)

        //menampilkan data sesuai dataHealthCare
        dataWisata?.let {
            tv_namaWisata.text = it.nameWisata
            tv_deskwisata.text = it.DeskWisata
            tv_kondisiwisata.text = it.KondisiWisata
            Glide.with(this).load(it.photoWisata).into(img_wisata)
        }
    }

    fun showArrowW(view: View){
        val toActivity = Intent(this@DetailWisata, MainActivity::class.java)
        startActivity(toActivity)
    }

    companion object {
        const val DATA_TEMPAT_WISATA = "DATA_TEMPAT_WISATA"
    }
}
