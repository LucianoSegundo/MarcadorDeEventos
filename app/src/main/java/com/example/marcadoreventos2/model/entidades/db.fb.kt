package com.example.marcadoreventos2.model.entidades

import com.example.marcadoreventos.model.entidades.eventos
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.firestore


class FBEvento {
    var nomeEvento : String? = null
    var autornome: String? = null
    var autoremail: String? = null

    var estadoEvento: String? = null
    var cidadeEvento: String? = null

    var numVagas: Int? = null
    var numeroConfirmacoes: Int ? = null

    var inicio: String? = null
    var termino: String? = null

    var lat : Double? = null
    var lng : Double? = null

    var descricao: String? = null

    fun toEvento(): eventos {
        var latlng = if (lat!=null&&lng!=null) LatLng(lat!!, lng!!) else null

        return eventos(
            location = latlng!!,
            estadoEvento = estadoEvento!!,
            descricao = descricao!!,
            inicio = inicio!!,
            cidadeEvento = cidadeEvento!!,
            nomeEvento = nomeEvento!!,
            termino = termino!!,
            autor = User(email = autoremail!!, name = autornome!!),
            numeroConfirmacoes = numeroConfirmacoes!!,
            numVagas = numVagas!!
        )
    }
}

fun eventos.toFBEvento() : FBEvento {
    val evento_fb = toFBEvento()
    evento_fb.nomeEvento = this.nomeEvento

    evento_fb.lat = this.location?.latitude ?: 0.0
    evento_fb.lng = this.location?.longitude ?: 0.0

    evento_fb.cidadeEvento = this.cidadeEvento?:"erro"
    evento_fb.estadoEvento = this.estadoEvento?:"erro"

    evento_fb.autornome = this.autor?.name?:"erro"
    evento_fb.autoremail = this.autor?.email?:"erro"

    evento_fb.descricao = this.descricao?:"erro"

    evento_fb.inicio= this.inicio?:"erro"
    evento_fb.termino = this.termino?:"erro"

    evento_fb.numVagas = this.numVagas?:0
    evento_fb.numeroConfirmacoes = this.numeroConfirmacoes?:0

    return evento_fb
}

class FBUser {
    var name : String ? = null
    var email : String? = null
    fun toUser() = User(name!!, email!!)
}
fun User.toFBUser() : FBUser {
    val fbUser = FBUser()
    fbUser.name = this.name
    fbUser.email = this.email
    return fbUser
}

class FBDatabase {
    interface Listener {
        fun onUserLoaded(user: User)
        fun onEventoAdded(evento: eventos)
        fun onEventoUpdate(evento: eventos)
        fun onEventoRemoved(evento: eventos)
    }
    private val auth = Firebase.auth
    private val db = Firebase.firestore
    private var eventosListReg: ListenerRegistration? = null
    private var listener : Listener? = null

    init {
        auth.addAuthStateListener { auth ->
            if (auth.currentUser == null) {
                eventosListReg?.remove()
                return@addAuthStateListener
            }
            val refCurrUser = db.collection("users")
                .document(auth.currentUser!!.uid)
            refCurrUser.get().addOnSuccessListener {
                it.toObject(FBUser::class.java)?.let { user ->
                    listener?.onUserLoaded(user.toUser())
                }
            }
            eventosListReg = refCurrUser.collection("eventos")
                .addSnapshotListener { snapshots, ex ->
                    if (ex != null) return@addSnapshotListener
                    snapshots?.documentChanges?.forEach { change ->
                        val FBEvento = change.document.toObject(FBEvento::class.java)
                        if (change.type == DocumentChange.Type.ADDED) {
                            listener?.onEventoAdded(FBEvento.toEvento())
                        } else if (change.type == DocumentChange.Type.REMOVED) {
                            listener?.onEventoRemoved(FBEvento.toEvento())
                        }
                    }
                }
        }
    }
    fun setListener(listener: Listener? = null) {
        this.listener = listener

    }
    fun register(user: User) {
        if (auth.currentUser == null)
            throw RuntimeException("User not logged in!")
        val uid = auth.currentUser!!.uid
        db.collection("users").document(uid + "").set(user.toFBUser());
    }
    fun add(evento: eventos) {
        if (auth.currentUser == null)
            throw RuntimeException("User not logged in!")
        val uid = auth.currentUser!!.uid
        db.collection("users").document(uid).collection("eventos")
            .document(evento.nomeEvento).set(evento.toFBEvento())

    }
    fun remove(evento: eventos) {
        if (auth.currentUser == null)
            throw RuntimeException("User not logged in!")
        val uid = auth.currentUser!!.uid
        db.collection("users").document(uid).collection("eventos")
            .document(evento.nomeEvento).delete();

    }

}
