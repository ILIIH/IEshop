package com.example.ieshop.framework.sourse.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class UserDatabase(
   @ColumnInfo(name = "Name") val name: String,
   @ColumnInfo(name = "Surname") val surname: String,
   @ColumnInfo(name = "Email") val email: String,
   @ColumnInfo(name = "Login") @PrimaryKey val login: String,
   @ColumnInfo(name = "Photo") val photo: String,
   @ColumnInfo(name = "Telephone") val telephone: String,
   @ColumnInfo(name = "Password") val password: String,
   @ColumnInfo(name = "Country") val country: String
)
