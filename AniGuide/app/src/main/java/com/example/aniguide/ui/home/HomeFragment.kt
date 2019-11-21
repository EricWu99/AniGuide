package com.example.aniguide.ui.home

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aniguide.R
import com.example.aniguide.api.Episode
import kotlinx.android.synthetic.main.action_bar.*

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var rowAdapter: RowListAdapter


    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    fun myRestore() {

    }

    private fun submitEpisodes(Episode: List<Episode>, adapter: RowListAdapter) {

        adapter.submitEpisodes(Episode)
    }

    private fun initAdapter(root: View) {

        val main = root.findViewById<RecyclerView>(R.id.showList)
        rowAdapter = RowListAdapter(viewModel)

        main.adapter = rowAdapter
        main.layoutManager = LinearLayoutManager(context)
    }

    private fun setActionTitle(value: String)
    {
        activity?.findViewById<TextView>(R.id.actionTitle)?.text = value.replace("+", " ")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        initAdapter(root)

        setActionTitle(viewModel.observeSeries().value!!)

        viewModel.observeSeries().observe(this, Observer {
            viewModel.refreshEpisodes()
        })

        viewModel.observeEpisodes().observe(this, Observer {
            submitEpisodes(it, rowAdapter)
        })
        return root
    }
}