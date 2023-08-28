package com.crickbuzztest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crickbuzztest.data.datasource.repo.SneakerRepo
import com.crickbuzztest.data.model.Cart
import com.crickbuzztest.data.model.Sneaker
import com.crickbuzztest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: SneakerRepo) : ViewModel() {

    private val _sneakersState: MutableStateFlow<Resource<List<Sneaker>>> =
        MutableStateFlow(Resource.Loading)
    val sneakersState = _sneakersState.asStateFlow()

    init {
        fetchSneakerListFromRemote()
        viewModelScope.launch {
            repo.fetchSneakerList().flowOn(Dispatchers.IO).collectLatest {
                _sneakersState.value = Resource.Success(it)
            }
        }
    }

    private fun fetchSneakerListFromRemote() {
        viewModelScope.launch {
            repo.fetchSneakerListFromRemote()
        }
    }

    fun insertIntoCart(sneaker: Sneaker) {
        viewModelScope.launch {
            repo.saveCartItem(
                Cart(
                    sneaker.id,
                    sneaker.retailPrice,
                    sneaker.name,
                    sneaker.imageUrl
                )
            )
        }
    }
}
