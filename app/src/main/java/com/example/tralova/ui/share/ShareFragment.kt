package com.example.tralova.ui.share

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tralova.R
import kotlinx.android.synthetic.main.fragment_share.view.*

class ShareFragment : Fragment() {

    //    private lateinit var shareViewModel: ShareViewModel
    var REQUEST_CODE = 0
    private lateinit var list: GroupAdapter
    private var groupList: ArrayList<Group> = arrayListOf()
    private lateinit var shareViewModel: ShareViewModel
    private lateinit var rvGroupList: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_share, container, false)
        list = GroupAdapter(DataGroup.listData)
        view.rvGroup.layoutManager = LinearLayoutManager(view.context)
        view.rvGroup.adapter = list
        rvGroupList = view.rvGroup
//        val textView: TextView = root.findViewById(R.id.text_share)
//        shareViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
        activity?.let {
            shareViewModel = ViewModelProviders.of(it).get(ShareViewModel::class.java)
        }
        //menjaga data agar tetap Live data ketika orientasi
        activity?.let { _ ->
            shareViewModel.getAllDataGroup(groupList)?.observe(this, Observer {
                if (it.isNotEmpty()) {
                    list = GroupAdapter(it)
                    list.notifyDataSetChanged()
                    rvGroupList.adapter = list
                }
            })
        }
        view.fabAdd.setOnClickListener {
            addGroup()
        }
        rvGroupList.layoutManager = LinearLayoutManager(context)
        return view
    }

    fun addGroup() {
        val groupAdd = Intent(requireContext(), AddGroupActivity::class.java)
        startActivityForResult(groupAdd, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            lateinit var dataList: Group
            val item = data?.getStringArrayListExtra("groupData")
            if (item != null && item.isNotEmpty()) {
                if (item.size > 0) {
                    dataList = Group(item[0], Uri.parse(item[1]))
                    groupList.add(dataList)
                    //menjaga data agar tetap live data ketika orientasi layar
                    activity?.let { activity ->
                        shareViewModel.getAllDataGroup(groupList)?.observe(activity, Observer {
                            if (it.isNotEmpty()) {
                                list = GroupAdapter(it)
                                list.notifyDataSetChanged()
                                rvGroupList.adapter = list
                            }
                        })
                    }
                    list = GroupAdapter(groupList)
                    rvGroupList.adapter = list
                }
            }
        }

    }


//    private fun showGroup(){
//        val view = inflater.inflate(R.layout.fragment_share, container, false)
//        list = GroupAdapter(DataGroup.listData)
//        view.rvGroup.layoutManager = LinearLayoutManager(view.context)
//        view.rvGroup.adapter = list
//    }
}