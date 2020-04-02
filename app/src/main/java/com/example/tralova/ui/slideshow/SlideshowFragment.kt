package com.example.tralova.ui.slideshow

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tralova.R
import com.example.tralova.ui.share.AddGroupActivity
import com.example.tralova.ui.share.DataGroup
import com.example.tralova.ui.share.Group
import com.example.tralova.ui.share.GroupAdapter
import kotlinx.android.synthetic.main.fragment_share.view.*
import kotlinx.android.synthetic.main.fragment_slideshow.*
import kotlinx.android.synthetic.main.fragment_slideshow.view.*
import java.text.DateFormatSymbols
import java.util.*
import kotlin.collections.ArrayList

class SlideshowFragment : Fragment() {

    var REQUEST_CODE = 0
    private lateinit var slideshowViewModel: SlideshowViewModel
    private lateinit var list: ReminderAdapter
    private var remindList: ArrayList<Reminder> = arrayListOf()
    private lateinit var rvRemindList: RecyclerView

    lateinit var todoAlert : AlertDialog
    lateinit var custLayoutInflater: LayoutInflater
    lateinit var addToDoLayout : View
    lateinit var builder: AlertDialog.Builder
    lateinit var tvDate : TextView
    lateinit var tvTime : TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        slideshowViewModel =
//            ViewModelProviders.of(this).get(SlideshowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        list = ReminderAdapter(DataRemind.listDataRemind)
        root.rvRemind.layoutManager = LinearLayoutManager(root.context)
        root.rvRemind.adapter = list
        rvRemindList = root.rvRemind
//        val textView: TextView = root.findViewById(R.id.text_slideshow)
//        slideshowViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
        activity?.let {
            slideshowViewModel = ViewModelProviders.of(it).get(SlideshowViewModel::class.java)
        }
        //menjaga data agar tetap Live data ketika orientasi
        activity?.let { _ ->
            slideshowViewModel.getAllDataRemind(remindList)?.observe(this, Observer {
                if (it.isNotEmpty()) {
                    list = ReminderAdapter(it)
                    list.notifyDataSetChanged()
                    rvRemindList.adapter = list
                }
            })
        }
        root.fabAddRem.setOnClickListener {
            addRemind()
        }
        rvRemindList.layoutManager = LinearLayoutManager(context)
        return root
    }

    fun addRemind() {
        val remindAdd = Intent(requireContext(), AddRemindActivity::class.java)
        startActivityForResult(remindAdd, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            lateinit var dataList: Reminder
            val item = data?.getStringArrayListExtra("remindData")
            if (item != null && item.isNotEmpty()) {
                if (item.size > 0) {
                    dataList = Reminder(item[0], item[1],item[2],item[3])
                    remindList.add(dataList)
                    //menjaga data agar tetap live data ketika orientasi layar
                    activity?.let { activity ->
                        slideshowViewModel.getAllDataRemind(remindList)?.observe(activity, Observer {
                            if (it.isNotEmpty()) {
                                list = ReminderAdapter(it)
                                list.notifyDataSetChanged()
                                rvRemindList.adapter = list
                            }
                        })
                    }
                    list = ReminderAdapter(remindList)
                    rvRemindList.adapter = list
                }
            }
        }

    }


}
