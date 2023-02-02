package com.dev.criminalintent.database

import androidx.room.TypeConverter
import java.util.Date
import java.util.UUID

class CrimeTypeConverters {

    @TypeConverter
    fun toId(idBlob:ByteArray):UUID {
        return UUID.nameUUIDFromBytes(idBlob)
    }

    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long): Date {
        return Date(millisSinceEpoch)
    }

    @TypeConverter
    fun fromIsSolved(isSolved: Boolean): String {
        return if (isSolved) "true" else "false"
    }

    @TypeConverter
    fun toIsSolved(isSolvedStr: String): Boolean {
        return isSolvedStr == "true"
    }


}