package com.example.tralova.ui.share

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
import com.example.tralova.ui.chat.ChatGroupActivity
import com.example.tralova.ui.tools.Article
import com.example.tralova.ui.tools.ArticleAdapter
import com.example.tralova.ui.tools.DetailArticleActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class GroupAdapter(private val listGroup: ArrayList<Group>) :
    RecyclerView.Adapter<GroupAdapter.ListViewHolder>() {

    private lateinit var onItemClick: ArticleAdapter.OnItemClick
    private lateinit var confirmBuilder: MaterialAlertDialogBuilder

    fun setonItemClick(onItemClick: OnItemClick) {
        this.onItemClick = onItemClick
    }

    interface OnItemClick : ArticleAdapter.OnItemClick {
        fun onItemClicked(data: Group)
    }
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvGroupName: TextView = itemView.findViewById(R.id.tv_group_name)
        var imgGroupPhoto: ImageView =
            itemView.findViewById(R.id.img_group_photo)
        var imgDelete: ImageView = itemView.findViewById(R.id.img_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_group, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listGroup.size
    }



    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val group = listGroup[position]
        Log.d("Check Uri", group.GroupPhoto.toString())
        if (group.GroupPhoto.toString() != "null") {
            Log.d("Glide", "Masuk")
            Glide.with(holder.itemView.context)
                .load(group.GroupPhoto)
                .apply(RequestOptions())
                .into(holder.imgGroupPhoto)
        } else
            holder.imgGroupPhoto.setImageResource(R.drawable.ic_pp_group)
        holder.tvGroupName.text = group.GroupName

        confirmBuilder = MaterialAlertDialogBuilder(
            holder.itemView.context,
            R.style.Theme_MaterialComponents_Dialog_Alert
        )
        holder.imgDelete.setOnClickListener {
            // Alert
            confirmBuilder.setMessage("Delete This Group?")
                .setTitle("Delete")
                .setPositiveButton("OK") { _, _ ->
                    listGroup.removeAt(position)
                    notifyDataSetChanged()
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, listGroup.size)
                }
                .setNegativeButton("Cancel") { DialogInterface, _ ->
                    DialogInterface.dismiss()
                }
            confirmBuilder.show()
        }

        holder.itemView.setOnClickListener {
            val moveIntent = Intent(holder.itemView.context, ChatGroupActivity::class.java)
            //mengirimkan data menggunakan parcelable, jadi yg dikirim objek class tersebut, tidak 1tipe data,
            //untuk mempersingkat kodingan.
            moveIntent.putExtra(ChatGroupActivity.DATA_GROUP,listGroup[position])
            holder.itemView.context.startActivity(moveIntent)
        }
    }


}