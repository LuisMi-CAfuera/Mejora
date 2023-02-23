package com.example.mejora

import android.content.Context
import java.io.Serializable

class Personaje {
    var nombre: String = ""
    var pesoMochila: Int = 0
    var raza: String=""
    var clase: String=""
    var fuerza : Int=0
    var destreza : Int=0
    var defensa : Int=0
    var vida : Int=0
    var mochila : ArrayList<Objetos> = ArrayList()
    //Monedero que sea un diccionario con el nombre del objeto y el valor
    var monedero : MutableMap<String,Int> = mutableMapOf(
        "1" to 100,
        "10" to 100,
        "20" to 100,
        "50" to 100,
        "100" to 100
    )



    constructor()

    constructor(nombre: String, pesoMochila: Int, raza: String, clase: String, fuerza: Int, destreza: Int,defensa : Int, vida: Int, mochila: ArrayList<Objetos>) {
        this.nombre = nombre
        this.pesoMochila = pesoMochila
        this.raza = raza
        this.clase = clase
        this.fuerza = fuerza
        this.destreza = destreza
        this.defensa = defensa
        this.vida = vida
        this.mochila = mochila
    }

    //Funcion que me diga que raza es aleatoriamente
    fun Raza():String{
        var raza : String = " "
        var razas = arrayOf("Elfo","Humano","Enano","Goblin")

        raza = razas.random()

        return raza
    }
    //Funcion que me diga si es adolescente,adulto o anciano aleatoriamente
    fun Edad():String{
        var edad : String = " "
        var edades = arrayOf("Adolescente","Adulto","Anciano")

        edad = edades.random()

        return edad
    }

    //Funcion que me de un nombre del EldenRing(Por que me apetece) aleatorio de un array de 7  nombres
    fun Nombre():String{
        var nombre : String = " "
        var nombres = arrayOf("Gwyn","Gwyndolin","Gwynevere","Malenia","Melina","Ranni","Radagon")

        nombre = nombres.random()

        return nombre
    }

    //Funcion que me diga el tipo de clase
    fun Clase():String{
        var clase : String = " "
        var clases = arrayOf("Guerrero","Mago","Ladron","Berserk")

        clase = clases.random()

        return clase
    }


}

class Personajes {
    var personajes: HashMap<String,Personaje> = HashMap()

    constructor()

    constructor(personajes: HashMap<String,Personaje>) {
        this.personajes = personajes
    }

}
class Partidas() : Serializable {
    private var listaPartidas = ArrayList<Partida>()
    fun getListaPartidas(): ArrayList<Partida> {
        return listaPartidas
    }

    fun encontrarPartida(nombre_personaje: String): Int {
        for ((indice, item) in listaPartidas.withIndex()) {
            if (item.getNombrePersonaje() == nombre_personaje) {
                return indice
            }
        }
        return -1
    }

    fun eliminarPartida(i: Int) {
        if (listaPartidas.isNotEmpty()) {
            getListaPartidas().removeAt(i)
        }
    }

    fun addPartida(partida: Partida) {
        listaPartidas.add(partida)
    }
}

class Partida(private var personaje: Personaje, private var nombre_personaje: String) :
    Serializable {

    constructor() : this(Personaje(), "")

    fun getNombrePersonaje(): String {
        return nombre_personaje
    }

    fun getPersonaje(): Personaje {
        return personaje
    }

    fun setNombrePersonaje(name: String) {
        this.nombre_personaje = name
    }

    fun setPersonaje(personaje: Personaje) {
        this.personaje = personaje
    }


}
