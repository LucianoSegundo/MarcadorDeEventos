package com.example.marcadoreventos2.viewer.paginas

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.marcadoreventos2.model.MainViewModel
import com.example.marcadoreventos2.ui.nav.BottomNavBar
import com.example.marcadoreventos2.ui.nav.BottomNavItem
import com.example.marcadoreventos2.ui.nav.Route
import com.example.marcadoreventos2.ui.theme.corTextoTopBar
import com.example.marcadoreventos2.ui.theme.corTopBar
import com.example.marcadoreventos2.ui.theme.fundo
import com.example.marcadoreventos2.viewer.componentes.listarEventos

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun tMarcados(navController: NavController, viewModel: MainViewModel) {

    val listaEventos = viewModel.ColetarListaEventos

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = corTopBar,
                    scrolledContainerColor = corTopBar,
                    navigationIconContentColor = corTextoTopBar,
                    titleContentColor = corTextoTopBar,
                    actionIconContentColor = corTextoTopBar,
                ),
                title = {
                    Text(
                        text = "Eventos Marcados",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White)
                }

            )
        },
        bottomBar = {
            val items = listOf(
                BottomNavItem.navMeus,
                BottomNavItem.navDisponiveis,
                BottomNavItem.navMarcados,
            )
            BottomNavBar(navController = navController, items)
        },
        floatingActionButton =  {
            FloatingActionButton(onClick = {navController.navigate(Route.tCriacao) }) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar")
            }
        },
        containerColor = fundo

    ){innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(listaEventos) { evento ->
                listarEventos(evento = evento, onClose = {
                    viewModel.CancelarEvento(evento)

                }, onClick = {
                    viewModel.setEventoManipulado(evento)
                    navController.navigate(Route.tLeitura)
                })
            }
        }
    }
}
