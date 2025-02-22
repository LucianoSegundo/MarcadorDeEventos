package com.example.marcadoreventos.model.entidades

import androidx.compose.runtime.mutableStateListOf
import com.example.marcadoreventos2.model.entidades.User
import com.google.android.gms.maps.model.LatLng
import java.time.LocalDate
import java.util.ArrayList
import java.util.Date

data class eventos(
    val nomeEvento : String,
    val autor: User? = null,

    val estadoEvento: String? = null,
    val cidadeEvento: String? = null,

    val numVagas: Int? = null,
    var numeroConfirmacoes: Int? = 1,

    val inicio: String? = null,
    val termino: String? = null,

    val descricao: String? = null,

    val location: LatLng? = null,

    val participantes: MutableList<User>

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as eventos

        if (nomeEvento != other.nomeEvento) return false
        if (autor != other.autor) return false
        if (estadoEvento != other.estadoEvento) return false
        if (cidadeEvento != other.cidadeEvento) return false
        if (numVagas != other.numVagas) return false
        if (inicio != other.inicio) return false
        if (termino != other.termino) return false
        if (descricao != other.descricao) return false
        if (location != other.location) return false

        return true
    }

    override fun hashCode(): Int {
        var result = nomeEvento.hashCode()
        result = 31 * result + (autor?.hashCode() ?: 0)
        result = 31 * result + (estadoEvento?.hashCode() ?: 0)
        result = 31 * result + (cidadeEvento?.hashCode() ?: 0)
        result = 31 * result + (numVagas ?: 0)
        result = 31 * result + (inicio?.hashCode() ?: 0)
        result = 31 * result + (termino?.hashCode() ?: 0)
        result = 31 * result + (descricao?.hashCode() ?: 0)
        result = 31 * result + (location?.hashCode() ?: 0)
        return result
    }
}

