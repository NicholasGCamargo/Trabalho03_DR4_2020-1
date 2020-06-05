package com.example.tp03dr4.classes

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromCriptoString(value: CriptoString?): String? {
        return value?.getCriptoBase64()
    }

    @TypeConverter
    fun toCriptoString(value: String?): CriptoString? {
        val cripto = CriptoString()
        cripto.setCriptoBase64(value)
        return cripto
    }
}