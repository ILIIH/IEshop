package com.example.ieshop

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.authentication.di.AuthDepsProvider
import com.example.authentication.di.authDeps
import com.example.ieshop.di.AppComponent
import com.example.ieshop.di.DaggerAppComponent
import com.example.ieshop.di.MyApplication

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}
