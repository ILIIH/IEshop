package com.example.ieshop.framework.sourse.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @ColumnInfo(name = "Name") val name: String,
    @ColumnInfo(name = "Surname") val surname: String,
    @ColumnInfo(name = "Login") @PrimaryKey val login: String,
    @ColumnInfo(name = "Photo") val photo: String?,
    @ColumnInfo(name = "Telephone") val telephone: String,
    @ColumnInfo(name = "Password") val password: String
)
