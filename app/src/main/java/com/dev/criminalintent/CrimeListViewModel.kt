package com.dev.criminalintent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.nio.ByteBuffer

private val crimeRepository = CrimeRepository.get()

class CrimeListViewModel : ViewModel() {

    private val _crimes: MutableStateFlow<List<Crime>> = MutableStateFlow(emptyList())

    val crimes: StateFlow<List<Crime>>
        get() = _crimes.asStateFlow()

    init {
        viewModelScope.launch {
            crimeRepository.getCrimes().collect {
                _crimes.value = it
            }
        }
    }

    fun addCrime(crime: Crime) {
        val bb = ByteBuffer.wrap(ByteArray(16))
        bb.putLong(crime.id.mostSignificantBits)
        bb.putLong(crime.id.leastSignificantBits)
        crimeRepository.addCrime(crime.title,crime.isSolved.toString(),crime.date,bb.array())
    }
}