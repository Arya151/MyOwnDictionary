package com.example.myowndictionary.presentation.createCollection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myowndictionary.R

@Composable
fun CreateCollection() {

    var collectionName by remember { mutableStateOf("") }

    val cardColor = Brush.linearGradient(
        listOf(
            colorResource(id = R.color.main),
            colorResource(id = R.color.sub_main)
        )
    )

    Column() {
        Text("Please give name to your Collection")
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            Modifier
                .fillMaxSize()
                .background(brush = cardColor)
                .clip(RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.frame_book_new),
                contentDescription = "",
                modifier = Modifier
                    .padding(34.dp)
                    .fillMaxSize()
            )
            BasicTextField(
                value = collectionName,
                onValueChange = {
                },

            )

        }
        Spacer(modifier = Modifier.height(24.dp))


    }

}

@Preview
@Composable
fun ShowCreateCollection(){
    CreateCollection()
}