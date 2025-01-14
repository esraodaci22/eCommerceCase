package com.example.ecommercecase

import android.app.Application
import androidx.room.Room
import com.example.ecommercecase.db.ProductsDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    lateinit var database: ProductsDatabase

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            ProductsDatabase::class.java, "Products"
        ).build()
    }

}