package com.example.mytaxi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mytaxi.data.dao.LocationDao
import com.example.mytaxi.data.model.LocationEntity

@Database(entities = [LocationEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
}
