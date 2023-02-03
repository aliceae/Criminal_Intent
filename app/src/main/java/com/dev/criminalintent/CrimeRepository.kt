package com.dev.criminalintent

import android.content.Context
import androidx.room.Room
import com.dev.criminalintent.database.CrimeDatabase
import kotlinx.coroutines.flow.Flow

private const val DATABASE_NAME = "crime-database"

class CrimeRepository private constructor(context: Context) {

    private val database: CrimeDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            CrimeDatabase::class.java,
            DATABASE_NAME
        )
        .createFromAsset(DATABASE_NAME)
        .allowMainThreadQueries()
        .build()

    fun getCrimes(): Flow<List<Crime>> = database.crimeDao().getCrimes()

    fun getCrime(id: ByteArray): Crime = database.crimeDao().getCrime(id)

    companion object {
        private var INSTANCE: CrimeRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        fun get(): CrimeRepository {
            return INSTANCE ?: throw IllegalAccessException("CrimeRepository must be initialized")
        }
    }
}
