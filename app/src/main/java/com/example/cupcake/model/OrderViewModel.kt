package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel: ViewModel() {
    private var _quantity = MutableLiveData<Int>(0)
    val quantity: LiveData<Int> = _quantity

    private var _flavour = MutableLiveData<String>("")
    val flavour: LiveData<String> = _flavour

    private var _date = MutableLiveData<String>("")
    val date: LiveData<String> = _date

    private var _price = MutableLiveData<Double>(0.0)
    val price: LiveData<Double> = _price

    fun setQuantity(numberCupcakes: Int){
        _quantity.value = numberCupcakes
    }

    fun setFlavour(desiredFlavour: String){
        _flavour.value = desiredFlavour
    }

    fun setDate(pickupDate: String){
        _date.value = pickupDate
    }
}