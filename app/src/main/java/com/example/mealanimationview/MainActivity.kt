package com.example.mealanimationview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mealanimationview.ui.theme.MealAnimationViewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealAnimationViewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MealCardAnimation()
                }
            }
        }
    }
}
@Composable
fun MealCardAnimation() {
    LazyColumn(
        content = {
            item {
                Column {
                    Text(text = "List Food", style = MaterialTheme.typography.h5)
                    Text(
                        text = "Pick Your Favourite Food Now",
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            }
            items(getMealsDataModel()) { MealsCard(dataModelData = it) }
        },
        verticalArrangement = Arrangement.spacedBy((-30).dp),
        contentPadding = PaddingValues(vertical = 20.dp, horizontal = 20.dp)
    )
}
