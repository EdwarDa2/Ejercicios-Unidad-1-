package com.carlose.scores_unidad3.viewmodel

import androidx.lifecycle.ViewModel
import com.carlose.scores_unidad3.model.Student
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SchoolViewModel : ViewModel() {
    private val _students = MutableStateFlow<List<Student>>(listOf(
        Student(id = 1, "Carlos", lastName = "Pérez", grade = "1", group = "A", score = 85.0),
        Student(id = 2, name = "Ana", lastName = "López", grade = "1", group = "A", score = 92.5),
        Student(id = 3, name = "Jorge", lastName = "Díaz", grade = "1", group = "B", score = 70.0),
        Student(id = 4, name = "Maria", lastName = "Gómez", grade = "2", group = "A", score = 98.0),
        Student(id = 5, name = "Luis", lastName = "Rios", grade = "2", group = "B", score = 60.0)
    ))


    val students = _students.asStateFlow()

    fun addStudent(student: Student) {
        val newId = (_students.value.maxOfOrNull { it.id } ?: 0) + 1
        val newStudent = student.copy(id = newId)
        _students.value = _students.value + newStudent
    }

    fun deleteStudent(id: Int) {
        _students.value = _students.value.filter { it.id != id }
    }

    fun updateStudent(updatedStudent: Student) {
        _students.value = _students.value.map {
            if (it.id == updatedStudent.id) updatedStudent else it
        }
    }

    fun getStudentById(id: Int): Student? {
        return _students.value.find { it.id == id }
    }

    fun getAverageByGroup(): Map<String, Double> {
        return _students.value
            .groupBy { it.group }
            .mapValues { entry ->
                entry.value.map { it.score }.average()
            }
    }

    fun getLaggingStudentByGroup(): Map<String, Student?> {
        return _students.value
            .groupBy { it.group }
            .mapValues { entry ->
                entry.value.minByOrNull { it.score }
            }
    }

    fun getTop3StudentsByGroup(): Map<String, List<Student>> {
        return _students.value
            .groupBy { it.group }
            .mapValues { entry ->
                entry.value.sortedByDescending { it.score }.take(3)
            }
    }
}