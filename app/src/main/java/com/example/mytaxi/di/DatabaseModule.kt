package com.example.mytaxi.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.mytaxi.data.local.AppDatabase
import com.example.mytaxi.data.dao.LocationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun providesContext(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "myTaxi_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideLocationDao(database: AppDatabase): LocationDao {
        return database.locationDao()
    }
}
