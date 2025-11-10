package com.EdwarDa.Screens

import com.EdwarDa.MainViewModel
import androidx.navigation.NavController
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun formulari(navController: NavController, viewModel: MainViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Formulario") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                }
            )
        }
    ) { innerPadding ->

        ContentFormulari(
            modifier = Modifier.padding(innerPadding),
            viewModel = viewModel
        )
    }
}

@Composable
fun ContentFormulari(modifier: Modifier = Modifier, viewModel: MainViewModel) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Ingrese los datos")
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.name,
            onValueChange = { viewModel.onNameChange(it) },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.lastName,
            onValueChange = { viewModel.onLastNameChange(it) },
            label = { Text("Apellido") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.age,
            onValueChange = { viewModel.onAgeChange(it) },
            label = { Text("Edad") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            viewModel.saveData()
            viewModel.fetchData()
        }) {
            Text("Guardar y Enviar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = viewModel.resultState)
    }
}