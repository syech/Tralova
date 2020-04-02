package com.example.tralova.ui.tools

import android.content.Intent
import android.util.Log
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ArticleAdapter(private val listArticle: ArrayList<Article>) : RecyclerView.Adapter<ArticleAdapter.ListViewHolder>() {
    private lateinit var onItemClick: OnItemClick
    private lateinit var confirmBuilder: MaterialAlertDialogBuilder

    fun setonItemClick(onItemClick: OnItemClick) {
        this.onItemClick = onItemClick
    }

    interface OnItemClick {
        fun onItemClicked(data: Article)
    }
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvArtJudul: TextView = itemView.findViewById(com.example.tralova.R.id.tv_judul_art)
        var tvArtSumber: TextView = itemView.findViewById(com.example.tralova.R.id.tv_sumber_art)
        var imgArtPhoto: ImageView = itemView.findViewById(com.example.tralova.R.id.img_article_photo)
        var delArt: ImageView = itemView.findViewById(com.example.tralova.R.id.img_deleteArt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(com.example.tralova.R.layout.item_article, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listArticle.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val article = listArticle[position]
        Log.d("Check Uri", article.Img_Art.toString())
        if (article.Img_Art.toString() != "null") {
            Log.d("Glide", "Masuk")
            Glide.with(holder.itemView.context)
                .load(article.Img_Art)
                .apply(RequestOptions())
                .into(holder.imgArtPhoto)
        } else
            holder.imgArtPhoto.setImageResource(com.example.tralova.R.drawable.ic_add_photo)
        holder.tvArtJudul.text = article.Judul
        holder.tvArtSumber.text = article.Sumber

        holder.itemView.setOnClickListener {
            val moveIntent = Intent(holder.itemView.context, DetailArticleActivity::class.java)
            //mengirimkan data menggunakan parcelable, jadi yg dikirim objek class tersebut, tidak 1tipe data,
            //untuk mempersingkat kodingan.
            moveIntent.putExtra(DetailArticleActivity.DATA_ARTICLE,listArticle[position])
            holder.itemView.context.startActivity(moveIntent)
        }

        confirmBuilder = MaterialAlertDialogBuilder(
            holder.itemView.context,
            R.style.Theme_MaterialComponents_Dialog_Alert
        )
        holder.delArt.setOnClickListener {
            // Alert
            confirmBuilder.setMessage("Delete This Article?")
                .setTitle("Delete")
                .setPositiveButton("OK") { _, _ ->
                    listArticle.removeAt(position)
                    notifyDataSetChanged()
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, listArticle.size)
                }
                .setNegativeButton("Cancel") { DialogInterface, _ ->
                    DialogInterface.dismiss()
                }
            confirmBuilder.show()
        }

    }

}
