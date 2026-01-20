package com.example.nestedrecyclerview.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nestedrecyclerview.R
import com.example.nestedrecyclerview.data.MovieItem

class MovieAdapter :
  ListAdapter<MovieItem, MovieAdapter.MovieVH>(Diff) {

    companion object {
    val Diff = object : DiffUtil.ItemCallback<MovieItem>() {
      override fun areItemsTheSame(old: MovieItem, new: MovieItem) =
        old.id == new.id

      override fun areContentsTheSame(old: MovieItem, new: MovieItem) =
        old == new
    }
  }

  inner class MovieVH(view: View) : RecyclerView.ViewHolder(view) {
    val poster: ImageView = view.findViewById(R.id.ivPoster)
    val title: TextView = view.findViewById(R.id.tvMovieTitle)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.movie_item, parent, false)
    return MovieVH(view)
  }
  //datasource > repo> usecase> vm> update ui
  override fun onBindViewHolder(holder: MovieVH, position: Int) {
    val movie = getItem(position)

    holder.title.text = movie.title

    Glide.with(holder.itemView)
      .load(movie.posterUrl)
      .centerCrop()
      .into(holder.poster)
  }
}