package com.example.tralova.ui.gallery

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_healthcare.*

class DetailHealthCareActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.tralova.R.layout.activity_detail_healthcare)

        //mengambil data secara Parcelable
        val dataHealthCare = intent.getParcelableExtra<HealthCare>(DATA_HEALTH_CARE)

        //menampilkan data sesuai dataHealthCare
        dataHealthCare?.let {
            tvName.text = it.name
            tvDisease.text = it.Detail
            tvMedicine.text = it.Obat
            tvMedicineFunction.text = it.Fungsi
            Glide.with(this).load(it.pp).into(imgProfile)
        }
    }

    companion object {
        const val DATA_HEALTH_CARE = "DATA_HEALTH_CARE"
    }
}
