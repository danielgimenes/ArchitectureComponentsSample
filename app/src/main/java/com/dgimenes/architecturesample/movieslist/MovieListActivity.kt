package com.dgimenes.architecturesample.movieslist

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.dgimenes.architecturesample.R
import com.dgimenes.architecturesample.android.ItemOffsetDecoration
import com.dgimenes.architecturesample.data.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity() {

    private lateinit var moviesAdapter: MoviesAdapter

    private lateinit var movieListViewModel: MovieListViewModel

    private var movies = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        movieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)
        movieListViewModel.init()
        setupMoviesListUI()

        loadPopularMovies()
    }

    private fun loadPopularMovies() {
        movieListViewModel.getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    movies.clear()
                    movies.addAll(it)
                    moviesAdapter.notifyDataSetChanged()
                }, {
                    it.printStackTrace()
                    Toast.makeText(this@MovieListActivity, "Error: ${it.message}", Toast.LENGTH_SHORT)
                            .show()
                    // TODO ERROR HANDLING
                })
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
