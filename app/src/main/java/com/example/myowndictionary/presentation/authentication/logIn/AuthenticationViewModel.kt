package com.example.myowndictionary.presentation.authentication.logIn

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthenticationViewModel:ViewModel() {

    private val _currentScreen: MutableLiveData<Int> = MutableLiveData()
    val currentScreen = _currentScreen

    private var otp = ""

    fun updateScreenNumber(num: Int){
        currentScreen.value = num
    }

    fun setTotalDigitsOfOtp(totalDigits: Int){
        for(i in 1..totalDigits){
            otp += " "
        }
    }

    fun updateEnteredOtp(idx: Int, digit: Char){
        var tempOtp = otp.toCharArray()
           tempOtp[idx] = digit
        otp = tempOtp.toString()
    }

    fun getEnteredOtp() = otp

}