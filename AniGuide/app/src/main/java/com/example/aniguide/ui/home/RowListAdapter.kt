package com.example.aniguide.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aniguide.R
import com.example.aniguide.api.Episode
import com.example.aniguide.glide.Glide


class RowListAdapter(private val viewModel: HomeViewModel)
    : ListAdapter<Episode, RowListAdapter.VH>(TMDB_Diff()) {

    private var episodes = listOf<Episode>()

    inner class VH(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

        var title = itemView.findViewById<TextView>(R.id.ep_title)
        var image = itemView.findViewById<ImageView>(R.id.ep_image)
        var descr = itemView.findViewById<TextView>(R.id.ep_text)

        fun bind(item: Episode) {

            title.text = item.name
            descr.text = item.overview
            Glide.glideFetch("https://image.tmdb.org/t/p/w500${item.still_path}",
                "https://image.tmdb.org/t/p/w500${item.still_path}", image)

            title.setOnClickListener {
                HomeViewModel.showMoreInfo(it.context, item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_episodes, parent, false)
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(episodes[holder.adapterPosition])
    }

    fun submitEpisodes(item: List<Episode>) {
        episodes = item
        notifyDataSetChanged()
    }

    override fun getItemCount() = episodes.size

    class TMDB_Diff : DiffUtil.ItemCallback<Episode>() {

        override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem.name == newItem.name && oldItem.overview == newItem.overview
        }
    }

}

