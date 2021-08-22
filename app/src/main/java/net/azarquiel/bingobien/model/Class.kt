package net.azarquiel.bingoshare.model

import java.io.Serializable

data class Carton(var numeros:String="" , var selected:Boolean=false ):Serializable
data class CartonesSelected(var cartones: List<Carton> = emptyList()):Serializable