package com.example.myowndictionary.presentation.authentication.logIn.components

import android.app.Activity
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myowndictionary.R
import com.example.myowndictionary.presentation.authentication.logIn.AuthenticationViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken


@Composable
fun LogInScreen(activity: Activity, auth: FirebaseAuth, authViewModel: AuthenticationViewModel) {

    var currentScreen = authViewModel.currentScreen.observeAsState()
    var enteredOtp by remember { mutableStateOf("")}
    var verificationId = ""

    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onCodeSent(sId: String, forceResendingToken: ForceResendingToken) {
            super.onCodeSent(sId, forceResendingToken)
            verificationId = sId
            authViewModel.updateScreenNumber(2);
        }

        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
            Log.d("abx", "verification complete - $phoneAuthCredential")
            val code: String? = phoneAuthCredential.smsCode;

            // checking if the code
            // is null or not.
            if (code != null) {
                enteredOtp = code;
            }
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            Log.d("abx", "verification failed - $p0")
        }

    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(
            modifier = Modifier
                .height(24.dp)
                .fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
        ) {

            Column(
                Modifier
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    "LogIn",
                    color = colorResource(R.color.main),
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier
                    .height(52.dp)
                    .fillMaxWidth())

                AnimatedVisibility(visible = currentScreen.value == 1) {
                    PhoneNumberScreen(activity = activity, auth = auth, callbacks = callbacks)
                }
                AnimatedVisibility(visible = currentScreen.value == 2) {
                    OtpVerificationScreen(authViewModel)
                }

                Spacer(modifier = Modifier
                    .height(54.dp)
                    .fillMaxWidth())

                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(45.dp),
                    painter = painterResource(id = R.drawable.book_boy),
                    contentDescription = "books"
                )
            }

        }

    }

}

private fun signInWithCredential(credential: PhoneAuthCredential) {
    // inside this method we are checking if
    // the code entered is correct or not.
    val mAuth = FirebaseAuth.getInstance()
    mAuth.signInWithCredential(credential)
        .addOnCompleteListener(OnCompleteListener<AuthResult?> { task ->
            if (task.isSuccessful) {
                // if the code is correct and the task is successful
                // we are sending our user to new activity.
               Log.d("abx","Log In success ")
            } else {
                // if the code is not correct then we are
                // displaying an error message to the user.
                Log.d("abx","Log In fail ")

            }
        })
}

@Preview
@Composable
fun PreviewLogInScreen() {
    //LogInScreen(activity = )
}
