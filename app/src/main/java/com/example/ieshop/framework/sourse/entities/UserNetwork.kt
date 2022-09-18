package com.example.ieshop.framework.sourse.entities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class UserNetwork (
    @field:SerializedName("Name")  val name: String,
    @field:SerializedName("Surname")  val surname: String,
    @field:SerializedName("Email")  val email: String,
    @field:SerializedName("Login") val login: String,
    @field:SerializedName("Photo") val photo: String,
    @field:SerializedName("Telephone")  val telephone: String,
    @field:SerializedName("Password") val password: String,
    @field:SerializedName("Country")  val country: String
    )