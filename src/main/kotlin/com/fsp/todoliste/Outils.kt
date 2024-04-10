package com.fsp.todoliste

import java.util.*

/**
 * Converti une chaine de caractères pour les utiliser dans des URL
 * ce qui signifie que les caractères spéciaux sont retirés
 * et que les espaces sont remplacés par des tirets
 */
fun String.toURLFree() = lowercase(Locale.getDefault())
    .replace("\n", " ")
    .replace("[^a-z\\d\\s]".toRegex(), " ")
    .split(" ")
    .joinToString("-")
    .replace("-+".toRegex(), "-")


    /**
     * Retourne une portion de la liste de tâches.
     * @param taillePortion Taille de la portion
     * @param numeroPortion Numéro de la portion
     * @return Portion de la liste de tâches
     */
    fun List<Tache>.portion(taillePortion: Int, numeroPortion: Int): List<Tache> {
        if (taillePortion <= 0 || numeroPortion < 0) {
            throw IllegalArgumentException("Taille de portion (${taillePortion}) ou numéro de portion (${numeroPortion}) invalide")
        }
        if (this.isEmpty()) {
            return emptyList()
        }
        if (this.size <= taillePortion) {
            return this
        }
        if (this.size / taillePortion <= numeroPortion) {
            return this.subList(numeroPortion * taillePortion, this.size)
        }
        return this.subList(
            numeroPortion * taillePortion,
            minOf((numeroPortion + 1) * taillePortion, this.size)
        )
    }