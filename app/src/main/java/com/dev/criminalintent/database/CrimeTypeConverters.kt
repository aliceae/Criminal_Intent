package com.dev.criminalintent.database

import androidx.room.TypeConverter
import java.nio.ByteBuffer
import java.util.*

class CrimeTypeConverters {

    @TypeConverter
    fun toId(idBlob:ByteArray):UUID {
        val bb = ByteBuffer.wrap(idBlob)
        return UUID(bb.long, bb.long)
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