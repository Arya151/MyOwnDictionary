package com.example.myowndictionary.presentation.authentication.logIn.components

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import com.example.myowndictionary.R
import com.example.myowndictionary.presentation.authentication.logIn.AuthenticationViewModel
import com.example.myowndictionary.presentation.authentication.logIn.LogInActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import java.lang.Exception


@Composable
fun LogInScreen(activity: Activity, auth: FirebaseAuth, authViewModel: AuthenticationViewModel) {

    var currentScreen = authViewModel.currentScreen.observeAsState()
    var mContext = LocalContext.current

    val cardColor = Brush.linearGradient(
        listOf(
            colorResource(id = R.color.main),
            colorResource(id = R.color.sub_main)
        )
    )

    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onCodeSent(sId: String, forceResendingToken: ForceResendingToken) {
            super.onCodeSent(sId, forceResendingToken)
            authViewModel.setVerificationId(sId)
            authViewModel.updateScreenNumber(2);
            Log.d("abx"," verificationId - $sId")
        }

        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
            Log.d("abx", "verification complete - $phoneAuthCredential")
            val code: String? = phoneAuthCredential.smsCode;

            // checking if the code
            // is null or not.
            if (code != null) {
                var enteredOtp = ""
                for(digit in authViewModel.getEnteredOtp().value!!){
                    enteredOtp += digit
                }
                if(authViewModel.getVerificationId().isEmpty()) return
                // below line is used for getting
                // credentials from our verification id and code.
                try{
                    val credential = PhoneAuthProvider.getCredential(authViewModel.getVerificationId(), enteredOtp)
                    // after getting credential we are
                    // calling sign in method.
                    val mAuth = FirebaseAuth.getInstance()
                    mAuth.signInWithCredential(credential)
                        .addOnCompleteListener(OnCompleteListener<AuthResult?> { task ->
                            if (task.isSuccessful) {
                                // if the code is correct and the task is successful
                                // we are sending our user to new activity.
                                Log.d("abx","Log In success ")
                                authViewModel.updateScreenNumber(3)

                            } else {
                                // if the code is not correct then we are
                                // displaying an error message to the user.
                                Log.d("abx","Log In fail ")

                            }
                        })
                }catch (e: Exception){
                    Log.e("Error","Exception: $e")
                    Toast.makeText(mContext,e.localizedMessage, Toast.LENGTH_LONG).show()
                }

            }
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            Log.d("abx", "verification failed - $p0")
        }

    }

    Column(
        Modifier
            .fillMaxSize()
            .background(brush = cardColor)
    ) {
        Spacer(
            modifier = Modifier
                .height(24.dp)
                .fillMaxWidth()
        )
        Column(
            Modifier
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                "LogIn",
                color = colorResource(R.color.white),
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
                OtpVerificationScreen(activity as LifecycleOwner , authViewModel)
            }

        }

        Spacer(modifier = Modifier
            .height(52.dp)
            .fillMaxWidth())

        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.card_oval_top),
                contentDescription = "",
                Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,)
            Spacer(modifier = Modifier.height(68.dp))
            Image(
                modifier = Modifier.size(240.dp),
                painter = painterResource(id = R.drawable.book_boy),
                contentDescription = "books"
            )
        }

    }

}


@Preview
@Composable
fun PreviewLogInScreen() {
    LogInScreen(activity = LogInActivity(),
        FirebaseAuth.getInstance(), AuthenticationViewModel()
    )
}
