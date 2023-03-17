package com.example.myowndictionary.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myowndictionary.R

@Composable
fun HomeScreen() {

    val cardColor = Brush.linearGradient(
        listOf(
            colorResource(id = R.color.main),
            colorResource(id = R.color.sub_main)
        )
    )

    Column(
        Modifier
            .fillMaxSize()
            .background(cardColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserCard()
        Box(
            Modifier
                .fillMaxHeight()
                .padding(start = 12.dp, end = 12.dp)
                .shadow(4.dp)
                .clip(RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp))
                .background(colorResource(id = R.color.white))
                .padding(start = 12.dp, end = 12.dp, top = 14.dp)
        ) {
            HomeRecyclerView()
        }
    }
}

@Preview
@Composable
fun ShowHomeScreen() {
    HomeScreen()
}