package com.example.aniguide.ui.spring

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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.aniguide.R
import com.example.aniguide.kitsu_api.Data
import com.example.aniguide.ui.ShowListAdapter
import com.example.aniguide.ui.ShowViewModel

class SpringFragment : Fragment() {

    private lateinit var viewModel: ShowViewModel
    private lateinit var showAdapter: ShowListAdapter

    private fun submitShows(shows: List<Data>, adapter: ShowListAdapter) {

        adapter.submitShows(shows)
    }

    private fun initAdapter(root: View) {

        val main = root.findViewById<RecyclerView>(R.id.springShowList)
        showAdapter = ShowListAdapter(viewModel)

        main.adapter = showAdapter
        main.layoutManager = LinearLayoutManager(context)
    }

    private fun initSwipeLayout(root: View) {

        val swipe = root.findViewById<SwipeRefreshLayout>(R.id.springSwipeRefreshLayout)
        swipe.setOnRefreshListener {

            viewModel.refreshSeasonalShows()
            swipe.isRefreshing = false
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(this).get(ShowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_spring, container, false)
        initAdapter(root)
        initSwipeLayout(root)

        viewModel.updateSeason("spring")
        viewModel.refreshSeasonalShows()

        viewModel.observeShows().observe(this, Observer {
            submitShows(it, showAdapter)
        })
        return root
    }
}