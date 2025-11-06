package com.example.EdwarGonzalez.ViewModel

import androidx.collection.MutableObjectList
import androidx.collection.mutableObjectListOf
import androidx.lifecycle.ViewModel
import com.example.EdwarGonzalez.model.Student

class StudentViewModel: ViewModel() {

    private var _studentsList: MutableObjectList<Student> = mutableObjectListOf()
    private val studentList: MutableObjectList<Student> = _studentsList

    fun getAllStudents(): MutableObjectList<Student> {
        return _studentsList
    }

    fun addStudent(name: String, description: String, imgURL: String){
        var id: Int = _studentsList.size + 1
        val newStudent = Student(id, name, description, imgURL)
        _studentsList.add(newStudent)
    }

    fun getStudent(id: Int?): Student? {
        _studentsList.forEach { it ->
            if (it.id == id){
                return it
            }
        }
        return null
    }
}