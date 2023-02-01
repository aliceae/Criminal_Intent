package com.dev.criminalintent

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

private const val TAG = "CrimeListViewModel"

private val crimeRepository = CrimeRepository.get()

class CrimeListViewModel : ViewModel() {
    val crimes = mutableListOf<Crime>()

    init {
        Log.d(TAG, "================init starting")
        viewModelScope.launch {
            Log.d(TAG, "coroutine launched")
            crimes += loadCrimes()
            Log.d(TAG, "Loading crimes finished")
        }
    }

    suspend fun loadCrimes(): List<Crime> {
//        val result = mutableListOf<Crime>()
//        Log.d(TAG, "123")
//        delay(5000)
//        for (i in 0 until 100) {
//            val crime = Crime(
//                id = UUID.randomUUID(),
//                title = "Crime #$i",
//                date = Date(),
//                isSolved = i % 2 == 0
//
//            )
//            result += crime
//        }
//        return result
        return crimeRepository.getCrimes()
    }
}