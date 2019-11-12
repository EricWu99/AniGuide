package com.example.aniguide.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aniguide.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
//    private lateinit var rowAdapter: RowListAdapter

//    private fun initAdapter(root: View) {
//
//        val main = root.findViewById<RecyclerView>(R.id.showList)
//        rowAdapter = RowListAdapter(homeViewModel)
//
//        main.adapter = rowAdapter
//        main.layoutManager = LinearLayoutManager(context)
//    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)

        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}