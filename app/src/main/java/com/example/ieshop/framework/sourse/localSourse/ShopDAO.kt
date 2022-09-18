package com.example.ieshop.framework.sourse.localSourse

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ieshop.framework.sourse.entities.Product
import com.example.ieshop.framework.sourse.entities.Purchases
import com.example.ieshop.framework.sourse.entities.UserDatabase

@Dao
interface ShopDAO {
    @Query("SELECT * FROM Product LIMIT :page, :per_page")
    fun getProductPerPage(page: Int, per_page: Int): List<Product>

    @Query("SELECT * FROM UserDatabase WHERE Login = :log")
    fun getUserInfo(log: String): List<UserDatabase>

    @Query("SELECT * FROM Purchases WHERE ownerLogin = :login")
    fun getPurchases(login: String): List<Purchases>

    @Query("SELECT COUNT(*) FROM UserDatabase WHERE Login = :login AND Password = :password")
    fun login(login: String, password: String): Int

    @Query("SELECT COUNT(:login) FROM UserDatabase WHERE Login = :login ")
    fun countOfUser(login: String): Int

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun registrate(user: UserDatabase)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPurchases(purchases: Purchases)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(product: Product)

    @Delete
    fun deleteProduct(product: Product)

    @Update
    fun changeUserInfo(user: UserDatabase)
}
