package com.dev.criminalintent.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.dev.criminalintent.Crime
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface CrimeDao {
    @Query("SELECT * FROM crime")
    fun getCrimes(): Flow<List<Crime>>

    @Query("SELECT * FROM crime WHERE id=(:idByteArray)")
    fun getCrime(idByteArray: ByteArray): Crime

    @Query("UPDATE crime SET title=:title, isSolved=:isSolved WHERE id=(:idByteArray)")
    fun updateCrime(title: String, isSolved: String, idByteArray: ByteArray)
}