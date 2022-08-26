package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val PRICE_PER_CUPCAKE = 2.00
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

class OrderViewModel: ViewModel() {
    private var _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    private var _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> = _flavor

    private var _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private var _price = MutableLiveData<Double>()
    val price: LiveData<String> = Transformations.map(_price){
        NumberFormat.getCurrencyInstance().format(it)
    }

    val dateOptions = pickUpOptions()

    init {
        resetOrder()
    }

    fun setQuantity(numberCupcakes: Int){
        _quantity.value = numberCupcakes
        updatePrice()
    }

    fun setFlavor(desiredFlavour: String){
        _flavor.value = desiredFlavour
    }

    fun hasNoFlavorSet(): Boolean{
        return _flavor.value.isNullOrEmpty()
    }

    fun setDate(pickupDate: String){
        _date.value = pickupDate
        updatePrice()
    }

    fun pickUpOptions(): List<String>{
        val options = mutableListOf<String>()
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
        _flavor.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0
    }

    private fun updatePrice() {
        var calculatedPrice = (quantity.value ?: 0) * PRICE_PER_CUPCAKE
        // for same day delivery additional cost of 3
        if(dateOptions[0] == _date.value) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }
}
