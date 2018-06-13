package com.dgimenes.architecturesample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity() {

    private lateinit var moviesAdapter: MoviesAdapter

    private val movies: List<Movie> = listOf(
            Movie(1, "Man Of Steel"),
            Movie(2, "Batman"),
            Movie(3, "Matrix"),
            Movie(4, "Jurassic Park"),
            Movie(4, "Ocean Eleven"),
            Movie(4, "007 Skyfall"),
            Movie(4, "Dead Pool 2"),
            Movie(4, "Forrest Gump")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        setupMoviesList()
    }

    private fun setupMoviesList() {
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
