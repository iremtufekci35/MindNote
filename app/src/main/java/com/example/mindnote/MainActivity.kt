package com.example.mindnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.mindnote.ui.screens.LoginScreen
import com.example.mindnote.ui.screens.RegisterScreen
import com.example.mindnote.ui.theme.MindNoteTheme
import com.example.mindnote.ui.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.composable
import com.example.mindnote.ui.screens.NotesScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MindNoteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val isUserLoggedIn by authViewModel.isUserLoggedIn.collectAsState()

    LaunchedEffect(isUserLoggedIn) {
        if (isUserLoggedIn) {
            navController.navigate("notes") {
                popUpTo(0) { inclusive = true }
            }
        } else {
            navController.navigate("login") {
                popUpTo(0) { inclusive = true }
            }
        }
    }

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                authViewModel = authViewModel,
                onLoginSuccess = { navController.navigate("notes") { popUpTo("login") { inclusive = true } } },
                onNavigateRegister = { navController.navigate("signup") },
                navController = navController
            )
        }
        composable("signup") {
            RegisterScreen(
                authViewModel = authViewModel,
                onRegisterSuccess = { navController.navigate("notes") { popUpTo("signup") { inclusive = true } } },
                navController = navController
            )
        }
        composable("notes") {
            NotesScreen(
                notesList = listOf(
                    "İlk Not",
                    "Alışveriş Listesi",
                    "Yapılacaklar"
                ),
                onAddNote = {
                    /** ADD NOTE **/
                },
                onNoteClick = { note ->
                    /** NOTE CLIKCING **/
                }
            )
        }
    }
}

