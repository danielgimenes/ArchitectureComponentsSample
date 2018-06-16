package com.dgimenes.architecturesample.movieslist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.dgimenes.architecturesample.R
import com.dgimenes.architecturesample.android.ItemOffsetDecoration
import com.dgimenes.architecturesample.data.model.Movie
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity() {

    private lateinit var moviesAdapter: MoviesAdapter

    private lateinit var movieListViewModel: MovieListViewModel

    private val movies = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        movieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)
        movieListViewModel.init()
        setupMoviesListUI()

        movieListViewModel.getPopularMovies().observe(
                this,
                Observer<List<Movie>> { movies ->
                    if (movies == null) {
                        // TODO BETTER ERROR HANDLING
                        showError(null)
                        return@Observer
                    }
                    renderMovies(movies)
                }
        )
    }

    private fun showError(error: Throwable?) {
        // TODO BETTER ERROR HANDLING
        Toast.makeText(this@MovieListActivity, "Error: ${error?.message}", Toast.LENGTH_SHORT)
                .show()
    }

    private fun renderMovies(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        moviesAdapter.notifyDataSetChanged()
    }

    private fun setupMoviesListUI() {
        movies_recyclerview.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        )
        movies_recyclerview.addItemDecoration(ItemOffsetDecoration(this))
        moviesAdapter = MoviesAdapter(movies)
        movies_recyclerview.adapter = moviesAdapter
    }

}
