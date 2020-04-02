package com.example.tralova

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.view.isVisible
import com.example.tralova.ui.gallery.TouristData
import kotlinx.android.synthetic.main.activity_profil_.*
import kotlinx.android.synthetic.main.activity_profil_.view.*

class Profil_Activity : AppCompatActivity() {
    private lateinit var dataprofil: ArrayList<TouristData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_)

        isi_name.setOnClickListener{
            editnama.setVisibility(View.VISIBLE)
            isi_name.setVisibility(View.INVISIBLE)
        }
        isi_tele.setOnClickListener {
            edittele.setVisibility(View.VISIBLE)
            isi_tele.setVisibility(View.INVISIBLE)
        }
        isi_penyakit.setOnClickListener {
            editpenyakit.setVisibility(View.VISIBLE)
            isi_penyakit.setVisibility(View.INVISIBLE)
        }
        isi_Obat.setOnClickListener {
            editobat.setVisibility(View.VISIBLE)
            isi_Obat.setVisibility(View.INVISIBLE)
        }

    }

    fun SaveData(view: View){
        isi_name.setText(editnama.getText().toString())
        editnama.setVisibility(View.INVISIBLE)
        isi_name.setVisibility(View.VISIBLE)

        isi_tele.setText(edittele.getText().toString())
        edittele.setVisibility(View.INVISIBLE)
        isi_tele.setVisibility(View.VISIBLE)

        isi_penyakit.setText(editpenyakit.getText().toString())
        editpenyakit.setVisibility(View.INVISIBLE)
        isi_penyakit.setVisibility(View.VISIBLE)

        isi_Obat.setText(editobat.getText().toString())
        editobat.setVisibility(View.INVISIBLE)
        isi_Obat.setVisibility(View.VISIBLE)

    }

}
