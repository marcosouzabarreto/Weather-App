package com.gbwa.presentation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.gbwa.presentation.landing.LandingPage
import kotlin.reflect.KClass

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LandingPage(navigateTo = ::navigateTo)
        }
    }


    private fun navigateTo(
        activity
        : KClass<out ComponentActivity>
    ) {
        val intent = Intent(this, activity.java)
        startActivity(intent)
    }
}

