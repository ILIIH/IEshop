package com.example.ieshop

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.basket.OrdersFragment
import com.example.ieshop.databinding.ActivityMainBinding
import com.example.ieshop.di.MyApplication
import com.example.ieshop.framework.repository.shopRepository
import com.example.main.HomeFragment
import com.example.profile.ProfileFragment
import com.example.search.TextSearchFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repo: shopRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ActivityMainBinding.inflate(layoutInflater)

        (applicationContext as MyApplication).appComponent.injectMainActivity(this)

        view.bottomMenu.visibility = View.GONE
        view.topNavIcon.visibility = View.GONE
        view.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        repo._curUser.observe(this) { user ->
            if (user != null) {
                view.bottomMenu.visibility = View.VISIBLE
                view.topNavIcon.visibility = View.VISIBLE
                view.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            }
        }

        view.topNavIcon.setOnClickListener {
            view.drawerLayout.openDrawer(GravityCompat.START)
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

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(view.navView, navController)
        setContentView(view.root)
    }
}
