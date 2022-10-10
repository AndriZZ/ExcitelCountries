package com.andriivanov.excitelcountries.data.post

enum class Region(val value: String) {
    Africa("Africa"),
    Americas("Americas"),
    Antarctic("Antarctic"),
    AntarcticOcean("Antarctic Ocean"),
    Asia("Asia"),
    Europe("Europe"),
    Oceania("Oceania"),
    Polar("Polar");

    companion object {
        fun fromValue(value: String): Region = when (value) {
            "Africa" -> Africa
            "Americas" -> Americas
            "Antarctic" -> Antarctic
            "Antarctic Ocean" -> AntarcticOcean
            "Asia" -> Asia
            "Europe" -> Europe
            "Oceania" -> Oceania
            "Polar" -> Polar
            else -> throw IllegalArgumentException()
        }
    }
}