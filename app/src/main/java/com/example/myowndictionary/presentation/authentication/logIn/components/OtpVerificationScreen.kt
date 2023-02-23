package com.example.myowndictionary.presentation.authentication.logIn.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myowndictionary.R
import com.example.myowndictionary.presentation.authentication.logIn.AuthenticationViewModel

@Composable
fun OtpVerificationScreen(authViewModel: AuthenticationViewModel) {

    var enteredOtp by remember { mutableStateOf("      ") }
    var otpStatusText by remember { mutableStateOf("Verify Otp") }
    var sendOtpEnabled by remember { mutableStateOf(false) }
    var currentFocus by remember { mutableStateOf(1) }

    val otpBox = Modifier
        .width(36.dp)
        .height(36.dp)
        .border(
            width = 2.dp,
            color = colorResource(id = R.color.light),
            shape = RoundedCornerShape(size = 4.dp)
        )

    val otpNum = TextStyle(
        textAlign = TextAlign.Center,
        color = colorResource(id = R.color.main),
        fontSize = 16.sp
    )

    val otpBoxStyle = Modifier
        .width(38.dp)
        .height(38.dp)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Otp Verification",
            color = colorResource(id = R.color.sub_main),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Otp has been sent to +919422848769",
            color = colorResource(id = R.color.light),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(24.dp))

        LazyRow() {
            items(count = authViewModel.getEnteredOtp().length,
                itemContent = { item ->

            })
        }
                    /*  Row() {
                          BasicTextField(
                              value = enteredOtp[0].toString(),
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
                          Spacer(modifier = Modifier.width(6.dp))
                          BasicTextField(
                              value = enteredOtp[1].toString(),
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
                          Spacer(modifier = Modifier.width(6.dp))
                          BasicTextField(
                              value = enteredOtp[2].toString(),
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
                          Spacer(modifier = Modifier.width(6.dp))
                          BasicTextField(
                              value = enteredOtp[3].toString(),
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
                          Spacer(modifier = Modifier.width(6.dp))
                          BasicTextField(
                              value = enteredOtp[4].toString(),
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
                          Spacer(modifier = Modifier.width(6.dp))
                          BasicTextField(
                              value = enteredOtp[5].toString(),
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
                      }*/

                    Spacer (modifier = Modifier.height(24.dp))
                    Button (
                    onClick = {

                    },
                enabled = sendOtpEnabled,
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.main)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = otpStatusText, fontSize = 18.sp, color = Color.White)
            }
        }

    }

    @Preview
    @Composable
    fun PreviewOtpScreen() {
        OtpVerificationScreen(authViewModel = AuthenticationViewModel())
    }