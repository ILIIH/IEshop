package com.example.ieshop.framework.sourse.remoteSourse

import com.example.core.domain.user
import com.example.ieshop.framework.sourse.entities.Product
import com.example.ieshop.framework.sourse.entities.Purchases
import com.example.ieshop.framework.sourse.entities.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ShopService {

    @GET("/api/get/user/by/login/")
    fun getUserInfo(@Query("login") login: String): Response<List<User>>

    @POST("/api/login/")
    fun login(@Body login: String, @Body password: String): Response<Boolean>

    @POST("/api/registrate/user")
    fun registrate(
        @Body name: String,
        @Body surname: String,
        @Body login: String,
        @Body photo: String?,
        @Body telephone: String,
        @Body password: String,
        @Body country: String
    ): Response<user>

    @POST("")
    fun addPurchases(
        @Body name: String,
        @Body cost: Int,
        @Body type: String,
        @Body buyData: String,
        @Body photos: List<String>,
        @Body ownerLogin: String
    ): Response<Boolean>

    @POST("")
    fun addProduct(
        @Body name: String,
        @Body cost: Int,
        @Body type: String,
        @Body publishData: String,
        @Body photos: List<String>,
        @Body ownerLogin: String
    ): Response<Boolean>

    @DELETE("")
    fun deleteProduct(
        @Body name: String,
        @Body cost: Int,
        @Body type: String,
        @Body publishData: String,
        @Body photos: List<String>,
        @Body ownerLogin: String
    ): Response<Boolean>

    @PUT("")
    fun changeUserInfo(
        @Body username: String,
        @Body name: String,
        @Body surname: String,
        @Body login: String,
        @Body photo: String?,
        @Body telephone: String,
        @Body password: String
    ): Response<Boolean>

    @GET("/product")
    fun getProductPerPage(@Query("page") page: Int, @Query("per_page") per_page: Int): Response<List<Product>>



    @POST("/{login}/user/purchase")
    fun countOfUser(@Body login: String): Response<List<Purchases>>

    @GET("/{login}/user/purchase")
    fun getPurchases(@Path("login") login: String): Response<List<Purchases>>
}
