package com.example.mytaxi.di

import com.example.mytaxi.data.LocationRepositoryImpl
import com.example.mytaxi.domain.LocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CreateChatModule {

    @Binds
    abstract fun bindRepository(repository: LocationRepositoryImpl): LocationRepository
}
