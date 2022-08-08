package com.example.ieshop.framework.sourse.remoteSourse

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

    @GET("/product")
    fun getProductPerPage(@Query("page") page: Int, @Query("per_page") per_page: Int): Response<List<Product>>

    @GET("/{login}/user/info")
    fun getUserInfo(@Path("login",) login: String): Response<List<User>>

    @GET("/{login}/user/purchase")
    fun getPurchases(@Path("login",) login: String): Response<List<Purchases>>

    @POST("/login")
    fun login(@Body login: String, @Body password: String): Response<Boolean>

    @POST("/registrate")
    fun registrate(
        @Body username: String,
        @Body name: String,
        @Body surname: String,
        @Body login: String,
        @Body photo: String?,
        @Body telephone: String,
        @Body password: String
    ): Response<Boolean>

    @POST("/add/purchases")
    fun addPurchases(
        @Body name: String,
        @Body cost: Int,
        @Body type: String,
        @Body buyData: String,
        @Body photos: List<String>,
        @Body ownerLogin: String
    ): Response<Boolean>

    @POST("/add/product")
    fun addProduct(
        @Body name: String,
        @Body cost: Int,
        @Body type: String,
        @Body publishData: String,
        @Body photos: List<String>,
        @Body ownerLogin: String
    ): Response<Boolean>

    @DELETE("/delete/product")
    fun deleteProduct(
        @Body name: String,
        @Body cost: Int,
        @Body type: String,
        @Body publishData: String,
        @Body photos: List<String>,
        @Body ownerLogin: String
    ): Response<Boolean>

    @PUT("/user/change")
    fun changeUserInfo(
        @Body username: String,
        @Body name: String,
        @Body surname: String,
        @Body login: String,
        @Body photo: String?,
        @Body telephone: String,
        @Body password: String
    ): Response<Boolean>
}
