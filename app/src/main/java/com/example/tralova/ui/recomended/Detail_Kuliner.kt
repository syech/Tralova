package com.example.tralova.ui.recomended

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.tralova.MainActivity
import com.example.tralova.R
import kotlinx.android.synthetic.main.activity_detail__kuliner.*
import kotlinx.android.synthetic.main.activity_detail_recomended.*

class Detail_Kuliner : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail__kuliner)

        //mengambil data secara Parcelable
        val dataKuliner = intent.getParcelableExtra<TempatKuliner>(Detail_Kuliner.DATA_TEMPAT_KULINER)

        //menampilkan data sesuai dataKuliner
        dataKuliner?.let {
            tv_namaKuliner.text = it.nameKuliner
            tv_deskkuliner.text = it.DeskKuliner
            Glide.with(this).load(it.photoKuliner).into(img_kuliner)
        }
    }

    fun showArrowK(view: View){
        val toActivity = Intent(this@Detail_Kuliner, MainActivity::class.java)
        startActivity(toActivity)
    }

    companion object {
        const val DATA_TEMPAT_KULINER = "DATA_TEMPAT_KULINER"
    }
}
