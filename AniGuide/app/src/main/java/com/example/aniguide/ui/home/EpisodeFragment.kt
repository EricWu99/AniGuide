package com.example.aniguide.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.aniguide.MainActivity
import com.example.aniguide.R
import com.example.aniguide.tmdb_api.Episode
import com.example.aniguide.ui.episode.EpisodeListAdapter
import com.example.aniguide.ui.episode.EpisodeViewModel

class EpisodeFragment : Fragment() {

    private lateinit var viewModel: EpisodeViewModel
    private lateinit var episodeAdapter: EpisodeListAdapter

    private fun submitEpisodes(episode: List<Episode>, adapter: EpisodeListAdapter) {

        adapter.submitEpisodes(episode)
    }

    private fun initAdapter(root: View) {

        val main = root.findViewById<RecyclerView>(R.id.epShowList)
        episodeAdapter = EpisodeListAdapter(viewModel)

        main.adapter = episodeAdapter
        main.layoutManager = LinearLayoutManager(context)
    }

    private fun initSwipeLayout(root: View) {

        val swipe = root.findViewById<SwipeRefreshLayout>(R.id.epSwipeRefreshLayout)
        swipe.setOnRefreshListener {

            viewModel.refreshEpisodes()
            swipe.isRefreshing = false
        }
    }

    private fun setActionTitle(value: String)
    {
        activity?.findViewById<TextView>(R.id.actionTitle)?.text = value.replace("+", " ")
    }

    private fun enableSearchFunction() {

        activity?.findViewById<EditText>(R.id.actionSearch)?.addTextChangedListener {
            viewModel.updateSearchTerm(it.toString())
            if(it.toString() == "") (activity as MainActivity).hideKeyboard()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = activity?.run {
            ViewModelProviders.of(this).get(EpisodeViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        val root = inflater.inflate(R.layout.fragment_episodes, container, false)
        initAdapter(root)
        initSwipeLayout(root)

        val selectedShow = arguments!!.getString(HomeFragment.show_key)!!
        viewModel.updateShow(selectedShow)

        setActionTitle(viewModel.observeShow().value!!)
        enableSearchFunction()

        viewModel.observeShow().observe(this, Observer {
            viewModel.refreshEpisodes()
        })

        viewModel.observeEpisodes().observe(this, Observer {
            viewModel.observeSearchEpisodes(it)
        })

        viewModel.getSearchEpisodes().observe(this, Observer {
            submitEpisodes(it, episodeAdapter)
        })
        return root
    }
}