package com.example.tralova.ui.gallery

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tralova.R
import kotlinx.android.synthetic.main.fragment_gallery.view.*

class GalleryFragment : Fragment() {
    //    private lateinit var galleryViewModel: GalleryViewModel
//    private lateinit var rvCare : RecyclerView
    private var list: HealtyCareAdapter? = null
    private lateinit var optionGroupSpinner: Spinner
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        galleryViewModel =
//            ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)

        //Ini langsung dimasukkan ke list dari data wisatawannya didalam adapter,
        list = HealtyCareAdapter(TouristData.listDataWisatawan)
        //penggunaan id di xml, kalo bisa lansung camelcase aja, seperti ini rvHealthCare atau btnSend, btnSave, etMemberName dll
        view.rvHealthCare.layoutManager = LinearLayoutManager(view.context)
        view.rvHealthCare.adapter = list

        var listSpinner = listOf("Pilih", "Umroh 2018", "Reuni ke Sabang", "Touring Sinabung")
        optionGroupSpinner = view.optionsGroup
        val spinnerArrayAdapter = object : ArrayAdapter<String>(
            view.context,
            android.R.layout.simple_spinner_item,
            listSpinner
        ) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val viewx = super.getDropDownView(position, convertView, parent)
                val tv = viewx as TextView
                when (position) {
                    0 -> tv.setTextColor(Color.GRAY)
                    else -> tv.setTextColor(Color.BLACK)
                }
                return viewx
            }
        }

        optionGroupSpinner.adapter = spinnerArrayAdapter
        optionGroupSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (optionGroupSpinner.selectedItemPosition > 0) {
                    Toast.makeText(
                        requireContext(),
                        "milih ${listSpinner[position]}",
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }
        }

        return view

//        rvCare.setHasFixedSize(true)

//        list.addAll(TouristData.listDataWisatawan)
//        showRecycleList()
//        val textView: TextView = root.findViewById(R.id.text_gallery)
//        galleryViewModel.text.observe(this, Observer {
//            textView.text = it
//        })

//        return root.rv_healtycare

    }


//    private fun showRecycleList() {
//        rvCare.layoutManager = LinearLayoutManager(this)
//        val list_Care = HealtyCareAdapter(list)
//        rvCare.adapter = list_Care
//    }


}