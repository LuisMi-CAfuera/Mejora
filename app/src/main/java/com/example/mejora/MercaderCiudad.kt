package com.example.mejora

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.mejora.databinding.ActivityMercaderBinding
import com.google.gson.Gson

class MercaderCiudad : AppCompatActivity() {
    private lateinit var binding: ActivityMercaderBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMercaderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val shared = getSharedPreferences("Personaje", MODE_PRIVATE)
        val gson = Gson()
        var json = shared.getString("Personaje", "")
        val p = gson.fromJson(json, Personaje::class.java)
        val ArrayObjetos: ArrayList<Objetos> = ArrayList()
        ArrayObjetos.add(Objetos("Espada", 2, 120, 200))
        ArrayObjetos.add(Objetos("Armadura", 1, 100, 150))
        ArrayObjetos.add(Objetos("Escudo", 1, 100, 150))
        ArrayObjetos.add(Objetos("Pocion", 0, 50, 50))
        p.monedero["1"] = 100
        p.monedero["10"] = 100
        p.monedero["20"] = 100
        p.monedero["50"] = 100
        p.monedero["100"] = 100
        binding.Mercader.setImageResource(R.drawable.mercader)
        println(p.mochila.toString())


        binding.Comerciar.setOnClickListener {
            binding.Comerciar.visibility = View.INVISIBLE
            binding.Comprar.visibility = View.VISIBLE
            binding.Vender.visibility = View.VISIBLE
            binding.Cancelar.visibility = View.VISIBLE
            binding.Volver.visibility = View.INVISIBLE
        }

        binding.Comprar.setOnClickListener {
            binding.spinner.visibility = View.VISIBLE
            binding.Comprar.visibility = View.INVISIBLE
            binding.Vender.visibility = View.INVISIBLE
            binding.Cancelar.visibility = View.INVISIBLE
            binding.mas.visibility = View.VISIBLE
            binding.menos.visibility = View.VISIBLE
            binding.CantidadYPrecio.visibility = View.VISIBLE
            binding.CantidadYPrecio.text = "Cantidad: 0 Precio: 0"
            binding.ComprarOVender.visibility = View.VISIBLE
            comprar(p)
        }

        binding.Vender.setOnClickListener {
            binding.spinner.visibility = View.VISIBLE
            binding.Comprar.visibility = View.INVISIBLE
            binding.Vender.visibility = View.INVISIBLE
            binding.Cancelar.visibility = View.INVISIBLE
            binding.mas.visibility = View.VISIBLE
            binding.menos.visibility = View.VISIBLE
            binding.CantidadYPrecio.visibility = View.VISIBLE
            binding.CantidadYPrecio.text = "Cantidad: 0 Precio: 0"
            vender(p)
        }



        binding.Cancelar.setOnClickListener {
            binding.Comerciar.visibility = View.VISIBLE
            binding.Comprar.visibility = View.INVISIBLE
            binding.Vender.visibility = View.INVISIBLE
            binding.Cancelar.visibility = View.INVISIBLE
            binding.Volver.visibility = View.VISIBLE
        }








        binding.Volver.setOnClickListener {
            val intent = Intent(this@MercaderCiudad, Ciudad::class.java)
            val edit = shared.edit()
            edit.clear()
            json = gson.toJson(p)
            edit.putString("Personaje", json)
            edit.apply()
            startActivity(intent)
        }


//        val adapter = ArrayAdapter(
//            this@Mercader,
//            android.R.layout.simple_spinner_item,
//            objetos.keys.toList()
//        )
//        binding.spinner.adapter = adapter


    }

    @SuppressLint("SetTextI18n")
    fun comprar(p: Personaje) {
        val espada = Objetos("Espada", 2, 120, 200)
        val armadura = Objetos("Armadura", 1, 100, 150)
        val escudo = Objetos("Escudo", 1, 100, 150)
        val pocion = Objetos("Pocion", 0, 50, 50)
        var cantidad = 0
        var precio = 0


        if (p.pesoMochila <= 0) {
            binding.Mercader.setImageResource(R.drawable.mercader)
            binding.CantidadYPrecio.text = "No tienes espacio en la mochila"
            binding.Comerciar.visibility = View.VISIBLE
            binding.Comprar.visibility = View.INVISIBLE
            binding.Vender.visibility = View.INVISIBLE
            binding.Cancelar.visibility = View.INVISIBLE
            binding.Volver.visibility = View.VISIBLE
            binding.spinner.visibility = View.INVISIBLE
            binding.mas.visibility = View.INVISIBLE
            binding.menos.visibility = View.INVISIBLE
            binding.ComprarOVender.visibility = View.INVISIBLE
        } else {
            binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                @SuppressLint("SetTextI18n")
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (binding.spinner.selectedItem.toString()) {
                        " " -> {
                            binding.Mercader.setImageResource(R.drawable.mercader)
                            binding.CantidadYPrecio.text = "Cantidad: 0 Precio: 0"
                        }
                        "Espada" -> {
                            precio = 0
                            cantidad = 0
                            binding.CantidadYPrecio.text = "Cantidad: 0 Precio: 0"
                            binding.Mercader.setImageResource(R.drawable.espada)
                            binding.mas.setOnClickListener {
                                if (p.pesoMochila > espada.peso * cantidad) {
                                    p.mochila.add(espada)
                                    cantidad++
                                    precio = espada.valor * cantidad
                                    binding.CantidadYPrecio.text =
                                        "Cantidad: $cantidad Precio: $precio Peso : ${espada.peso * cantidad}"
                                }
                            }
                            binding.menos.setOnClickListener {
                                if (cantidad > 0) {
                                    p.mochila.removeAt(encontrarObjeto(p.mochila,"Espada"))
                                    cantidad--
                                    precio = espada.valor * cantidad
                                    binding.CantidadYPrecio.text =
                                        "Cantidad: $cantidad Precio: $precio Peso : ${espada.peso * cantidad}"
                                }
                            }
                        }
                        "Armadura" -> {
                            precio = 0
                            cantidad = 0
                            binding.CantidadYPrecio.text = "Cantidad: 0 Precio: 0"
                            binding.Mercader.setImageResource(R.drawable.armadura)
                            binding.mas.setOnClickListener {
                                if (p.pesoMochila > armadura.peso * cantidad) {
                                    p.mochila.add(armadura)
                                    cantidad++
                                    precio = armadura.valor * cantidad
                                    binding.CantidadYPrecio.text =
                                        "Cantidad: $cantidad Precio: $precio Peso : ${armadura.peso * cantidad}"
                                }
                            }
                            binding.menos.setOnClickListener {
                                if (cantidad > 0) {
                                    p.mochila.removeAt(encontrarObjeto(p.mochila,"Armadura"))
                                    cantidad--
                                    precio = armadura.valor * cantidad
                                    binding.CantidadYPrecio.text =
                                        "Cantidad: $cantidad Precio: $precio Peso : ${armadura.peso * cantidad}"
                                }
                            }
                        }
                        "Escudo" -> {
                            precio = 0
                            cantidad = 0
                            binding.CantidadYPrecio.text = "Cantidad: 0 Precio: 0"
                            binding.Mercader.setImageResource(R.drawable.escudo)
                            binding.mas.setOnClickListener {
                                if (p.pesoMochila > escudo.peso * cantidad) {
                                    p.mochila.add(escudo)
                                    cantidad++
                                    precio = escudo.valor * cantidad
                                    binding.CantidadYPrecio.text =
                                        "Cantidad: $cantidad Precio: $precio Peso : ${escudo.peso * cantidad}"
                                }
                            }
                            binding.menos.setOnClickListener {
                                if (cantidad > 0) {
                                    p.mochila.removeAt(encontrarObjeto(p.mochila,"Escudo"))
                                    cantidad--
                                    precio = escudo.valor * cantidad
                                    binding.CantidadYPrecio.text =
                                        "Cantidad: $cantidad Precio: $precio Peso : ${escudo.peso * cantidad}"
                                }
                            }

                        }
                        "Pocion" -> {
                            precio = 0
                            cantidad = 0
                            binding.CantidadYPrecio.text = "Cantidad: 0 Precio: 0"
                            binding.Mercader.setImageResource(R.drawable.pocion)
                            binding.mas.setOnClickListener {
                                if (p.pesoMochila > pocion.peso * cantidad) {
                                    p.mochila.add(pocion)
                                    cantidad++
                                    precio = pocion.valor * cantidad
                                    binding.CantidadYPrecio.text =
                                        "Cantidad: $cantidad Precio: $precio Peso : ${pocion.peso * cantidad}"
                                }
                            }
                            binding.menos.setOnClickListener {
                                if (cantidad > 0) {
                                    p.mochila.removeAt(encontrarObjeto(p.mochila,"Pocion"))
                                    cantidad--
                                    precio = pocion.valor * cantidad
                                    binding.CantidadYPrecio.text =
                                        "Cantidad: $cantidad Precio: $precio Peso : ${pocion.peso * cantidad}"
                                }
                            }
                        }
                    }
                    binding.ComprarOVender.setOnClickListener {
                        var cociente = 0
                        val pago2 = precio

                        cociente = precio / 100

                        if (cociente > 0) {
                            precio -= 100 * cociente
                            p.monedero["100"] = p.monedero["100"]!! - cociente
                        }

                        cociente = precio / 50

                        if (cociente > 0) {
                            precio -= 50 * cociente
                            p.monedero["50"] = p.monedero["50"]!! - cociente
                        }

                        cociente = precio / 20

                        if (cociente > 0) {
                            precio -= 20 * cociente
                            p.monedero["20"] = p.monedero["20"]!! - cociente
                        }

                        cociente = precio / 10

                        if (cociente > 0) {
                            precio -= 10 * cociente
                            p.monedero["10"] = p.monedero["10"]!! - cociente
                        }

                        cociente = precio / 1

                        if (cociente > 0) {
                            precio -= 1 * cociente
                            p.monedero["1"] = p.monedero["1"]!! - cociente
                        }

                        binding.Mercader.setImageResource(R.drawable.mercader)
                        binding.CantidadYPrecio.text =
                            "Has comprado $cantidad \n ${binding.spinner.selectedItem.toString()} por $pago2"
                        binding.Comerciar.visibility = View.VISIBLE
                        binding.Comprar.visibility = View.INVISIBLE
                        binding.Vender.visibility = View.INVISIBLE
                        binding.Cancelar.visibility = View.INVISIBLE
                        binding.Volver.visibility = View.VISIBLE
                        binding.spinner.visibility = View.INVISIBLE
                        binding.mas.visibility = View.INVISIBLE
                        binding.menos.visibility = View.INVISIBLE
                        binding.ComprarOVender.visibility = View.INVISIBLE

                        println(p.mochila.toString())

                        binding.spinner.setSelection(0)

                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }

        }

    }

    @SuppressLint("SetTextI18n")
    fun vender(p: Personaje) {
        binding.ComprarOVender.text = "Vender"
        val espada = Objetos("Espada", 2, 120, 200)
        val armadura = Objetos("Armadura", 1, 100, 150)
        val escudo = Objetos("Escudo", 1, 100, 150)
        val pocion = Objetos("Pocion", 0, 50, 50)
        var precio = 0
        var cantidad = 0
        var CantidadObjetos = HashMap<String, Int>()


        if (p.mochila.isEmpty()) {
            binding.CantidadYPrecio.text = "No tienes nada en la mochila"
            binding.Comerciar.visibility = View.VISIBLE
            binding.Comprar.visibility = View.INVISIBLE
            binding.Vender.visibility = View.INVISIBLE
            binding.Cancelar.visibility = View.INVISIBLE
            binding.Volver.visibility = View.VISIBLE
            binding.spinner.visibility = View.INVISIBLE
            binding.mas.visibility = View.INVISIBLE
            binding.menos.visibility = View.INVISIBLE
            binding.ComprarOVender.visibility = View.INVISIBLE
        } else {
            binding.ComprarOVender.visibility = View.VISIBLE
            binding.Mercader.setImageResource(R.drawable.cars1)
            for (i in p.mochila) {
                if (CantidadObjetos.containsKey(i.nombre)) {
                    CantidadObjetos[i.nombre] = CantidadObjetos[i.nombre]!! + 1
                } else {
                    CantidadObjetos[i.nombre] = 1
                }
            }
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                CantidadObjetos.keys.toTypedArray()
            )
            binding.spinner.adapter = adapter

            binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                @SuppressLint("SetTextI18n")
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when(binding.spinner.selectedItem.toString()){
                        "Espada"->{
                            precio = 0
                            cantidad = 0
                            binding.CantidadYPrecio.text = "Cantidad: 0 Precio: 0"
                            binding.mas.setOnClickListener {
                                if (CantidadObjetos["Espada"]!! > cantidad) {
                                    p.mochila.removeAt(encontrarObjeto(p.mochila, "Espada"))
                                    p.pesoMochila -= espada.peso
                                    cantidad++
                                    precio = espada.valor * cantidad
                                    binding.CantidadYPrecio.text =
                                        "Cantidad: $cantidad Precio: $precio Peso : ${espada.peso * cantidad}"
                                }
                            }
                            binding.menos.setOnClickListener {
                                if (cantidad > 0) {
                                    p.mochila.add(espada)
                                    p.pesoMochila += espada.peso
                                    cantidad--
                                    precio = espada.valor * cantidad
                                    binding.CantidadYPrecio.text =
                                        "Cantidad: $cantidad Precio: $precio Peso : ${espada.peso * cantidad}"
                                }
                            }

                        }
                        "Armadura"->{
                            precio = 0
                            cantidad = 0
                            binding.CantidadYPrecio.text = "Cantidad: 0 Precio: 0"
                            binding.mas.setOnClickListener {
                                if (CantidadObjetos["Armadura"]!! > cantidad) {
                                    p.mochila.removeAt(encontrarObjeto(p.mochila, "Armadura"))
                                    p.pesoMochila -= armadura.peso
                                    cantidad++
                                    precio = armadura.valor * cantidad
                                    binding.CantidadYPrecio.text =
                                        "Cantidad: $cantidad Precio: $precio Peso : ${armadura.peso * cantidad}"
                                }
                            }
                            binding.menos.setOnClickListener {
                                if (cantidad > 0) {
                                    p.mochila.add(armadura)
                                    p.pesoMochila += armadura.peso
                                    cantidad--
                                    precio = armadura.valor * cantidad
                                    binding.CantidadYPrecio.text =
                                        "Cantidad: $cantidad Precio: $precio Peso : ${armadura.peso * cantidad}"
                                }
                            }
                        }
                        "Escudo"->{
                            precio = 0
                            cantidad = 0
                            binding.CantidadYPrecio.text = "Cantidad: 0 Precio: 0"
                            binding.mas.setOnClickListener {
                                if (CantidadObjetos["Escudo"]!! > cantidad) {
                                    p.mochila.removeAt(encontrarObjeto(p.mochila, "Escudo"))
                                    p.pesoMochila -= escudo.peso
                                    cantidad++
                                    precio = escudo.valor * cantidad
                                    binding.CantidadYPrecio.text =
                                        "Cantidad: $cantidad Precio: $precio Peso : ${escudo.peso * cantidad}"
                                }
                            }
                            binding.menos.setOnClickListener {
                                if (cantidad > 0) {
                                    p.mochila.add(escudo)
                                    p.pesoMochila += escudo.peso
                                    cantidad--
                                    precio = escudo.valor * cantidad
                                    binding.CantidadYPrecio.text =
                                        "Cantidad: $cantidad Precio: $precio Peso : ${escudo.peso * cantidad}"
                                }
                            }
                        }
                        "Pocion"->{
                            precio = 0
                            cantidad = 0
                            binding.CantidadYPrecio.text = "Cantidad: 0 Precio: 0"
                            binding.mas.setOnClickListener {
                                if (CantidadObjetos["Pocion"]!! > cantidad) {
                                    p.mochila.removeAt(encontrarObjeto(p.mochila, "Pocion"))
                                    p.pesoMochila -= pocion.peso
                                    cantidad++
                                    precio = pocion.valor * cantidad
                                    binding.CantidadYPrecio.text =
                                        "Cantidad: $cantidad Precio: $precio Peso : ${pocion.peso * cantidad}"
                                }
                            }
                            binding.menos.setOnClickListener {
                                if (cantidad > 0) {
                                    p.mochila.add(pocion)
                                    p.pesoMochila += pocion.peso
                                    cantidad--
                                    precio = pocion.valor * cantidad
                                    binding.CantidadYPrecio.text =
                                        "Cantidad: $cantidad Precio: $precio Peso : ${pocion.peso * cantidad}"
                                }
                            }
                        }
                        "Objeto Random"->{
                            precio = 0
                            cantidad = 0
                            binding.CantidadYPrecio.text = "No puedes vender este objeto"

                        }
                    }

                    binding.ComprarOVender.setOnClickListener{
                        var cociente = 0
                        var precio2 = precio

                        cociente = precio / 100
                        if (cociente > 0) {
                            precio -= 100 * cociente
                            p.monedero["100"] = p.monedero["100"]!! - cociente
                        }
                        cociente = precio / 50
                        if (cociente > 0) {
                            precio -= 50 * cociente
                            p.monedero["50"] = p.monedero["50"]!! - cociente
                        }
                        cociente = precio / 20
                        if (cociente > 0) {
                            precio -= 20 * cociente
                            p.monedero["20"] = p.monedero["20"]!! - cociente
                        }
                        cociente = precio / 10
                        if (cociente > 0) {
                            precio -= 10 * cociente
                            p.monedero["10"] = p.monedero["10"]!! - cociente
                        }
                        cociente = precio / 1
                        if (cociente > 0) {
                            precio -= 1 * cociente
                            p.monedero["1"] = p.monedero["1"]!! - cociente
                        }

                        binding.Mercader.setImageResource(R.drawable.mercader)
                        binding.CantidadYPrecio.text =
                            "Has vendido $cantidad \n ${binding.spinner.selectedItem.toString()} por $precio2"
                        binding.Comerciar.visibility = View.VISIBLE
                        binding.Comprar.visibility = View.INVISIBLE
                        binding.Vender.visibility = View.INVISIBLE
                        binding.Cancelar.visibility = View.INVISIBLE
                        binding.Volver.visibility = View.VISIBLE
                        binding.spinner.visibility = View.INVISIBLE
                        binding.mas.visibility = View.INVISIBLE
                        binding.menos.visibility = View.INVISIBLE
                        binding.ComprarOVender.visibility = View.INVISIBLE

                        println(p.mochila.toString())
                    }


                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
    }

    fun encontrarObjeto(objetos : ArrayList<Objetos>, nombre : String) : Int {
        var cont = 0
        for (i in objetos){
            if (i.nombre == nombre){
                return cont
            }
            cont ++
        }
        return -1
    }


}




