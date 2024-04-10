package com.fsp.todoliste

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Controller
class HTMLControleur(val tacheRepository: TacheRepository) {

    @GetMapping("/")
    fun index(model: Model): String {
        model["titre"] = "Liste de tâches"
        model["taches"] = tacheRepository.findAllByOrderByTitre()

        return "index"
    }

    @GetMapping("/tache/{urlFree}")
    fun tache(@PathVariable urlFree: String, model: Model): String {
        val tache = tacheRepository.findByUrlFree(urlFree)
            ?: throw IllegalArgumentException("Tâche inconnue")

        model["titre"] = tache.titre
        model["tache"] = tache
        return "tache"
    }

    @GetMapping("/tache/{urlFree}/editer")
    fun editer(@PathVariable urlFree: String, model: Model): String {
        val tache = tacheRepository.findByUrlFree(urlFree)
            ?: throw IllegalArgumentException("Tâche inconnue")

        var formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        model["titre"] = "Édition de la tache"
        model["tache"] = tache
        model["echeance"] = tache.echeance?.format(formatter)?: ""
        model["etatPossible"] = EtatTache.entries.map { it.toString() }

        return "editer"
    }

    @PostMapping("/tache/{urlFree}/editer")
    fun editer(
        @PathVariable urlFree: String,
        @RequestParam titre: String,
        @RequestParam description: String,
        @RequestParam echeance: String,
        @RequestParam etat: String
    ): String {
        val tache = tacheRepository.findByUrlFree(urlFree)
            ?: throw IllegalArgumentException("Tâche inconnue")

        var pageDestination: String = "redirect:/tache/${tache.urlFree}/editer"
        val dateEcheance: LocalDateTime? =
            if (echeance.isNotBlank())
                LocalDate.parse(echeance).atStartOfDay()
            else null

        if (titre.isNotBlank() && (dateEcheance == null || dateEcheance.isAfter(LocalDateTime.now()))) {
            tache.titre = titre
            tache.description = description
            tache.etat = EtatTache.valueOf(etat).toString()
            tache.echeance = dateEcheance

            tacheRepository.save(tache)

            println("Tâche modifiée : $tache")

            pageDestination = "redirect:/tache/${tache.urlFree}"
        }


        return pageDestination
    }

    @GetMapping("/tache/{urlFree}/supprimer")
    fun supprimer(@PathVariable urlFree: String, model: Model): String {
        val tache = tacheRepository.findByUrlFree(urlFree)
            ?: throw IllegalArgumentException("Tâche inconnue")

        model["titre"] = "Suppression de la tache"
        model["tache"] = tache

        return "supprimer"
    }

    @PostMapping("/tache/{urlFree}/supprimer")
    fun supprimer(@PathVariable urlFree: String): String {
        val tache = tacheRepository.findByUrlFree(urlFree)
            ?: throw IllegalArgumentException("Tâche inconnue")

        tacheRepository.delete(tache)

        println("Tâche supprimée : $tache")

        return "redirect:/"
    }

    @GetMapping("/tache/creer")
    fun creer(model: Model): String {
        model["titre"] = "Création de la tache"
        model["etatPossible"] = EtatTache.entries.map { it.toString() }
        model["tache"] = Tache()

        return "creer"
    }

    private fun Tache(): Tache {
        return Tache("", "", null, EtatTache.A_FAIRE.toString())
    }

    @PostMapping("/tache/creer")
    fun creer(
        @RequestParam titre: String,
        @RequestParam description: String,
        @RequestParam echeance: String,
        @RequestParam etat: String
    ): String {
        var pageDestination: String = "redirect:/tache/creer"
        val dateEcheance: LocalDateTime? =
            if (echeance.isNotBlank())
                LocalDate.parse(echeance).atStartOfDay()
            else null

        if (titre.isNotBlank() && (dateEcheance == null || dateEcheance.isAfter(LocalDateTime.now()))) {
            val tache = Tache(titre, description, dateEcheance, etat)
            tacheRepository.save(tache)

            println("Tâche créée : $tache")

            pageDestination = "redirect:/tache/${tache.urlFree}"
        }

        return pageDestination
    }

}