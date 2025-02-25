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
import android.Manifest
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButtonDefaults
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ContextCastToActivity",
    "SuspiciousIndentation"
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun tHomeMapa(navController: NavController, viewModel: MainViewModel, launcher: ManagedActivityResultLauncher<String, Boolean>){

    val listaEventos = viewModel.ColetarListaEventos
    val activity = LocalContext.current as? Activity
    val camPosState = rememberCameraPositionState ()


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
                    val nome = viewModel.user?.name?:"[não logado]"

                    Text(
                        text = "Bem vindo " + nome,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White)
                },
                actions = {
                    IconButton( onClick = {
                        Firebase.auth.signOut()
                        activity?.finish() }
                    ) {
                        Icon(
                            imageVector =
                            Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Localized description"
                        )
                    }
                }


            )
        },
        floatingActionButton =  {
            ExtendedFloatingActionButton(
                onClick = { navController.navigate(Route.tHome) },
                icon = { Icon(Icons.Default.Home, contentDescription = "aplicação em modo lista") },
                text =  { Text("Modo Lista") },
                containerColor = corTopBar,
                contentColor = corTextoTopBar

                )
        },
        floatingActionButtonPosition = FabPosition.Center,
        containerColor = fundo

    ){innerpadding ->
        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)

        GoogleMap(
            modifier = Modifier.fillMaxSize().padding(innerpadding),
            cameraPositionState = camPosState,
            onMapClick = {
                viewModel.setlocalizacaoSelecionada(it)
                navController.navigate(Route.tCriacao)
            }

        ) {
            var user = viewModel.user;
            viewModel.ColetarListaEventos.forEach{
                if(it.location != null){
                    var nv = it.numVagas!!
                    var nc = it.numeroConfirmacoes!!
                    var icone: BitmapDescriptor
                    var autor = it.autor
                    val usuario  = it?.participantes?.find { it.name  == user?.name && it.email  == user?.email  }

                    if(autor?.equals(user) == true) {
                            if (nc == nv) {
                                icone = BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_BLUE
                                )
                            }
                            else{
                                icone = BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_AZURE)
                            }
                        }
                        else if(usuario != null){
                        icone = BitmapDescriptorFactory.defaultMarker(
                            BitmapDescriptorFactory.HUE_GREEN)
                        }
                        else if(nc == nv){
                            icone = BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_RED)
                        }
                        else if( nc > nv -3 &&  nc != nv){
                            icone = BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_ORANGE)
                        }
                        else{
                        icone = BitmapDescriptorFactory.defaultMarker(
                            BitmapDescriptorFactory.HUE_YELLOW)
                        }



                    var evento = it;
                    Marker(
                        state = MarkerState(position = it.location),
                        title = it.nomeEvento,
                        snippet = "Clique aqui para mais detalhes" ,
                        icon = icone,
                        onInfoWindowClick = {
                        viewModel.setEventoManipulado(evento)
                        navController.navigate(Route.tLeitura)
                    }

                    )
                }

            }
        }

    }
}