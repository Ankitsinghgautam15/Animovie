package com.example.animovie.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.animovie.R

@Composable
fun BuildScreen(navHostController: NavHostController, modifier: Modifier){
    Box (modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.banner),
            contentDescription = "",
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(16.dp)
                .background(color = Color.White.copy(0.4f))
                .border(0.4.dp, Color.White, RoundedCornerShape(topEnd = 16.dp))
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to the Movie App",
                modifier = modifier.padding(8.dp),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
            Button(
                onClick = {
                    navHostController.navigate("Movie screen")
                },
                modifier = modifier
                    .padding(bottom = 55.dp, start = 8.dp, end = 8.dp, top = 55.dp)
                    .fillMaxWidth()
                    .background(color = Color.Transparent)
                    .border(2.dp, Color.White)
            ) {
                Text(text = "Get Started")
            }
        }
    }
}