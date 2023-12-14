package com.example.ieshop.framework.sourse.localSourse

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.ieshop.framework.sourse.entities.ProductDatabase
import com.example.ieshop.framework.sourse.entities.UserDatabase
import com.google.gson.Gson

@Database(entities = [UserDatabase::class, ProductDatabase::class], version = 7)
@TypeConverters(Converters::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun userDao(): ShopDAO
}

class Converters {

    @TypeConverter
    fun listToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}