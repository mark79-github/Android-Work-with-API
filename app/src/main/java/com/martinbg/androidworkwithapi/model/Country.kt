package com.martinbg.androidworkwithapi.model

data class Country(
    var name: String,
    var capital: String,
    var flags: Flags,
    var region: String,
    var population: String?,
    var area: String?,
)
