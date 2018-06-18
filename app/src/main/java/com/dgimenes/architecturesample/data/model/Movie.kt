package com.dgimenes.architecturesample.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
        @PrimaryKey @ColumnInfo(name = "id") val id: Int,
        @ColumnInfo(name = "title") val title: String
)