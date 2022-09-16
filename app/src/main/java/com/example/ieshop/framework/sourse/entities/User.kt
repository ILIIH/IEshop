package com.example.ieshop.framework.sourse.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(
    @SerializedName("Name") @ColumnInfo(name = "Name") val name: String,
    @SerializedName("Surname") @ColumnInfo(name = "Surname") val surname: String,
    @SerializedName("Email") @ColumnInfo(name = "Email") val email: String,
    @SerializedName("Login") @ColumnInfo(name = "Login") @PrimaryKey val login: String,
    @SerializedName("Photo") @ColumnInfo(name = "Photo") val photo: String,
    @SerializedName("Telephone") @ColumnInfo(name = "Telephone") val telephone: String,
    @SerializedName("Password") @ColumnInfo(name = "Password") val password: String,
    @SerializedName("Country") @ColumnInfo(name = "Country") val country: String
)
