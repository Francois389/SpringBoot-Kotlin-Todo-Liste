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