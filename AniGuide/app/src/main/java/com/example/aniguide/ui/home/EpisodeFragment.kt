package com.example.aniguide.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.aniguide.MainActivity
import com.example.aniguide.R
import com.example.aniguide.glide.Glide
import com.example.aniguide.tmdb_api.Episode
import com.example.aniguide.ui.episode.EpisodeListAdapter
import com.example.aniguide.ui.episode.EpisodeViewModel

class EpisodeFragment : Fragment() {

    private lateinit var viewModel: EpisodeViewModel
    private lateinit var episodeAdapter: EpisodeListAdapter

    private fun setAppbarImage(value: String)
    {
        val appbarImage = activity?.findViewById<ImageView>(R.id.appbar_image)
        if(appbarImage?.drawable != null)
            Glide.glideFetch("$value", "$value", appbarImage!!)
    }

    private fun submitEpisodes(episode: List<Episode>, adapter: EpisodeListAdapter) {

        adapter.submitEpisodes(episode)
    }

    private fun initAdapter(root: View) {

        val main = root.findViewById<RecyclerView>(R.id.epShowList)
        episodeAdapter = EpisodeListAdapter(viewModel) { value -> setAppbarImage(value) }

        main.adapter = episodeAdapter
        main.layoutManager = LinearLayoutManager(context)
    }

    private fun setActionTitle(value: String)
    {
        activity?.findViewById<TextView>(R.id.actionTitle)?.text = value.replace("+", " ")
    }

    private fun enableSearchFunction() {

        activity?.findViewById<SearchView>(R.id.actionSearch)?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{

            override fun onQueryTextChange(value: String?): Boolean {
                viewModel.updateSearchTerm(value.toString())
                if(value.toString() == "") (activity as MainActivity).hideKeyboard()
                return true
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
        })
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

        val selectedShow = arguments!!.getString(PopularFragment.show_key)!!
        viewModel.updateShow(selectedShow)
        viewModel.updateSeason()

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