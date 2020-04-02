package com.example.tralova.ui.recomended

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tralova.R
import com.example.tralova.ui.gallery.HealthCare
import com.example.tralova.ui.gallery.HealtyCareAdapter
import com.example.tralova.ui.recomended.DetailWisata.Companion.DATA_TEMPAT_WISATA
import kotlinx.android.synthetic.main.item_wisata.view.*

class WisataAdapter(private val list_Wisata: ArrayList<TempatWisata>) : RecyclerView.Adapter<WisataAdapter.LisViewHolder>() {
    private lateinit var onItemClick: OnItemClick

    fun setonItemClick(onItemClick: OnItemClick) {
        this.onItemClick = onItemClick
    }

    interface OnItemClick {
        fun onItemClicked(data: TempatWisata)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): LisViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_wisata, viewGroup, false)
        return WisataAdapter.LisViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list_Wisata.size
    }

    override fun onBindViewHolder(holder: LisViewHolder, position: Int) {
        val Wisata = list_Wisata[position]

        Glide.with(holder.itemView.context)
            .load(Wisata.photoWisata)
            .apply(RequestOptions().override(55, 55))
            .into(holder.img_Wisata)

        holder.tv_name_Wisata.text = Wisata.nameWisata
        holder.tv_jarak_Wisata.text = Wisata.jarakWisata

        holder.itemView.setOnClickListener {
            val moveIntent = Intent(holder.itemView.context, DetailWisata::class.java)
            //mengirimkan data menggunakan parcelable, jadi yg dikirim objek class tersebut, tidak 1tipe data,
            //untuk mempersingkat kodingan.
            moveIntent.putExtra(DATA_TEMPAT_WISATA,list_Wisata[position])
            holder.itemView.context.startActivity(moveIntent)
        }
    }

    class LisViewHolder (itemview: View) : RecyclerView.ViewHolder(itemview) {
        var tv_name_Wisata: TextView = itemview.findViewById(R.id.tv_tempat_wisata)
        var tv_jarak_Wisata: TextView = itemview.findViewById(R.id.tv_jarak)
        var img_Wisata: ImageView = itemview.findViewById(R.id.item_img_wisata)
    }
}