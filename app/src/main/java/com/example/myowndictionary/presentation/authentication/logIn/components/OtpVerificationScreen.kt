package com.example.myowndictionary.presentation.authentication.logIn.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.myowndictionary.R
import com.example.myowndictionary.presentation.authentication.logIn.AuthenticationViewModel
import com.example.myowndictionary.presentation.authentication.logIn.LogInActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import java.lang.Exception


@Composable
fun OtpVerificationScreen(
    lifecycleOwner: LifecycleOwner,
    authViewModel: AuthenticationViewModel
) {
    val mContext = LocalContext.current
    var otpStatusText by remember { mutableStateOf("") }
    //val enteredOtp by authViewModel.getEnteredOtp().observeAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Otp Verification",
            color = colorResource(id = R.color.semi_white),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Otp has been sent to +919422848769",
            color = colorResource(id = R.color.white),
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            Modifier.height(56.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            authViewModel.setTotalDigitsOfOtp(6)
            CustomOtpField(authViewModel = authViewModel)
        }

        Text(
            text = otpStatusText,
            color = colorResource(id = R.color.red_light),
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

        Button(
            onClick = {
                var enteredOtp = ""
                for (digit in authViewModel.getEnteredOtp().value!!) {
                    enteredOtp += digit
                }
                if(authViewModel.getVerificationId().isNotEmpty()) {
                    try{
                        val credential = PhoneAuthProvider.getCredential(authViewModel.getVerificationId(), enteredOtp)
                        val mAuth = FirebaseAuth.getInstance()
                        mAuth.signInWithCredential(credential)
                            .addOnCompleteListener(OnCompleteListener<AuthResult?> { task ->
                                if (task.isSuccessful) {
                                    // if the code is correct and the task is successful
                                    // we are sending our user to new activity.
                                    Log.d("abx", "Log In success >>")
                                    authViewModel.updateScreenNumber(3)
                                } else {
                                    // if the code is not correct then we are
                                    // displaying an error message to the user.
                                    Log.d("abx", "Log In fail >>")
                                    otpStatusText = "Invalid Otp"
                                }
                            })
                    }catch (e:Exception){
                        otpStatusText = "Invalid Otp"
                        Log.e("Error","Exception: $e")
                        Toast.makeText(mContext,e.localizedMessage,Toast.LENGTH_LONG).show()
                    }

                }

            },
            enabled = true,
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.white)),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
        ) {
            Text(text = "Verify Otp", fontSize = 18.sp, color = colorResource(id = R.color.main))
        }
    }

    authViewModel.otp.observe(lifecycleOwner, Observer {
        otpStatusText = ""
    })

}

@Preview
@Composable
fun PreviewOtpScreen() {
    OtpVerificationScreen(
        lifecycleOwner = LogInActivity() as LifecycleOwner,
        authViewModel = AuthenticationViewModel()
    )
}