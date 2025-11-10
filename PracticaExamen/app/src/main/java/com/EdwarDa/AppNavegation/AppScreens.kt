package com.EdwarDa.AppNavegation

sealed class AppScreens(val route: String) {
    object Content : AppScreens("Botones")
    object Formulari : AppScreens("Formulari")
    object ColorPage : AppScreens("ColorPage")

}