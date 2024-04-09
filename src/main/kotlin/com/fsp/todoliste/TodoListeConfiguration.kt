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
            taches.add(Tache("Acheter du pain"))
            taches.add(Tache("Acheter du lait"))
            taches.add(Tache("Acheter du beurre"))
            taches.add(Tache("Acheter de la confiture"))

            for (tache in taches) {
                tacheRepository.save(tache)
            }
        }
}