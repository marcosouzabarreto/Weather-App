package com.gbwa.presentation.landing

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gbwa.presentation.auth.LoginActivity
import com.gbwa.presentation.auth.SignUpActivity
import com.gbwa.presentation.settings.SettingsAction
import com.gbwa.presentation.settings.SettingsScreen
import com.gbwa.presentation.ui.theme.DarkBlue
import com.gbwa.presentation.ui.theme.DeepBlue
import com.gbwa.presentation.ui.theme.WeatherAppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.reflect.KClass

class LandingActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    private fun navigateTo(activity: KClass<out ComponentActivity>) {
        val intent = Intent(this, activity.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContent {
            LandingPage(navigateTo = ::navigateTo)
        }
    }
}

@Composable
fun LandingPage(navigateTo: (KClass<out ComponentActivity>) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "GBWA",
                color = Color.White,
                fontSize = 60.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = "Geolocation Based Weather App",
                color = Color.White,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(200.dp))

            Button(
                onClick = { navigateTo(LoginActivity::class) },
                colors = ButtonDefaults.buttonColors(backgroundColor = DeepBlue)
            ) {
                Text(text = "Login", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navigateTo(SignUpActivity::class) },
                colors = ButtonDefaults.buttonColors(backgroundColor = DeepBlue)
            ) {
                Text(text = "Sign Up", color = Color.White)
            }
        }
    }
}

