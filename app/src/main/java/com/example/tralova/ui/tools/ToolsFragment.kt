package com.example.tralova.ui.tools

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
import com.example.tralova.ui.share.GroupAdapter
import com.example.tralova.ui.share.ShareViewModel
import kotlinx.android.synthetic.main.fragment_tools.*
import kotlinx.android.synthetic.main.fragment_tools.view.*

class ToolsFragment : Fragment() {
//    private lateinit var toolsViewModel: ToolsViewModel
    var REQUEST_CODE1 = 0
    private lateinit var list: ArticleAdapter
    private var article_list: ArrayList<Article> = arrayListOf()
    private lateinit var toolsViewModel: ToolsViewModel
    private lateinit var rvArticleList: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        toolsViewModel =
//            ViewModelProviders.of(this).get(ToolsViewModel::class.java)
//        val root = inflater.inflate(R.layout.fragment_tools, container, false)
//        val textView: TextView = root.findViewById(R.id.text_tools)
//        toolsViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
//        return root
        val view = inflater.inflate(R.layout.fragment_tools, container, false)
        list = ArticleAdapter(DataArticle.listDataArt)
        view.rvArticle.layoutManager = LinearLayoutManager(view.context)
        view.rvArticle.adapter = list

        activity?.let {
            toolsViewModel = ViewModelProviders.of(it).get(ToolsViewModel::class.java)
        }
        //menjaga data agar tetap Live data ketika orientasi
        activity?.let { _ ->
            toolsViewModel.getAllDataArticle(article_list)?.observe(this, Observer {
                if (it.isNotEmpty()) {
                    list = ArticleAdapter(it)
                    list.notifyDataSetChanged()
                    rvArticleList.adapter = list
                }
            })
        }

        view.fabAddArt.setOnClickListener {
            addArticle()
        }
        return view
    }

    fun addArticle() {
        val articleAdd = Intent(requireContext(), AddArticleActivity::class.java)
        startActivityForResult(articleAdd, REQUEST_CODE1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE1 && resultCode == Activity.RESULT_OK) {
            lateinit var dataList: Article
            val item = data?.getStringArrayListExtra("articleData")
            if (item != null && item.isNotEmpty()) {
                if (item.size > 0) {
                    dataList = Article(
                        item[0],item[2],item[1],
                        Uri.parse(item[3])
                    )
                    val list: ArrayList<Article> = arrayListOf(dataList)
                    article_list.addAll(list)
                    DataArticle.listDataArt = (article_list)
                    list.clear()
                    article_list.clear()
                }
            }
        }
        list = ArticleAdapter(DataArticle.listDataArt)
        rvArticle.layoutManager = LinearLayoutManager(context)
        rvArticle.adapter = list
    }
}