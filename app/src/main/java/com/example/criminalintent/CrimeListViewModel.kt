package com.example.criminalintent

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.UUID
import androidx.lifecycle.ViewModel


class CrimeListViewModel : ViewModel() {
    private val crimes = mutableListOf<Crime>()
    private val currentTimeMillis = System.currentTimeMillis()
    private val currentDate = java.util.Date(currentTimeMillis)
    init {
        viewModelScope.launch {
            crimes+=loadCrime()
        }
    }

    fun loadCrime():List<Crime>{
        val result = mutableListOf<Crime>()
        for (i in 0 until 100) {
            val crime = Crime(
                id = UUID.randomUUID(),
                title = "Crime #$i",
                date = currentDate,
                isSolved = i % 2 == 0,
                requiresPolice = i % 11 == 0
            )
            result += crime
        }
        return result
    }
}