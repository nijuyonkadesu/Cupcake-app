package com.example.cupcake

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cupcake.model.OrderViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class ViewModelTests {
    @get:Rule // ViewModel cannot access main thread, that's where UI runs. Here have to explicitly specify to not use main thread
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test // purpose of test: to check if view model gets updated on calling setQuantity()
    fun quantity_twelve_cupcakes(){
        val viewModel = OrderViewModel()
        viewModel.quantity.observeForever {}
        viewModel.setQuantity(12)
        assertEquals(12, viewModel.quantity.value)
    }

    @Test // #1 update price on assigning quantity
    fun price_twelve_cupcakes(){
        val viewModel = OrderViewModel()
        viewModel.price.observeForever{}
        viewModel.setQuantity(12)
        /*
        When transforming a LiveData object, the code doesn't get called unless it absolutely has to be,
        this saves resources on a mobile device. The code will only be called if we 'observe' the object for changes.

        And therefore it is necessary to observer LiveData while testing
        */
        assertEquals("₹ 27.00", viewModel.price.value) //   what is this lol
    }


}