package com.example.marcadoreventos2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marcadoreventos2.model.MainViewModel
import com.example.marcadoreventos2.ui.nav.Route
import com.example.marcadoreventos2.ui.theme.MarcadorEventos2Theme
import com.example.marcadoreventos2.viewer.paginas.tCadastro
import com.example.marcadoreventos2.viewer.paginas.tCriacao
import com.example.marcadoreventos2.viewer.paginas.tDisponiveis
import com.example.marcadoreventos2.viewer.paginas.tHome
import com.example.marcadoreventos2.viewer.paginas.tLeitura
import com.example.marcadoreventos2.viewer.paginas.tLogin
import com.example.marcadoreventos2.viewer.paginas.tMarcados

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarcadorEventos2Theme {
            val viewModel : MainViewModel by viewModels()
            val navController = rememberNavController()

            NavHost(navController, startDestination = Route.tLogin) {
                composable<Route.tLogin>{ tLogin(navController,viewModel) }
                composable<Route.tCadastro>{ tCadastro(navController, viewModel) }
                composable<Route.tHome>{ tHome(navController, viewModel) }
                composable<Route.tCriacao>{ tCriacao(navController, viewModel) }
                composable<Route.tLeitura>{ tLeitura(navController, viewModel) }
                composable<Route.tMarcados>{ tMarcados(navController, viewModel) }
                composable<Route.tDisponiveis>{ tDisponiveis(navController, viewModel) }

            }
           }
        }
    }
}
