package com.fsp.todoliste

import org.springframework.data.repository.CrudRepository

interface TacheRepository: CrudRepository<Tache, Long>{
    fun findByTitre(titre: String): Tache?
    fun findByUrlFree(urlFree: String): Tache?
    fun findAllByOrderByTitre(): List<Tache>
}

//interface UserRepository: CrudRepository<User, Long>{
//    fun findByLogin(login: String): User?
//}