package com.example.mejora

class Objetos{
    var nombre :String= ""
    var peso :Int= 0
    var valor :Int= 0
    var vida :Int= 0

    constructor(nombre:String,peso: Int, valor: Int, vida: Int) {
        this.nombre = nombre
        this.peso = peso
        this.valor = valor
        this.vida = vida
    }
    constructor()

    override fun toString(): String {
        return "Objetos(nombre='$nombre', peso=$peso, valor=$valor, vida=$vida)"
    }


}