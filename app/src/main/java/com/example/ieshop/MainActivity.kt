package com.example.ieshop

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.ieshop.databinding.ActivityMainBinding
import com.example.ieshop.di.MyApplication
import com.example.ieshop.framework.repository.shopRepository
import androidx.navigation.ui.setupWithNavController
import com.example.basket.OrdersFragment
import com.example.main.HomeFragment
import com.example.profile.ProfileFragment
import com.example.search.TextSearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repo: shopRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ActivityMainBinding.inflate(layoutInflater)

        (applicationContext as MyApplication).appComponent.injectMainActivity(this)

        view.bottomMenu.visibility = View.GONE
        view.burger.visibility = View.GONE

        repo._curUser.observe(this) { user ->
            if (user != null) {
                view.bottomMenu.visibility = View.VISIBLE
                view.burger.visibility = View.VISIBLE
            }
        }

        view.bottomMenu.setOnItemSelectedListener {
            when (it) {

                R.id.main_navigation -> {
                    val homeFragment = HomeFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, homeFragment).commit()
                }
                R.id.basket_nav_graph -> {
                    val ordersFragment = OrdersFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, ordersFragment).commit()

                }
                R.id.search_nav_garph -> {
                        val searchFragment = TextSearchFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, searchFragment).commit()
                    }
                R.id.profile_nav_graph -> {
                    val profileFragment = ProfileFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, profileFragment).commit()
                }
            }
        }

        setContentView(view.root)
    }



}
