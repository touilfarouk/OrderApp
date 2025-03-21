package com.farouktouil.farouktouil.core.di

import android.content.Context
import androidx.room.Room
import com.farouktouil.farouktouil.core.data.local.AppDatabase
import com.farouktouil.farouktouil.core.data.local.DelivererDao
import com.farouktouil.farouktouil.core.data.local.OrderDao
import com.farouktouil.farouktouil.core.data.local.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideOrderDao(orderDatabase: AppDatabase):OrderDao{
        return orderDatabase.orderDao()
    }

    @Provides
    @Singleton
    fun provideDelivererDao(orderDatabase: AppDatabase):DelivererDao{
        return orderDatabase.delivererDao()
    }

    @Provides
    @Singleton
    fun provideProductDao(orderDatabase: AppDatabase):ProductDao{
        return orderDatabase.productDao()
    }



}