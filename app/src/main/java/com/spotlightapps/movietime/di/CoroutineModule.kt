package com.spotlightapps.movietime.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 * Created by Ahmad Jawid Muhammadi
 * on 19-01-2022.
 */

@InstallIn(SingletonComponent::class)
@Module
class CoroutineModule {

    @Provides
    @Singleton
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}