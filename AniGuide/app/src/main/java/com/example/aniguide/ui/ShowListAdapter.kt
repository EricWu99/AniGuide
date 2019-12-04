package com.example.aniguide.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aniguide.MainActivity
import com.example.aniguide.R
import com.example.aniguide.tmdb_api.Episode
import com.example.aniguide.glide.Glide
import com.example.aniguide.kitsu_api.Data
import com.example.aniguide.ui.tmdb_ep.EpisodeViewModel


class ShowListAdapter(private val viewModel: ShowViewModel,
                      private val openEpisodeList: ()->Unit) : ListAdapter<Episode, ShowListAdapter.VH>( TMDB_Diff() ) {

    private var shows = listOf<Data>()

    inner class VH(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

        var title = itemView.findViewById<TextView>(R.id.ep_title)
        var image = itemView.findViewById<ImageView>(R.id.ep_image)
        var descr = itemView.findViewById<TextView>(R.id.ep_text)

        fun bind(item: Data) {

            title.text = item.attributes.canonicalTitle
            descr.text = item.attributes.synopsis
            Glide.glideFetch("${item.attributes.posterImage.large}", "${item.attributes.posterImage.large}", image)

            title.setOnClickListener {
                viewModel.setSelectedShow(item.attributes.canonicalTitle)
                openEpisodeList()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_show, parent, false)
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(shows[holder.adapterPosition])
    }

    fun submitShows(item: List<Data>) {
        shows = item
        notifyDataSetChanged()
    }

    override fun getItemCount() = shows.size

    class TMDB_Diff : DiffUtil.ItemCallback<Episode>() {

        override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem.name == newItem.name && oldItem.overview == newItem.overview
        }
    }

}

