package com.example.ecommercecase.di

import android.content.Context
import androidx.room.Room
import com.example.ecommercecase.db.CartListItemDao
import com.example.ecommercecase.db.ProductsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): ProductsDatabase {
        return Room.databaseBuilder(
            appContext,
            ProductsDatabase::class.java,
            "Products"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideProductListDao(database: ProductsDatabase) = database.productListDao

    @Provides
    fun provideCartListItemDao(database: ProductsDatabase): CartListItemDao {
        return database.cartListItemDao
    }
}