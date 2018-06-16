package com.dgimenes.architecturesample.movieslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dgimenes.architecturesample.R
import com.dgimenes.architecturesample.data.model.Movie

class MoviesAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    class MovieViewHolder(private val rootView: View) : RecyclerView.ViewHolder(rootView) {

        fun bind(movie: Movie) {
            val movieTitleTextView = rootView.findViewById<TextView>(R.id.movie_title)
            movieTitleTextView.text = movie.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_movie, parent, false)
        return MovieViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size
}