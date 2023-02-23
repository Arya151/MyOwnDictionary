package com.example.myowndictionary.presentation.authentication.logIn.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myowndictionary.R
import com.example.myowndictionary.presentation.authentication.logIn.AuthenticationViewModel

@Composable
fun CustomOtpField(idx: Int, authViewModel: AuthenticationViewModel) {


    val otpBox = Modifier
        .width(36.dp)
        .height(36.dp)
        .border(
            width = 2.dp,
            color = colorResource(id = R.color.light),
            shape = RoundedCornerShape(size = 4.dp)
        )

    val otpBoxStyle = Modifier
        .width(38.dp)
        .height(38.dp)

    val otpNum = TextStyle(
        textAlign = TextAlign.Center,
        color = colorResource(id = R.color.main),
        fontSize = 16.sp
    )

    BasicTextField(
        value = authViewModel.getEnteredOtp()[0],
        modifier = otpBoxStyle,
        onValueChange = {

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
    )

}