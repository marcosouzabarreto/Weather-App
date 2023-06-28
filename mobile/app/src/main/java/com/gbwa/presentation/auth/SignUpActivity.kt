package com.gbwa.presentation.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gbwa.presentation.home.WeatherActivity
import com.gbwa.presentation.ui.theme.DarkBlue
import com.gbwa.presentation.ui.theme.DeepBlue
import com.gbwa.presentation.ui.theme.WeatherAppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    private val viewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContent {
            WeatherAppTheme {
                SignUpScreen(
                    auth,
                    handleSignUpSuccessful = ::handleSignUpSuccessful,
                    viewModel = viewModel
                )
            }
        }
    }

    private fun handleSignUpSuccessful() {
        val intent = Intent(this, WeatherActivity::class.java)
        startActivity(intent)
    }

}

@Composable
fun SignUpScreen(auth: FirebaseAuth, handleSignUpSuccessful: () -> Unit, viewModel: AuthViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    MaterialTheme(
        colors = MaterialTheme.colors.copy(
            primary = Color.White,
            onPrimary = Color.White
        )
    ) {
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

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    textStyle = TextStyle(color = Color.White),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = "Toggle password visibility",
                                tint = if (passwordVisibility) Color.White else LocalContentColor.current
                            )
                        }
                    },
                    textStyle = TextStyle(color = Color.White),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirm Password") },
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = "Toggle password visibility",
                                tint = if (passwordVisibility) Color.White else LocalContentColor.current
                            )
                        }
                    },
                    textStyle = TextStyle(color = Color.White),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                viewModel.state.error?.let {
                    Text(
                        text = it,
                        color = Color.Red,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }


                Button(
                    onClick = {
                        viewModel.signUp(
                            auth,
                            email,
                            password,
                            handleSignUpSuccessful,
                        )
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = DeepBlue)
                ) {
                    Text(text = "Sign Up", color = Color.White)
                }
            }
        }
    }
}
