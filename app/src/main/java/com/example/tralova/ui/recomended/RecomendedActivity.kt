package com.example.tralova.ui.recomended

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tralova.MainActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.R



class RecomendedActivity : AppCompatActivity() {
    private lateinit var rvWisata: RecyclerView
    private var listWisata: ArrayList<TempatWisata> = arrayListOf()
    private lateinit var rvKuliner: RecyclerView
    private var listKuliner: ArrayList<TempatKuliner> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.tralova.R.layout.activity_recomended)

        rvWisata = findViewById(com.example.tralova.R.id.rv_wisata)
        rvWisata.setHasFixedSize(true)

        listWisata.addAll(Data_TempatWisata.listData)
        showRecycleListwisata()

        rvKuliner = findViewById(com.example.tralova.R.id.rv_kuliner)
        rvKuliner.setHasFixedSize(true)

        listKuliner.addAll(Data_TempatKuliner.listData)
        showRecycleListkuliner()
    }

    private fun showRecycleListwisata() {
        val horizontalLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvWisata.setLayoutManager(horizontalLayoutManager)
        val List_wisata = WisataAdapter(listWisata)
        rvWisata.adapter = List_wisata
    }

    private fun showRecycleListkuliner() {
        val horizontalLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvKuliner.setLayoutManager(horizontalLayoutManager)
        val List_kuliner = KulinerAdapter(listKuliner)
        rvKuliner.adapter = List_kuliner
    }

}
