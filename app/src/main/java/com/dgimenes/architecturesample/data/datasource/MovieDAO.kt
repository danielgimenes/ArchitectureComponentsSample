package com.dgimenes.architecturesample.data.datasource

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.dgimenes.architecturesample.data.model.Movie
import io.reactivex.Single

@Dao
interface MovieDAO {

    @Query("SELECT * FROM Movies")
    fun getMovies(): Single<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovies(movies: List<Movie>)

}