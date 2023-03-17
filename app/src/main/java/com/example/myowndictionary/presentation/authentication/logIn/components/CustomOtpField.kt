package com.example.myowndictionary.presentation.authentication.logIn.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myowndictionary.R
import com.example.myowndictionary.presentation.authentication.logIn.AuthenticationViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomOtpField(authViewModel: AuthenticationViewModel) {

    val otpBox = Modifier
        .width(36.dp)
        .height(36.dp)
        .border(
            width = 2.dp,
            color = colorResource(id = R.color.white),
            shape = RoundedCornerShape(size = 4.dp)
        )


    val otpNum = TextStyle(
        textAlign = TextAlign.Center,
        color = colorResource(id = R.color.white),
        fontSize = 16.sp
    )

    val arrFocus = mutableListOf<FocusRequester>()
    val arr = mutableListOf<Unit>()
    val arrState = mutableListOf<MutableState<String>>()

    for (i in 0 until authViewModel.getEnteredOtp().value!!.size) {
        arrFocus.add(remember { FocusRequester() })
        arrState.add(remember { mutableStateOf(authViewModel.getEnteredOtp().value!![i]) })
        arr.add(
            Row() {
                BasicTextField(
                    value = arrState[i].value.toString(),
                    modifier = Modifier
                        .width(38.dp)
                        .height(38.dp)
                        .focusRequester(arrFocus[i])
                        .onKeyEvent {
                            if (it.key == Key.Delete || it.key == Key.Backspace) {
                                // Action When Click on Delete
                                if (i != 0 && authViewModel.getEnteredOtp().value!![i].isEmpty()) {
                                    arrFocus[i - 1].requestFocus()
                                }
                                true
                            } else {
                                false
                            }
                        },
                    cursorBrush = SolidColor(colorResource(id = R.color.white)),
                    onValueChange = {
                        if (it.length == 1) {
                            authViewModel.updateEnteredOtp(i, it[0].toString())
                            arrState[i].value = it[0].toString()
                        } else if (it.isEmpty()) {
                            authViewModel.updateEnteredOtp(i, "")
                            arrState[i].value = ""
                        }
                        if (it.length == 1 && i + 1 != authViewModel.getEnteredOtp().value!!.size) arrFocus[i + 1].requestFocus()
                        //if( authViewModel.ot == authViewModel.getEnteredOtp().size) authViewModel.enableVerifyBtn(true) else authViewModel.enableVerifyBtn(false)
                    },
                    textStyle = otpNum,
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = otpBox,
                            contentAlignment = Alignment.Center
                        ) {
                            innerTextField()
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
                Spacer(modifier = Modifier.width(6.dp))

            }
        )
    }


}