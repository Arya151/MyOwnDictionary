package com.example.myowndictionary.presentation.authentication.logIn

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthenticationViewModel : ViewModel() {

    private val _currentScreen: MutableLiveData<Int> = MutableLiveData()
    val currentScreen = _currentScreen

    private var verificationId = ""

    private val _otp: MutableLiveData<Array<String>> = MutableLiveData()
    val otp = _otp

    fun updateScreenNumber(num: Int) {
        currentScreen.value = num
    }

    fun setTotalDigitsOfOtp(totalDigits: Int) {
        otp.value =  Array<String>(totalDigits) { "" }
    }

    fun updateEnteredOtp(idx: Int, digit: String) {
        otp.value?.set(idx, digit)
    }

    fun getEnteredOtp() = otp

    fun setVerificationId(id : String){
        verificationId = id
    }

    fun getVerificationId() = verificationId

}