package com.example.mealanimationview

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class MealData(
    val meal_name: String,
    val cooking: String,
    @DrawableRes
    val image: Int,
    val color: Color
)

fun getMealsDataModel(): List<MealData> = listOf(
    MealData("Paleo Meal", "Kajal Suri", R.drawable.c, Color(0xFFFF8B01)),
    MealData("Suparipalana", "Shailendra Joshi", R.drawable.d, Color(0xFFFF0000)),
    MealData("The Christmas Pig", "JK Rowling", R.drawable.b, Color(0xFF005DFF)),
    MealData("Shailendra", "Jhumpa Lahiri", R.drawable.a, Color(0xFF00FF37)),
    MealData("Whereabouts", "Jhumpa Lahiri", R.drawable.f, Color(0xFFFFEB3B)),
    MealData("Whereab", "Jhumpa Lahiri", R.drawable.c, Color(0xFF8D948E)),
    MealData("Abouts", "Jhumpa Lahiri", R.drawable.a, Color(0xFFFA00D9)),
    MealData("Lahiri", "Jhumpa Lahiri", R.drawable.i, Color(0xFF00A6FF)),
)
