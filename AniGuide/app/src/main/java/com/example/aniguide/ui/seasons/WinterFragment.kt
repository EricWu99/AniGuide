package com.example.aniguide.ui.seasons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.aniguide.MainActivity
import com.example.aniguide.R
import com.example.aniguide.kitsu_api.Data
import com.example.aniguide.ui.show.ShowListAdapter
import com.example.aniguide.ui.show.ShowViewModel
import com.example.aniguide.ui.home.EpisodeFragment
import com.google.android.material.navigation.NavigationView

class WinterFragment : Fragment() {

    private lateinit var viewModel: ShowViewModel
    private lateinit var showAdapter: ShowListAdapter

    companion object {
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

    private fun enableSearchFunction() {

        val searchBar = activity?.findViewById<SearchView>(R.id.actionSearch)
        searchBar?.setQuery("", true)
        searchBar?.isIconified = true
        searchBar?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{

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

    private fun initAdapter(root: View) {

        val main = root.findViewById<RecyclerView>(R.id.winterShowList)
        showAdapter = ShowListAdapter(viewModel) { openEpisodeList() }

        main.adapter = showAdapter
        main.layoutManager = GridLayoutManager(context, 2)
        //https://stackoverflow.com/questions/36127734/detect-when-recyclerview-reaches-the-bottom-most-position-while-scrolling
        val gridManager = GridLayoutManager(context, 2)
        main.addOnScrollListener( object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if(!recyclerView.canScrollVertically(1)  && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    if (gridManager.findFirstVisibleItemPosition() >= gridManager!!.itemCount - 4) {
                        viewModel.updateOffset(false)
                        viewModel.refreshSeasonalShows()
                    }
                }
            }
        })
    }

    private fun setHeaderImages()
    {
        val navView = activity?.findViewById<NavigationView>(R.id.nav_view)
        val headerView = navView?.getHeaderView(0)
        val headerImage = headerView?.findViewById<ImageView>(R.id.navImage)
        headerImage?.setImageResource(R.drawable.winteranime)

        val appbarImage = activity?.findViewById<ImageView>(R.id.appbar_image)
        appbarImage?.setImageResource(R.drawable.winterscene)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(this).get(ShowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_winter, container, false)
        initAdapter(root)

        viewModel.updateSeason("winter")
        activity?.findViewById<TextView>(R.id.actionTitle)?.text = "Winter"
        setHeaderImages()
        enableSearchFunction()

        viewModel.refreshSeasonalShows()

        viewModel.observeShows().observe(this, Observer {
            viewModel.observeSearchShows(it)
        })

        viewModel.getSearchShows().observe(this, Observer {
            submitShows(it, showAdapter)
        })
        return root
    }
}