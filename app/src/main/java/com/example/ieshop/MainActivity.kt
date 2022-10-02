package com.example.ieshop

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.authentication.fragment.ConfirmationFragment
import com.example.authentication.fragment.LoginFragment
import com.example.authentication.fragment.OnboardingFragment
import com.example.authentication.fragment.RegistrateFragment
import com.example.ieshop.databinding.ActivityMainBinding
import com.example.ieshop.di.MyApplication
import com.example.ieshop.framework.repository.shopRepository
import com.example.main.HomeFragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar
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
            val a = 0
            if (user != null) {
                view.bottomMenu.visibility = View.VISIBLE
                view.burger.visibility = View.VISIBLE
            }
        }
        setContentView(view.root)
    }



}
