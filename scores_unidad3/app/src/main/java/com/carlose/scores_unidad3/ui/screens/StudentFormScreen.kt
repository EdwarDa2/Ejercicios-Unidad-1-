package com.carlose.scores_unidad3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.carlose.scores_unidad3.model.Student
import com.carlose.scores_unidad3.viewmodel.SchoolViewModel

@Composable
fun StudentFormScreen(
    studentId: Int? = null,
    viewModel: SchoolViewModel,
    onNavigateBack: () -> Unit
) {

    val studentToEdit = studentId?.let { viewModel.getStudentById(it) }

    var name by remember { mutableStateOf(studentToEdit?.name ?: "") }
    var lastName by remember { mutableStateOf(studentToEdit?.lastName ?: "") }
    var grade by remember { mutableStateOf(studentToEdit?.grade ?: "") }
    var group by remember { mutableStateOf(studentToEdit?.group ?: "") }
    var score by remember { mutableStateOf(studentToEdit?.score?.toString() ?: "") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = if (studentId == null) "Agregar Estudiante" else "Editar Estudiante",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Apellido") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = grade,
                onValueChange = { grade = it },
                label = { Text("Grado") },
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = group,
                onValueChange = { group = it },
                label = { Text("Grupo") },
                modifier = Modifier.weight(1f)
            )
        }

        OutlinedTextField(
            value = score,
            onValueChange = { score = it },
            label = { Text("Calificación") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(
            onClick = {
                val newScore = score.toDoubleOrNull() ?: 0.0

                if (studentId == null) {
                    viewModel.addStudent(
                        Student(id = 0, name = name, lastName = lastName, grade = grade, group = group, score = newScore)
                    )
                } else {
                    viewModel.updateStudent(
                        Student(id = studentId, name = name, lastName = lastName, grade = grade, group = group, score = newScore)
                    )
                }
                onNavigateBack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }
    }
}