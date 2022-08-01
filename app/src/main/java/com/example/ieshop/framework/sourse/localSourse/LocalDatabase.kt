package com.example.ieshop.framework.sourse.localSourse

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ieshop.framework.sourse.entities.Product
import com.example.ieshop.framework.sourse.entities.Purchases
import com.example.ieshop.framework.sourse.entities.User

@Database(entities = [Purchases::class, User::class, Product::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun userDao(): ShopDAO
}
