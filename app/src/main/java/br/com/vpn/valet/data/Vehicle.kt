package br.com.vpn.valet.data

data class Vehicle(
    var id: String? = null,
    var brand: String,
    var model: String,
    var license: String,
    val color: String? = "",
    val isParked: Boolean = false,
)