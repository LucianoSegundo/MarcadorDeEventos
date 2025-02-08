package com.example.marcadoreventos.model.entidades

import java.time.LocalDate
import java.util.Date

data class eventos(
    val nomeEvento : String,
    val autor: String,

    val estadoEvento: String,
    val cidadeEvento: String,

    val numVagas: Int,
    val numeroConfirmacoes: Int? =0,

    val inicio: String,
    val termino: String,

    val descricao: String? = null
)

