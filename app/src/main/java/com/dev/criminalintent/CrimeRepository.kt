package com.dev.criminalintent

import android.content.Context
import androidx.room.Room
import com.dev.criminalintent.database.CrimeDatabase
import com.dev.criminalintent.database.migration1_2
import com.dev.criminalintent.database.migration2_3
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.Date

private const val DATABASE_NAME = "crime-database"

class CrimeRepository private constructor(
    context: Context,
    private val coroutineScope: CoroutineScope = GlobalScope
) {

    private val database: CrimeDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            CrimeDatabase::class.java,
            DATABASE_NAME
        )
//        .createFromAsset(DATABASE_NAME)
        .addMigrations(migration1_2, migration2_3)
        .allowMainThreadQueries()
        .build()

    fun getCrimes(): Flow<List<Crime>> = database.crimeDao().getCrimes()

    fun getCrime(id: ByteArray): Crime = database.crimeDao().getCrime(id)

    fun updateCrime(
        title: String,
        isSolved: String,
        date: Date,
        idByteArray: ByteArray,
        suspect: String,
        photoFileName: String?
    ) {
        coroutineScope.launch {
            database.crimeDao()
                .updateCrime(title, isSolved, date, idByteArray, suspect, photoFileName)
        }
    }

    fun addCrime(
        title: String,
        isSolved: String,
        date: Date,
        idByteArray: ByteArray,
        suspect: String,
        photoFileName: String?
    ) {
        database.crimeDao().addCrime(title, isSolved, date, idByteArray, suspect, photoFileName)
    }

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
