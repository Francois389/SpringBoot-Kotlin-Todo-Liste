package com.fsp.todoliste

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TodoListeConfiguration {

    @Bean
    fun databaseInitialisation(
        tacheRepository: TacheRepository
    ) =
        ApplicationRunner {

            var taches = mutableListOf<Tache>()
            taches.add(Tache("a - Acheter du pain"))
            taches.add(Tache("b - Acheter du lait"))
            taches.add(Tache("c - Acheter du beurre"))
            taches.add(Tache("d - Acheter de la confiture"))
            taches.add(Tache("e - Acheter des croissants"))
            taches.add(Tache("f - Faire le ménage"))
            taches.add(Tache("g - Faire la vaisselle"))
            taches.add(Tache("h - Faire le lit"))
            taches.add(Tache("i - Faire les courses"))
            taches.add(Tache("j - Travailler les structures de données"))
            taches.add(Tache("k - Travailler les bases de données"))
            taches.add(Tache("l - Travailler les réseaux"))
            taches.add(Tache("m - Travailler les systèmes d'exploitation"))

            for (tache in taches) {
                tacheRepository.save(tache)
            }
        }
}