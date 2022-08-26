package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class OrderViewModel: ViewModel() {
    private var _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    private var _flavour = MutableLiveData<String>()
    val flavour: LiveData<String> = _flavour

    private var _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private var _price = MutableLiveData<Double>()
    val price: LiveData<Double> = _price

    val dateOptions = pickUpOptions()

    init {
        resetOrder()
    }

    fun setQuantity(numberCupcakes: Int){
        _quantity.value = numberCupcakes
    }

    fun setFlavour(desiredFlavour: String){
        _flavour.value = desiredFlavour
    }

    fun hasNoFlavourSet(): Boolean{
        return _flavour.value.isNullOrEmpty()
    }

    fun setDate(pickupDate: String){
        _date.value = pickupDate
    }

    fun pickUpOptions(): List<String>{
        var options = mutableListOf<String>()
        val calendar = Calendar.getInstance() // current date
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        repeat(4){
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }
    fun resetOrder() {
        _quantity.value = 0
        _flavour.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0
    }
}