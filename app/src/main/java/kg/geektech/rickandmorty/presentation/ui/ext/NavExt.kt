package kg.geektech.rickandmorty.presentation.ui.ext

import androidx.navigation.NavController
import androidx.navigation.NavDirections

fun NavController.directionsSafeNavigation(directions: NavDirections) {
    currentDestination?.getAction(directions.actionId)?.let { navigate(directions) }
}