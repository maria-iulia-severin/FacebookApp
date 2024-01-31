package com.example.facebookapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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

    val people = remember { mutableStateOf(Person.getPeople()) }
    val shortcuts = remember { mutableStateOf(Shortcut.getShortcuts()) }

    LazyColumn {
        item {
            Row(
                modifier = Modifier
                    .background(Color.White)
                    .height(50.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = R.drawable.user), contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(8.dp)
                        .clip(CircleShape)

                )
                Text(text = "Iulia")
            }
        }
        item {
            Text(
                text = "Your Shortcuts",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
            )
        }
        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(people.value) {
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
        }
        item {
            Text(
                text = "All Shortcuts",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
            )
        }
        item {
//            LazyVerticalGrid(
//                columns = GridCells.FixedSize(2.dp)
//            ) {
//                items(shortcuts.value) {
//                    Column(
//                        verticalArrangement = Arrangement.Center,
//                        horizontalAlignment = AbsoluteAlignment.Left,
//                        modifier = Modifier.background(Color.White)
//                    ) {
//                        Image(painter = painterResource(id = it.resId), contentDescription = null)
//                        Text(text = it.title)
//                    }
//                }
//            }
        }
    }
}