package com.henrasn.tvpulse.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val image: String,
    val rate: Float,
    val genre: String
)
