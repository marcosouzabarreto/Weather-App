package com.gbwa.presentation.settings

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gbwa.presentation.landing.LandingActivity
import com.gbwa.presentation.ui.theme.DarkBlue
import com.gbwa.presentation.ui.theme.WeatherAppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SettingsActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    private fun navigateToLanding() {
        val intent = Intent(this, LandingActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContent {
            WeatherAppTheme {
                SettingsScreen { action ->
                    when (action) {
                        is SettingsAction.Logout -> {
                            auth.signOut()
                            navigateToLanding()
                            finish()
                        }
                    }
                }
            }
        }
    }
}

sealed class SettingsAction {
    object Logout : SettingsAction()
}

@Composable
fun SettingsScreen(onAction: (SettingsAction) -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))
        Card(
            modifier = Modifier
                .clickable { onAction(SettingsAction.Logout) }
                .padding(16.dp),
            backgroundColor = DarkBlue
        ) {
            Text(
                text = "Sair da Conta",
                modifier = Modifier.padding(16.dp),
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}
