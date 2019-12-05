package com.example.aniguide.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.aniguide.R
import com.example.aniguide.kitsu_api.Data
import com.example.aniguide.ui.show.ShowListAdapter
import com.example.aniguide.ui.show.ShowViewModel

class PopularFragment : Fragment() {

    private lateinit var viewModel: ShowViewModel
    private lateinit var showAdapter: ShowListAdapter

    companion object{
        const val show_key = "show_key"
    }

    private fun openEpisodeList()
    {
        val fragment = EpisodeFragment()
        val bundle = Bundle()
        bundle.putString(show_key, viewModel.observeSelectedShow().value!!)
        fragment.arguments = bundle

        fragmentManager!!
            .beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

    private fun submitShows(shows: List<Data>, adapter: ShowListAdapter) {

        adapter.submitShows(shows)
    }

    private fun initAdapter(root: View) {

        val main = root.findViewById<RecyclerView>(R.id.showList)
        showAdapter = ShowListAdapter(viewModel) { openEpisodeList() }

        main.adapter = showAdapter
        main.layoutManager = GridLayoutManager(context, 2)
    }

    private fun initSwipeLayout(root: View) {

        val swipe = root.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        swipe.setOnRefreshListener {

            viewModel.refreshAllShows()
            swipe.isRefreshing = false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(this).get(ShowViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        initAdapter(root)
        initSwipeLayout(root)

        activity?.findViewById<TextView>(R.id.actionTitle)?.text = "Popular"

        viewModel.refreshAllShows()

        viewModel.observeShows().observe(this, Observer {
            submitShows(it, showAdapter)
        })

        return root
    }
}