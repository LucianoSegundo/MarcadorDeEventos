package com.example.marcadoreventos2.viewer.paginas

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@SuppressLint("ContextCastToActivity")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun tDisponiveis(navController: NavController, viewModel: MainViewModel) {

    val listaEventos = viewModel.ColetarListaEventos
    val activity = LocalContext.current as? Activity


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
                        text = "Eventos Disponiveis",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White)
                },
                actions = {
                    IconButton( onClick = {
                        Firebase.auth.signOut()
                        activity?.finish()
                    } ) {
                        Icon(
                            imageVector =
                            Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Localized description"
                        )
                    }
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
       containerColor = fundo

    ){innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
           val user = viewModel.user;
            items(listaEventos) { evento ->
                if(evento.autor?.equals(user) == false){
                    val usuario  = evento?.participantes?.find { it.name  == user?.name && it.email  == user?.email  }

                    if (usuario == null)
                    listarEventos(evento = evento, onClose = {

                }, onClick = {
                    viewModel.setEventoManipulado(evento)
                    navController.navigate(Route.tLeitura)
                })}
            }
        }
    }
}
