package com.gdscug.mooflix.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gdscug.mooflix.R
import com.gdscug.mooflix.data.local.MoviesEntity
import com.gdscug.mooflix.databinding.ItemMovieBinding
import com.gdscug.mooflix.utils.sharedObject.IMG_URL

class MovieAdapter : ListAdapter<MoviesEntity, MovieAdapter.MovieViewHolder>(DIFFUTIL) {

    var onItemClickCallback: OnItemClickcallback? = null
    private object DIFFUTIL : DiffUtil.ItemCallback<MoviesEntity>() {
        override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
            return  oldItem == newItem
        }

    }
    class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MoviesEntity, itemClicked: () -> Unit) {
            binding.apply {
                ivTitle.text = movie.title
                Glide.with(itemView.context)
                    .load(IMG_URL + movie.posterPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_broken_img))
                    .into(ivPoster)
            }
            itemView.setOnClickListener { itemClicked.invoke() }
        }

    }

    interface OnItemClickcallback {
        fun onItemClicked(movie: MoviesEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(
                    parent
                        .context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie) {
            onItemClickCallback?.onItemClicked(movie)
        }
    }

}


