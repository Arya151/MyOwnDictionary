package com.example.myowndictionary.presentation.authentication.logIn.components

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myowndictionary.R
import com.example.myowndictionary.countryCodeList
import com.example.myowndictionary.validDigitsForCountryCode
import com.example.myowndictionary.validDigitsForPhoneNumber
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

@Composable
fun PhoneNumberScreen(
    activity: Activity,
    auth: FirebaseAuth,
    callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
) {

    var countryCode by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var otpStatusText by remember { mutableStateOf("Send Otp") }
    var sendOtpEnabled by remember { mutableStateOf(false) }
    var errorMsg by remember { mutableStateOf("") }

    Column() {
        Text(
            text = "Enter Your Phone Number",
            color = colorResource(id = R.color.sub_main),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))

        Row() {
            Column() {
                Text(
                    "Country Code",
                    fontSize = 12.sp,
                    color = Color.Gray,
                )
                Spacer(modifier = Modifier.height(4.dp))
                BasicTextField(
                    cursorBrush = SolidColor(Color.White),
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = colorResource(id = R.color.main)
                    ),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                        ) {
                            if (countryCode.isEmpty()) {
                                Text(
                                    text = "91",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.White
                                )
                            }
                            innerTextField()
                        }
                    },
                    modifier = Modifier
                        .background(colorResource(id = R.color.light))
                        .width(100.dp),
                    value = countryCode,
                    onValueChange = { value: String ->
                        var lengthCorrect = false
                        var digitCorrect = true
                        if (value.length in 0..5) lengthCorrect = true
                        for (i in value) {
                            if (!validDigitsForCountryCode.contains(i)) {
                                digitCorrect = false
                            }
                        }

                        if (lengthCorrect && digitCorrect) countryCode = value

                        errorMsg =
                            if (!countryCodeList.contains(countryCode)) "Invalid Country Code" else ""
                        sendOtpEnabled =
                            countryCodeList.contains(countryCode) && phoneNumber.length in 10..13
                    },
                    maxLines = 1,
                    singleLine = true,/*
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = colorResource(id = R.color.main),
                            placeholderColor = Color.White,
                            unfocusedIndicatorColor = Color.White,
                            cursorColor = Color.White),*/
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }

            Spacer(modifier = Modifier.width(4.dp))
            Column() {
                Text(
                    "Phone Number",
                    fontSize = 12.sp,
                    color = Color.Gray,
                )
                Spacer(modifier = Modifier.height(4.dp))
                BasicTextField(
                    cursorBrush = SolidColor(Color.White),
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = colorResource(id = R.color.main)
                    ),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                        ) {
                            if (phoneNumber.isEmpty()) {
                                Text(
                                    text = "9765....",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.White
                                )
                            }
                            innerTextField()
                        }
                    },
                    modifier = Modifier.background(colorResource(id = R.color.light)),
                    value = phoneNumber,
                    onValueChange = { value: String ->
                        var lengthCorrect = false
                        var digitCorrect = true
                        if (value.length in 0..13) lengthCorrect = true
                        for (i in value) {
                            if (!validDigitsForPhoneNumber.contains(i)) {
                                digitCorrect = false
                            }
                        }

                        if (lengthCorrect && digitCorrect) phoneNumber = value

                        sendOtpEnabled =
                            countryCodeList.contains(countryCode) && phoneNumber.length in 10..13
                    },
                    maxLines = 1,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }

        }

        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = errorMsg,
            color = colorResource(id = R.color.red_light),
            fontSize = 12.sp,
            modifier = Modifier.padding(2.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                if (phoneNumber.trim().replace("\\s".toRegex(), "").length in 10..13) {
                    // send otp
                    val options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber("+$countryCode$phoneNumber") // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(activity) // Activity (for callback binding)
                        .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
                        .build()
                    PhoneAuthProvider.verifyPhoneNumber(options)

                    CircularProgressIndicator(activity)
                    otpStatusText = "Sending Otp..."
                } else {
                    errorMsg = "Invalid phone number"
                }
            },
            enabled = sendOtpEnabled,
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.main)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = otpStatusText, fontSize = 18.sp, color = Color.White)
        }
    }

}