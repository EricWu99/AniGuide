package com.example.aniguide.ui.show

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aniguide.R
import com.example.aniguide.tmdb_api.Episode
import com.example.aniguide.glide.Glide
import com.example.aniguide.kitsu_api.Data
import com.example.aniguide.kitsu_api.Shows


class ShowListAdapter(private val viewModel: ShowViewModel,
                      private val openEpisodeList: ()->Unit) : ListAdapter<Shows, ShowListAdapter.VH>(Diff()) {

    private var shows = listOf<Data>()

    inner class VH(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

        var title = itemView.findViewById<TextView>(R.id.ep_title)
        var image = itemView.findViewById<ImageView>(R.id.ep_image)

        fun bind(item: Data) {

            title.text = item.attributes.canonicalTitle
            Glide.glideFetch("${item.attributes.posterImage.large}", "${item.attributes.posterImage.large}", image)

            title.setOnClickListener {
                viewModel.updateSelectedShow(item.attributes.canonicalTitle)
                openEpisodeList()
            }
            image.setOnClickListener {
                viewModel.updateSelectedShow(item.attributes.canonicalTitle)
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

    class Diff : DiffUtil.ItemCallback<Shows>() {

        override fun areItemsTheSame(oldItem: Shows, newItem: Shows): Boolean {
            return oldItem.data.id == newItem.data.id
        }

        override fun areContentsTheSame(oldItem: Shows, newItem: Shows): Boolean {
            return oldItem.data.attributes.canonicalTitle == newItem.data.attributes.canonicalTitle
                    && oldItem.data.attributes.synopsis == newItem.data.attributes.synopsis
        }
    }

}

