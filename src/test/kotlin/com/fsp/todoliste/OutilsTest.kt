package com.fsp.todoliste

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import java.time.LocalDateTime

class OutilsTest {

    private lateinit var listeTache : MutableList<Tache>

    @BeforeEach
    fun setUp() {
        listeTache = mutableListOf<Tache>()
        listeTache.add(Tache("Tache 1", "Description 1", LocalDateTime.now(), EtatTache.A_FAIRE.toString(), "tache-1"))
        listeTache.add(Tache("Tache 2", "Description 2", LocalDateTime.now(), EtatTache.A_FAIRE.toString(), "tache-2"))
        listeTache.add(Tache("Tache 3", "Description 3", LocalDateTime.now(), EtatTache.A_FAIRE.toString(), "tache-3"))
        listeTache.add(Tache("Tache 4", "Description 4", LocalDateTime.now(), EtatTache.A_FAIRE.toString(), "tache-4"))
        listeTache.add(Tache("Tache 5", "Description 5", LocalDateTime.now(), EtatTache.A_FAIRE.toString(), "tache-5"))
        listeTache.add(Tache("Tache 6", "Description 6", LocalDateTime.now(), EtatTache.A_FAIRE.toString(), "tache-6"))
        listeTache.add(Tache("Tache 7", "Description 7", LocalDateTime.now(), EtatTache.A_FAIRE.toString(), "tache-7"))
        listeTache.add(Tache("Tache 8", "Description 8", LocalDateTime.now(), EtatTache.A_FAIRE.toString(), "tache-8"))
    }

    @Test
    fun `Test de la fonction portion nominal`() {
        //Given une liste de tâche
        //When, on demande la première portion de 4 éléments
        val portion = listeTache.portion(4, 0)

        //Then, on obtient une liste de 4 premiers éléments
        assertEquals(4, portion.size)
        //And les éléments sont les 4 premiers éléments de la liste
        assertEquals(listeTache[0], portion[0])
        assertEquals(listeTache[1], portion[1])
        assertEquals(listeTache[2], portion[2])
        assertEquals(listeTache[3], portion[3])
    }

    @Test
    fun `Test de la fonction portion avec une taille de portion negative`() {
        //Given une liste de tâche
        //When, on demande une portion de taille négative
        //Then, une exception est levée
        assertThrows(IllegalArgumentException::class.java) {
            listeTache.portion(-1, 0)
        }
    }

    @Test
    fun `Test de la fonction portion avec un numero de portion negatif`() {
        //Given une liste de tâche
        //When, on demande une portion avec un numéro de portion négatif
        //Then, une exception est levée
        assertThrows(IllegalArgumentException::class.java) {
            listeTache.portion(4, -1)
        }
    }

    @Test
    fun `Test de la fonction portion avec une liste vide`() {
        //Given une liste de tâche vide
        //When, on demande une portion
        val portion = emptyList<Tache>().portion(4, 0)

        //Then, on obtient une liste vide
        assertTrue(portion.isEmpty())
    }

    @Test
    fun `Test de la fonction portion avec une liste de taille inferieure a la taille de portion`() {
        //Given une liste de tâche
        //When, on demande une portion de taille supérieure à la taille de la liste
        val portion = listeTache.portion(listeTache.size + 1, 0)

        //Then, on obtient la liste complète
        assertEquals(listeTache.size, portion.size)
        //And les éléments sont les éléments de la liste
        assertEquals(listeTache[0], portion[0])
        assertEquals(listeTache[1], portion[1])
        assertEquals(listeTache[2], portion[2])
        assertEquals(listeTache[3], portion[3])
        assertEquals(listeTache[4], portion[4])
        assertEquals(listeTache[5], portion[5])
        assertEquals(listeTache[6], portion[6])
        assertEquals(listeTache[7], portion[7])
    }

    @Test
    fun `Test de la fonction portion avec le numero de portion superieur au nombre de portion`() {
        //Given une liste de tâche
        //When, on demande une portion avec un numéro de portion supérieur au nombre de portions
        //Then, on obtient une erreur
        assertThrows(IllegalArgumentException::class.java) {
            listeTache.portion(4, listeTache.size / 4 + 1)
        }
    }

    @Test
    fun `Test de la fonction portion avec un taille de portion egale a 1`() {
        //Given une liste de tâche
        //When, on demande une portion de taille 1
        val portion = listeTache.portion(1, 0)

        //Then, on obtient une liste de 1 élément
        assertEquals(1, portion.size)
        //And l'élément est le premier élément de la liste
        assertEquals(listeTache[0], portion[0])
    }
}