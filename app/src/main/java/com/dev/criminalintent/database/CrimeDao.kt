package com.dev.criminalintent.database

import androidx.room.Dao
import androidx.room.Query
import com.dev.criminalintent.Crime
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface CrimeDao {
    @Query("SELECT * FROM crime")
    fun getCrimes(): Flow<List<Crime>>

    @Query("SELECT * FROM crime WHERE id=(:idByteArray)")
    fun getCrime(idByteArray: ByteArray): Crime

    @Query("UPDATE crime SET title=:title, isSolved=:isSolved, date=:date, suspect=:suspect,photoFileName=:photoFileName WHERE id=(:idByteArray)")
    fun updateCrime(
        title: String, isSolved: String, date: Date, idByteArray: ByteArray, suspect: String,
        photoFileName: String?
    )

    @Query("INSERT into crime VALUES (:idByteArray,:title,:date,:isSolved,:suspect,:photoFileName)")
    fun addCrime(
        title: String,
        isSolved: String,
        date: Date,
        idByteArray: ByteArray,
        suspect: String,
        photoFileName: String?
    )
}