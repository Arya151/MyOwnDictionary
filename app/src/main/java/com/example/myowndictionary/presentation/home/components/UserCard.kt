package com.example.myowndictionary.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myowndictionary.R

@Composable
fun UserCard() {

    Row(
        Modifier.fillMaxWidth().padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column() {
            Text(
                "Arya Bhavate",
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.simple)),
                color = colorResource(id = R.color.semi_white)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    "Total Word Collection ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.semi_white)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "87",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.semi_white)
                )
            }
        }
        Image(
            painter = painterResource(id = R.drawable.ic_user),
            contentDescription = "",
            Modifier
                .padding(8.dp)
                .size(56.dp)
        )
    }

}

@Preview
@Composable
fun ShowCard() {
    UserCard()
}