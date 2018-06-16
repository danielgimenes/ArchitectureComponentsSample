package com.dgimenes.architecturesample.movieslist

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.dgimenes.architecturesample.R
import com.dgimenes.architecturesample.android.ItemOffsetDecoration
import com.dgimenes.architecturesample.data.model.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity() {

    private lateinit var moviesAdapter: MoviesAdapter

    private lateinit var movieListViewModel: MovieListViewModel

    private val disposables by lazy { CompositeDisposable() }

    private val movies = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        movieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)
        movieListViewModel.init()
        setupMoviesListUI()

        disposables.add(
                movieListViewModel.loadPopularMovies()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ movies ->
                            renderMovies(movies)
                        }, { error ->
                            error.printStackTrace()
                            showError(error)
                        })

        )
    }

    private fun showError(error: Throwable?) {
        // TODO BETTER ERROR HANDLING
        Toast.makeText(this@MovieListActivity, "Error: ${error?.message}", Toast.LENGTH_SHORT)
                .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
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
