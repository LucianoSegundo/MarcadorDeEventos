package com.example.marcadoreventos2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
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
import androidx.navigation.NavDestination.Companion.hasRoute
import com.example.marcadoreventos2.model.MainViewModelFactory
import com.example.marcadoreventos2.model.entidades.FBDatabase
import com.example.marcadoreventos2.viewer.paginas.tHomeMapa


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarcadorEventos2Theme {
                val fbDB = remember { FBDatabase() }
                val viewModel : MainViewModel = viewModel(
                    factory = MainViewModelFactory(fbDB)
                )

                val navController = rememberNavController()
                val currentRoute = navController.currentBackStackEntryAsState()
                val showButton = currentRoute.value?.destination?.hasRoute(Route.tHomeMapa::class)?:false
                val launcher = rememberLauncherForActivityResult(contract =
                ActivityResultContracts.RequestPermission(), onResult = {} )

            NavHost(navController, startDestination = Route.tHomeMapa) {
                composable<Route.tHomeMapa>{ tHomeMapa(navController, viewModel,launcher) }
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
