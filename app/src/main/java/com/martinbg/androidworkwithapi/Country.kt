package com.martinbg.androidworkwithapi

data class Country(
    var name: String,
    var capital: String,
    var flags: Flags,
    var region: String,
    var population: String,
    var area: String,
)

data class Flags(
    var svg: String,
    var png: String
)
