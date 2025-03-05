package com.dsoft.mynewtestapp.data.di

import android.content.Context
import androidx.room.Room
import com.dsoft.mynewtestapp.data.db.AppDatabase
import com.dsoft.mynewtestapp.data.db.ItemDao
import com.dsoft.mynewtestapp.data.repository.RepositoryImpl
import com.dsoft.mynewtestapp.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "data.db"

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            DATABASE_NAME
        )
            .createFromAsset(DATABASE_NAME)
            .build()


    @Singleton
    @Provides
    fun provideDao(db: AppDatabase) = db.itemDao()

    @Singleton
    @Provides
    fun provideRepository(dao: ItemDao): Repository {
        return RepositoryImpl(dao)
    }
}