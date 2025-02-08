package com.example.marcadoreventos2.ui.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object tLogin : Route
    @Serializable
    data object tCadastro : Route
    @Serializable
    data object tHome : Route
    @Serializable
    data object tLeitura : Route
    @Serializable
    data object tCriacao : Route
    @Serializable
    data object tMarcados : Route
    @Serializable
    data object tDisponiveis : Route
}

sealed class BottomNavItem(
    var title: String,
    var icon: ImageVector,
    var route: Route)
{
    data object navMeus :
        BottomNavItem("Meus Eventos", Icons.Default.Home, Route.tHome)
    data object navDisponiveis :
        BottomNavItem("Eventos Abertos", Icons.Default.LocationOn, Route.tDisponiveis)
    data object navMarcados :
        BottomNavItem("Eventos Marcados", Icons.Default.CheckCircle, Route.tMarcados)
}