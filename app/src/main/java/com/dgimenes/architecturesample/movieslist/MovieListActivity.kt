package com.dgimenes.architecturesample.movieslist

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.dgimenes.architecturesample.R
import com.dgimenes.architecturesample.android.ItemOffsetDecoration
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity() {

    private lateinit var moviesAdapter: MoviesAdapter

    private lateinit var movieListViewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        movieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)
        movieListViewModel.init()
        setupMoviesListUI()
    }

    private fun setupMoviesListUI() {
        movies_recyclerview.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        )
        movies_recyclerview.addItemDecoration(ItemOffsetDecoration(this))
        moviesAdapter = MoviesAdapter(movieListViewModel.getMovies())
        movies_recyclerview.adapter = moviesAdapter
    }

}
