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

class KulinerAdapter (private val list_Kuliner: ArrayList<TempatKuliner>) : RecyclerView.Adapter<KulinerAdapter.LisViewHolder>(){
    private lateinit var onItemClick: OnItemClick

    fun setonItemClick(onItemClick: OnItemClick) {
        this.onItemClick = onItemClick
    }

    interface OnItemClick {
        fun onItemClicked(data: TempatKuliner)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): LisViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_kuliner, viewGroup, false)
        return KulinerAdapter.LisViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list_Kuliner.size
    }

    override fun onBindViewHolder(holder: LisViewHolder, position: Int) {
        val Kuliner = list_Kuliner[position]

        Glide.with(holder.itemView.context)
            .load(Kuliner.photoKuliner)
            .apply(RequestOptions().override(55, 55))
            .into(holder.img_Kuliner)

        holder.tv_name_Kuliner.text = Kuliner.nameKuliner
        holder.tv_jarak_Kuliner.text = Kuliner.jarakKuliner

        holder.itemView.setOnClickListener {
            val moveIntent = Intent(holder.itemView.context, Detail_Kuliner::class.java)
            //mengirimkan data menggunakan parcelable, jadi yg dikirim objek class tersebut, tidak 1tipe data,
            //untuk mempersingkat kodingan.
            moveIntent.putExtra(Detail_Kuliner.DATA_TEMPAT_KULINER,list_Kuliner[position])
            holder.itemView.context.startActivity(moveIntent)
        }
    }
    class LisViewHolder (itemview: View) : RecyclerView.ViewHolder(itemview) {
        var tv_name_Kuliner: TextView = itemview.findViewById(R.id.tv_tempat_kuliner)
        var tv_jarak_Kuliner: TextView = itemview.findViewById(R.id.tv_jarakkuliner)
        var img_Kuliner: ImageView = itemview.findViewById(R.id.item_img_kuliner)
    }

}