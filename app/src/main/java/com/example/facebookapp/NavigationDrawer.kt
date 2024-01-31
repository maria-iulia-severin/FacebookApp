package com.example.facebookapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.facebookapp.data.Person
import com.example.facebookapp.data.Shortcut

@Composable
fun NavigationDrawer() {

    //val randomItems = remember { mutableStateOf(getRandomItems(10)) }
    val randomItems = remember { mutableStateOf(Person.getPeople()) }
    val shortcuts = remember { mutableStateOf(Shortcut.getShortcuts()) }

    Column {

        Row(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.user), contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(8.dp)
                    .clip(CircleShape)

            )
            Text(text = "Iulia", modifier = Modifier.padding(8.dp))
        }

        Text(
            text = "Your Shortcuts",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        )


        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(randomItems.value) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(4.dp)
                        .width(60.dp)
                ) {
                    Image(
                        painter = painterResource(id = it.resId),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(
                                CircleShape
                            )

                    )
                    Text(text = it.name)
                }
            }
        }


        Text(
            text = "All Shortcuts",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(shortcuts.value) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = it.resId),
                        tint = it.tint,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = it.title, fontSize = 12.sp)
                }
            }
        }

    }
}