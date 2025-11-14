package com.carlose.scores_unidad3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.carlose.scores_unidad3.viewmodel.SchoolViewModel

@Composable
fun DashboardScreen(
    viewModel: SchoolViewModel,
    onAddClick: () -> Unit,
    onEditClick: (Int) -> Unit
) {
    val students by viewModel.students.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) { padding ->
        if (students.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No hay estudiantes registrados")
            }
        } else {
            LazyColumn(modifier = Modifier.padding(padding)) {
                items(students) { student ->
                    Card(
                        modifier = Modifier.padding(8.dp).fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "${student.name} ${student.lastName}",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(text = "Grado: ${student.grade} - Grupo: ${student.group}")
                                Text(
                                    text = "Nota: ${student.score}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            IconButton(onClick = { onEditClick(student.id) }) {
                                Icon(Icons.Default.Edit, "Editar")
                            }
                            IconButton(onClick = { viewModel.deleteStudent(student.id) }) {
                                Icon(Icons.Default.Delete, "Eliminar", tint = MaterialTheme.colorScheme.error)
                            }
                        }
                    }
                }
            }
        }
    }
}