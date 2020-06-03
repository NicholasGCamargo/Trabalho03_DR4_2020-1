package com.example.tp03dr4.classes

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun castToFloat(value: Boolean): Float{
        return if(value == true) 1f else 0f
    }
}