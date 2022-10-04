package com.example.ieshop.framework.sourse.remoteSourse

import com.example.ieshop.framework.sourse.entities.ProductDatabase
import com.example.ieshop.framework.sourse.entities.UserDatabase
import com.example.ieshop.framework.sourse.entities.UserNetwork
import retrofit2.Response
import retrofit2.http.*

interface ShopService {
    @GET("/api/sendEmail/{email}/{code}")
    suspend fun authorizeEmail(@Path("email") email:String,@Path("code") code:String): Response<Boolean>

    @GET("/api/get/user/by/login/")
    fun getUserInfo(@Query("login") login: String): Response<List<UserDatabase>>

    @GET("/api/get/all/product")
    fun getAllProduct(): Response<List<UserDatabase>>


    @FormUrlEncoded
    @POST("/api/login/")
    suspend fun login(@Field("Login") login: String, @Field("Password") password: String): Response<List<UserNetwork>>

    @FormUrlEncoded
    @POST("/api/registrate/user")
    suspend fun registrate(
        @Field("Name") name:String,
        @Field("Surname") surname: String,
        @Field("Email") email:String,
        @Field("Login") login:String,
        @Field("Photo") photo:String,
        @Field("Telephone") telephone:String,
        @Field("Password") pass:String,
        @Field("Country") country:String,
    ): Response<UserNetwork>

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
    fun getProductPerPage(@Query("page") page: Int, @Query("per_page") per_page: Int): Response<List<ProductDatabase>>



    @POST("/{login}/user/purchase")
    fun countOfUser(@Body login: String): Response<List<ProductDatabase>>

    @GET("/{login}/user/purchase")
    fun getPurchases(@Path("login") login: String): Response<List<ProductDatabase>>
}
