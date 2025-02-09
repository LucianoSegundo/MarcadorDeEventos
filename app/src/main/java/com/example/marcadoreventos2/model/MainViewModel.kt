package com.example.marcadoreventos2.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marcadoreventos.model.entidades.eventos
import com.example.marcadoreventos2.model.entidades.FBDatabase
import com.example.marcadoreventos2.model.entidades.User
import com.google.android.gms.maps.model.LatLng

class MainViewModel (private val db: FBDatabase): ViewModel(),
    FBDatabase.Listener {

    private val _EventosColetados = mutableStateListOf<eventos>()
    val ColetarListaEventos  get() = _EventosColetados.toList()

    private var _EventoSelecionado = mutableStateOf<eventos?> (null)
    val EventoManuseado: eventos?  get() = _EventoSelecionado.value

    private val _localizacaoSelecionada = mutableStateOf<LatLng?> (null)

    private val _user = mutableStateOf<User?> (null)
    val user : User? get() = _user.value

    val localizacaoSelecionada: LatLng?  get() = _localizacaoSelecionada.value


    init {
        db.setListener(this)
    }
    // manipulação de variaveis

    fun setEventoManipulado(evento: eventos) {
        _EventoSelecionado.value = evento
    }

    fun setlocalizacaoSelecionada(latlang: LatLng) {
        _localizacaoSelecionada.value = latlang
    }

    // manipulação de lista

    fun CancelarEvento(evento: eventos) {
        db.remove(evento)
    }

    fun addEvento(evento: eventos) {
        db.add(evento)
    }

    override fun onUserLoaded(user: User) {
        _user.value = user    }

    override fun onEventoAdded(evento: eventos) {
        _EventosColetados.add(evento)
    }

    override fun onEventoUpdate(evento: eventos) {
        TODO("Not yet implemented")
    }

    override fun onEventoRemoved(evento: eventos) {
        _EventosColetados.remove(evento)
    }
}

class MainViewModelFactory(private val db : FBDatabase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
