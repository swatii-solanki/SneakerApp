package com.crickbuzztest.data.module

import android.app.Application
import android.content.Context
import com.crickbuzztest.data.datasource.local.LocalDataSource
import com.crickbuzztest.data.datasource.local.LocalDataSourceImpl
import com.crickbuzztest.data.datasource.remote.RemoteDataSource
import com.crickbuzztest.data.datasource.remote.RemoteDataSourceImpl
import com.crickbuzztest.data.datasource.repo.SneakerRepo
import com.crickbuzztest.data.datasource.repo.SneakerRepoImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun getSneakersRepository(source: SneakerRepoImpl): SneakerRepo

    @Binds
    @Singleton
    abstract fun getLocalSource(source: LocalDataSourceImpl): LocalDataSource

    @Binds
    @Singleton
    abstract fun getRemoteSource(source: RemoteDataSourceImpl): RemoteDataSource

    companion object {
        @Singleton
        @Provides
        fun provideContext(application: Application): Context = application.applicationContext
    }
}