package com.example.aniguide.ui.home

import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aniguide.R
import com.example.aniguide.glide.AppGlideModule
import com.example.aniguide.glide.Glide


//class PostRowListAdapter(private val viewModel: MainViewModel,
//    // If true call notifyDataSetChanged if unfavorited
//                         private val unfavoriteIsRemove: Boolean = false)
//    : ListAdapter<RedditPost, PostRowListAdapter.VH>(RedditDiff()) {
//
//    private var posts = listOf<RedditPost>()
//
//    inner class VH(itemView: View)
//        : RecyclerView.ViewHolder(itemView) {
//
//        var title = itemView.findViewById<TextView>(R.id.title)
//        var image = itemView.findViewById<ImageView>(R.id.image)
//        var selfText = itemView.findViewById<TextView>(R.id.selfText)
//        var scoreIcon = itemView.findViewById<ImageView>(R.id.score_icon)
//        var scoreValue = itemView.findViewById<TextView>(R.id.score)
//        var commentIcon = itemView.findViewById<ImageView>(R.id.comments_icon)
//        var commentValue = itemView.findViewById<TextView>(R.id.comments)
//        var favoritesIcon = itemView.findViewById<ImageView>(R.id.rowFav)
//
//        fun bind(item: RedditPost) {
//
//            title.text = item.title
//            Glide.glideFetch(item.imageURL, item.thumbnailURL, image)
//            selfText.text = item.selfText
//
//            scoreIcon.setImageResource(R.drawable.ic_star_black_24dp)
//            scoreValue.text = item.score.toString()
//
//            commentIcon.setImageResource(R.drawable.ic_comment_black_24dp)
//            commentValue.text = item.commentCount.toString()
//
//            if(viewModel.isFavorite(item))
//                favoritesIcon.setImageResource(R.drawable.ic_favorite_black_24dp)
//            else
//                favoritesIcon.setImageResource(R.drawable.ic_favorite_border_black_24dp)
//
//            favoritesIcon.setOnClickListener {
//
//                if(viewModel.isFavorite(item)) {
//                    viewModel.removeFavoritesPost(item)
//                    favoritesIcon.setImageResource(R.drawable.ic_favorite_border_black_24dp)
//                } else {
//                    viewModel.addFavoritesPost(item)
//                    favoritesIcon.setImageResource(R.drawable.ic_favorite_black_24dp)
//                }
//            }
//
//            title.setOnClickListener {
//                MainViewModel.doOnePost(it.context, item)
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
//        val itemView = LayoutInflater.from(parent.context)
//            .inflate(R.layout.row_post, parent, false)
//        return VH(itemView)
//    }
//
//    override fun onBindViewHolder(holder: VH, position: Int) {
//        holder.bind(posts[holder.adapterPosition])
//    }
//
//    fun submitPostList(items: List<RedditPost>) {
//        posts = items
//        notifyDataSetChanged()
//    }
//
//    override fun getItemCount() = posts.size
//
//
//    class RedditDiff : DiffUtil.ItemCallback<RedditPost>() {
//
//        override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
//            return oldItem.key == newItem.key
//        }
//
//        override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
//            return oldItem.title == newItem.title
//                    && oldItem.score == newItem.score
//                    && oldItem.commentCount == newItem.commentCount
//        }
//    }
//
//}

