package com.example.myowndictionary.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myowndictionary.R
import com.example.myowndictionary.domain.Action

@Composable
fun ActionCard(action: Action) {
    Column(
        Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = action.icon),
            contentDescription = "icon",
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .size(48.dp)
                .padding(4.dp),
        )
        Text(
            text = action.title,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = colorResource(id = R.color.white),
            textAlign = TextAlign.Center
        )
    }
}