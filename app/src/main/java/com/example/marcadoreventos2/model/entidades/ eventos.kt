package com.example.marcadoreventos.model.entidades

import com.example.marcadoreventos2.model.entidades.User
import com.google.android.gms.maps.model.LatLng
import java.time.LocalDate
import java.util.Date

data class eventos(
    val nomeEvento : String,
    val autor: User? = null,

    val estadoEvento: String? = null,
    val cidadeEvento: String? = null,

    val numVagas: Int? = null,
    val numeroConfirmacoes: Int? = null,

    val inicio: String? = null,
    val termino: String? = null,

    val descricao: String? = null,

    val location: LatLng? = null
)

