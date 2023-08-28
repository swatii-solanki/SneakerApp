package com.crickbuzztest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crickbuzztest.data.datasource.repo.SneakerRepo
import com.crickbuzztest.data.model.Cart
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
class CartViewModel @Inject constructor(private val repo: SneakerRepo) : ViewModel() {

    private val _cartState: MutableStateFlow<Resource<List<Cart>>> =
        MutableStateFlow(Resource.Loading)
    val cartState = _cartState.asStateFlow()

    private val _cartPrice: MutableStateFlow<Int?> = MutableStateFlow(null)
    val cartPrice = _cartPrice.asStateFlow()

    init {
        viewModelScope.launch {
            repo.fetchCartList().flowOn(Dispatchers.IO).collectLatest {
                _cartState.value = Resource.Success(it)
            }
        }

        fetchTotalPrice()
    }

    private fun fetchTotalPrice() {
        viewModelScope.launch {
            repo.calculateCartPrice().flowOn(Dispatchers.IO).collectLatest {
                _cartPrice.value = it
            }
        }
    }

    fun removeItemFromCart(cart: Cart) {
        viewModelScope.launch {
            repo.removeCartItem(cart)
        }
    }
}
