package com.example.tralova.ui.gallery

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
import com.example.tralova.ui.gallery.DetailHealthCareActivity.Companion.DATA_HEALTH_CARE

class HealtyCareAdapter(private val list_healtycare: ArrayList<HealthCare>) : RecyclerView.Adapter<HealtyCareAdapter.LisViewHolder>() {
    private lateinit var onItemClick: OnItemClick

    fun setonItemClick(onItemClick: OnItemClick) {
        this.onItemClick = onItemClick
    }

    interface OnItemClick {
        fun onItemClicked(data: HealthCare)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): LisViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_healthcare, viewGroup, false)
        return LisViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list_healtycare.size
    }

    override fun onBindViewHolder(holder: LisViewHolder, position: Int) {
        val Healty = list_healtycare[position]

        Glide.with(holder.itemView.context)
            .load(Healty.pp)
            .apply(RequestOptions().override(55, 55))
            .into(holder.pp_wisatawan)

        holder.tv_name_wisatawan.text = Healty.name
        holder.tv_asal.text = Healty.asal
        holder.tv_umur.text = Healty.umur

        holder.itemView.setOnClickListener {
            val moveIntent = Intent(holder.itemView.context, DetailHealthCareActivity::class.java)
            //mengirimkan data menggunakan parcelable, jadi yg dikirim objek class tersebut, tidak 1tipe data,
            //untuk mempersingkat kodingan.
            moveIntent.putExtra(DATA_HEALTH_CARE,list_healtycare[position])
            holder.itemView.context.startActivity(moveIntent)
        }
    }

    class LisViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var tv_name_wisatawan: TextView = itemview.findViewById(R.id.tv_name_wisatawan)
        var tv_asal: TextView = itemview.findViewById(R.id.tv_asal_daerah)
        var tv_umur: TextView = itemview.findViewById(R.id.tv_umur)
        var pp_wisatawan: ImageView = itemview.findViewById(R.id.item_pp_wisatawan)
    }
}