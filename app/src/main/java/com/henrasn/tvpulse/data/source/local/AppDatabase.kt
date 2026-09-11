package com.henrasn.tvpulse.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.henrasn.tvpulse.data.model.entity.MovieEntity
import com.henrasn.tvpulse.data.source.local.dao.MovieDao

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        const val DATABASE_NAME = "tvpulse_db"
    }
}
