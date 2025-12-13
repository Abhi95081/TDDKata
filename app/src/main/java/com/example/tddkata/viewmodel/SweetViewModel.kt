package com.example.tddkata.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tddkata.Datamodel.Sweet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// -------------------- VIEWMODEL --------------------
class SweetViewModel : ViewModel() {

    private val _sweets = MutableStateFlow<List<Sweet>>(emptyList())
    val sweets: StateFlow<List<Sweet>> = _sweets

    init {
        loadSweets()
    }

    private fun loadSweets() {
        viewModelScope.launch {
            try {
                _sweets.value = ApiClient.api.getSweets()
            } catch (e: Exception) {
                e.printStackTrace()
                _sweets.value = emptyList()
            }
        }
    }

    fun purchaseSweet(id: Int) {
        viewModelScope.launch {
            try {
                ApiClient.api.purchaseSweet(id)
                loadSweets()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}