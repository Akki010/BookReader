package com.example.bookreader.screens.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bookreader.ReaderApplication
import com.example.bookreader.navigation.ReaderScreens

@Composable
fun LoginScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ReaderLoginScreen(navController)
    }
}

@Composable
fun ReaderLoginScreen(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Book Reader",
            style = MaterialTheme.typography.displayLarge,
            color = Color(0xFFBB4C72)
        )
        Spacer(modifier = Modifier.height(32.dp))
        EmailPasswordRow()
        NewUserRow() {
            navController.navigate(ReaderScreens.SignupScreen.name)
        }
    }
}

@Composable
fun NewUserRow(signUpClick: () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "New User? ",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Sign up",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0088F5),
            modifier = Modifier.clickable { signUpClick() }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailPasswordRow() {
    var emailText by rememberSaveable {
        mutableStateOf("")
    }
    var pswdText by rememberSaveable {
        mutableStateOf("")
    }
    var showPassword by remember { mutableStateOf(false) }
    var isValid by remember { mutableStateOf(false) }

    if(isValid)
        Toast.makeText(LocalContext.current, "jhs", Toast.LENGTH_SHORT).show()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = emailText, onValueChange = {
                emailText = it
            },
            label = { Text(text = "Email") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = false,
            enabled = true,
            textStyle = TextStyle(
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "email")
            },
            keyboardActions = KeyboardActions.Default,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        TextField(
            value = pswdText, onValueChange = {
                pswdText = it
            },
            label = { Text(text = "Password") },
            textStyle = TextStyle(
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "password")
            },
            trailingIcon = {
                if (showPassword)
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "show password",
                        modifier = Modifier.clickable {
                            showPassword = !showPassword
                        }
                    )
                else
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "don't show password",
                        modifier = Modifier.clickable {
                            showPassword = !showPassword
                        }
                    )
            },
            visualTransformation = if (showPassword)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            keyboardActions = KeyboardActions.Default,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
        Spacer(
            modifier = Modifier
                .height(16.dp)
        )
        Button(onClick = {
            isValid = onDone(emailText.trim(), pswdText.trim())
        }, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

}

fun onDone(email: String, pswd: String): Boolean {
    val emailPattern = Regex("[a-zA-Z0–9._-]+@[a-z]+\\.+[a-z]+")
    val passwordPattern = Regex("^(?=.*[0–9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")
    return emailPattern.matches(email) && passwordPattern.matches(pswd)
}
