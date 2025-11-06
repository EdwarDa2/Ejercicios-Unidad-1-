package com.example.EdwarDa.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.EdwarDa.data.Student


@OptIn(ExperimentalMaterial3Api::class)


@Composable
fun HomeView(navController: NavController) {
    var students = listOf<Student>(
        Student(id = 1L, name = "Emilia", description = "JEFA"),
        Student(2L, "Nadia", "Amiga de emilia"),
        Student(3L, "Antonio", "Amigo de la amiga"),
        Student(id = 4L, name = "Tony", description = "Amigo del amigo de la amiga"),
        Student(5L, "Angel", "Amigo del amigo del amigo de la amiga"),
        Student(6L, "Emma", "Amiga del amigo del amigo del amigo de la amiga"),
    )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Dashboard")
                }
            )
        }
    ) {
        Content(it, students, navController)
    }
}


@Composable
fun Content(paddingValues: PaddingValues, students: List<Student>, navController: NavController) {
    LazyColumn(

        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 12.dp)
    ) {
        items(students) { student ->
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(color = Color.Red)
                        .wrapContentSize(Alignment.Center)
                        .clickable { navController.navigate("Details/${student.name}") }

                ) {
                    Text(student.name, fontSize = 22.sp)
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

    }

}
