package com.example.mejora

import android.content.Context
import java.io.Serializable

class Personaje {
    var id: Int = 0
    var email : String = ""
    var nombre: String = ""
    var pesoMochila: Int = 0
    var raza: String=""
    var clase: String=""
    var lugar : String=""
    var fuerza : Int=0
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

    constructor(id:Int,nombre: String, pesoMochila: Int, raza: String, clase: String,lugar:String, fuerza: Int,defensa : Int, vida: Int, mochila: ArrayList<Objetos>) {
        this.id = id
        this.nombre = nombre
        this.pesoMochila = pesoMochila
        this.raza = raza
        this.clase = clase
        this.lugar = lugar
        this.fuerza = fuerza
        this.defensa = defensa
        this.vida = vida
        this.mochila = mochila
    }

    constructor(id: Int, email: String, nombre: String, pesoMochila: Int, raza: String, clase: String, lugar: String, fuerza: Int, defensa: Int, vida: Int, mochila: ArrayList<Objetos>, monedero: MutableMap<String, Int>) {
        this.id = id
        this.email = email
        this.nombre = nombre
        this.pesoMochila = pesoMochila
        this.raza = raza
        this.clase = clase
        this.lugar = lugar
        this.fuerza = fuerza
        this.defensa = defensa
        this.vida = vida
        this.mochila = mochila
        this.monedero = monedero
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

    override fun toString(): String {
        return "Personaje(id=$id, email='$email', nombre='$nombre', pesoMochila=$pesoMochila, raza='$raza', clase='$clase', lugar='$lugar', fuerza=$fuerza, defensa=$defensa, vida=$vida, mochila=$mochila, monedero=$monedero)"
    }


}




class Partidas{
    var email : String = ""
    var partidas  = ArrayList<Personaje>()

    constructor(email: String, partidas: ArrayList<Personaje>) {
        this.email = email
        this.partidas = partidas
    }
    constructor()

}

