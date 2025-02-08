package com.example.marcadoreventos2.model

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.marcadoreventos.model.entidades.eventos

class MainViewModel: ViewModel() {
    private val _EventosColetados = getEventos().toMutableStateList()
    private var _EventoSelecionado: eventos? = null;

    val ColetarListaEventos  get() = _EventosColetados.toList()
    val EventoManuseado  get() = _EventoSelecionado

    fun setEventoManipulado(evento: eventos) {
        _EventoSelecionado = evento
    }
    fun CancelarEvento(evento: eventos) {
        _EventosColetados.remove(evento)
    }

    fun addEvento(evento: eventos) {
        _EventosColetados.add(evento)
    }
}

fun getEventos() = List(20) { i ->
    eventos(nomeEvento = "ir para praia dia $i", autor = "caio", estadoEvento = "Pernambuco", cidadeEvento = "jaboatão dos guararapes", numVagas = i*5, numeroConfirmacoes = 0, descricao = "ir na praia e bater uma bolinha por "+ i*3+" horas.", inicio = "25/05/2025", termino = "12/12/2025")
}