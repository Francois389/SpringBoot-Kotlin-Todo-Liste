package com.fsp.todoliste

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class Tache(
    var titre: String,
    var description: String? = null,
    var echeance: LocalDateTime? = null,
    var etat: String = EtatTache.A_FAIRE.toString(),
    var urlFree: String = titre.toURLFree(),
    @Id @GeneratedValue var id: Long? = null
) {
    override fun toString(): String {
        return "Tache(titre='$titre', description=$description, echeance=$echeance, terminee=$etat, urlFree='$urlFree', id=$id)"
    }
}

enum class EtatTache {
    A_FAIRE,
    EN_COURS,
    TERMINEE
}

//@Entity
//class User (
//    var login: String,
//    var firstname: String,
//    var lastname: String,
//    @Id @GeneratedValue var id: Long? = null
//)