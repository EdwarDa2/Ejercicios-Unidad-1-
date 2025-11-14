package com.carlose.scores_unidad3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.carlose.scores_unidad3.model.Student
import com.carlose.scores_unidad3.viewmodel.SchoolViewModel

@Composable
fun StatisticsScreen(viewModel: SchoolViewModel) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Promedios", "Rezago", "Top 3")

    Column {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title) }
                )
            }
        }

        when (selectedTabIndex) {
            0 -> GroupAverageView(viewModel.getAverageByGroup())
            1 -> GroupLaggingView(viewModel.getLaggingStudentByGroup())
            2 -> GroupTop3View(viewModel.getTop3StudentsByGroup())
        }
    }
}

@Composable
fun GroupAverageView(averages: Map<String, Double>) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item { Text("Promedio General por Grupo", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 8.dp)) }
        items(averages.toList()) { (group, avg) ->
            ListItem(
                headlineContent = { Text("Grupo $group") },
                trailingContent = { Text(String.format("%.2f", avg), style = MaterialTheme.typography.titleLarge) }
            )
            HorizontalDivider()
        }
    }
}

@Composable
fun GroupLaggingView(lagging: Map<String, Student?>) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item { Text("Alumno con Mayor Rezago", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 8.dp)) }
        items(lagging.toList()) { (group, student) ->
            student?.let {
                ListItem(
                    headlineContent = { Text("Grupo $group") },
                    supportingContent = { Text("${it.name} ${it.lastName}") },
                    trailingContent = { Text("${it.score}", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.titleMedium) }
                )
            }
        }
    }
}

@Composable
fun GroupTop3View(topStudents: Map<String, List<Student>>) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item { Text("Top 3 Alumnos por Grupo", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 8.dp)) }
        items(topStudents.toList()) { (group, list) ->
            Card(modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Grupo $group", style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(8.dp))
                    list.forEachIndexed { index, student ->
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("${index + 1}. ${student.name} ${student.lastName}")
                            Text("${student.score}", style = MaterialTheme.typography.bodyLarge)
                        }
                        if (index < list.size - 1) HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                    }
                }
            }
        }
    }
}