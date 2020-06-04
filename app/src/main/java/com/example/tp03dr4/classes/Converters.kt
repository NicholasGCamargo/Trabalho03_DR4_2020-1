package com.example.tp03dr4.classes

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun castToFloat(value: Boolean): Float{
        return if(value == true) 1f else 0f
    }

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