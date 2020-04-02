package com.example.tralova.ui.slideshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tralova.R
import com.example.tralova.ui.share.Group
import com.example.tralova.ui.share.GroupAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ReminderAdapter(private val listRemind: ArrayList<Reminder>) :
    RecyclerView.Adapter<ReminderAdapter.ListViewHolder>() {
    private lateinit var confirmBuilder: MaterialAlertDialogBuilder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_reminder, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listRemind.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val remind = listRemind[position]
        holder.tvRemindName.text = remind.remind
        holder.tvRemindTime.text = remind.time
        holder.tvRemindDate.text = remind.date

        confirmBuilder = MaterialAlertDialogBuilder(
            holder.itemView.context,
            R.style.Theme_MaterialComponents_Dialog_Alert
        )
        holder.del_remind.setOnClickListener {
            // Alert
            confirmBuilder.setMessage("Delete This Reminder?")
                .setTitle("Delete")
                .setPositiveButton("OK") { _, _ ->
                    listRemind.removeAt(position)
                    notifyDataSetChanged()
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, listRemind.size)
                }
                .setNegativeButton("Cancel") { DialogInterface, _ ->
                    DialogInterface.dismiss()
                }
            confirmBuilder.show()
        }

    }

    class ListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvRemindName: TextView = itemView.findViewById(R.id.tv_judul_rem)
        var tvRemindTime: TextView = itemView.findViewById(R.id.tv_time)
        var tvRemindDate: TextView = itemView.findViewById(R.id.tv_date)
        var del_remind:ImageView = itemView.findViewById(R.id.img_delRemind)
    }
}