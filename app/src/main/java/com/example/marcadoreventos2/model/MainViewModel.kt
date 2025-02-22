package com.example.marcadoreventos2.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marcadoreventos.model.entidades.eventos
import com.example.marcadoreventos2.api.WeatherService
import com.example.marcadoreventos2.model.entidades.FBDatabase
import com.example.marcadoreventos2.model.entidades.User
import com.google.android.gms.maps.model.LatLng

class MainViewModel (private val db: FBDatabase,
                     private val service : WeatherService): ViewModel(),
    FBDatabase.Listener {

    private val _EventosColetados = mutableStateListOf<eventos>()
    val ColetarListaEventos  get() = _EventosColetados.toList()

    private var _EventoSelecionado = mutableStateOf<eventos?> (null)
    val EventoManuseado: eventos?  get() = _EventoSelecionado.value


    private val _user = mutableStateOf<User?> (null)
    val user : User? get() = _user.value

    private val _localizacaoSelecionada = mutableStateOf<LatLng?> (null)
    val localizacaoSelecionada: LatLng?  get() = _localizacaoSelecionada.value

    private val _cidadeNome = mutableStateOf ("")
    val cidadeNome: String?  get() = _cidadeNome.value

    init {
        db.setListener(this)
    }
    // manipulação de variaveis

    fun setEventoManipulado(evento: eventos) {
        _EventoSelecionado.value = evento
    }

    fun setlocalizacaoSelecionada(latlang: LatLng) {
        _localizacaoSelecionada.value = latlang

        service.getName(latlang.latitude, latlang.longitude) { name ->
            if (name != null) {
                _cidadeNome.value = name
            }
        }
    }

    // manipulação de lista

    fun CancelarEvento(evento: eventos) {
        db.remove(evento)
    }

    fun addEvento(evento: eventos) {
        db.add(evento)
    }

    fun updateEvento(evento: eventos) {
        db.update(evento)
        _EventosColetados.remove(evento)
        _EventosColetados.add(evento)
    }

    override fun onUserLoaded(user: User) {
        _user.value = user    }

    override fun onEventoAdded(evento: eventos) {
        _EventosColetados.add(evento)
    }

    override fun onEventoUpdate(evento: eventos) {
        _EventosColetados.remove(evento)
        _EventosColetados.add(evento)

    }

    override fun onEventoRemoved(evento: eventos) {
        _EventosColetados.remove(evento)
    }



}

class MainViewModelFactory(private val db : FBDatabase, private val service : WeatherService) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(db, service) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
