package com.example.mealanimationview

import android.view.MotionEvent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.music.TouchState

@OptIn(ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class)
@Composable
fun MealsCard(dataModelData: MealData) {
    var currentState: TouchState by remember { mutableStateOf(TouchState.NotTouched) }
    val transition = updateTransition(targetState = currentState, label = "animation")
    val scale: Float by transition.animateFloat(
        transitionSpec = { spring(stiffness = 900f) },
        label = "animation"
    ) { touchState ->
        if (touchState == TouchState.Touched) 1.3f else 1f
    }
    val colorAlpha: Float by transition.animateFloat(
        transitionSpec = { spring(stiffness = 900f) },
        label = ""
    ) { touchState ->
        if (touchState == TouchState.Touched) 1f else 0.2f
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .pointerInteropFilter {
                    currentState = when (it.action) {
                        MotionEvent.ACTION_DOWN -> TouchState.Touched
                        else -> TouchState.NotTouched
                    }
                    true
                },
            shape = RoundedCornerShape(16.dp),
            backgroundColor = dataModelData.color.copy(alpha = 0.2f),
            elevation = 0.dp
        ) {
            Row(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                dataModelData.color.copy(alpha = 0.2f),
                                dataModelData.color.copy(alpha = colorAlpha)
                            )
                        )
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 32.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = dataModelData.meal_name, style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.height(8.dp))
                    AnimatedVisibility(visible = currentState == TouchState.NotTouched) {
                        Text(
                            text = dataModelData.cooking,
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                    AnimatedVisibility(visible = currentState == TouchState.Touched) {
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(text = "Pick Up Now", style = MaterialTheme.typography.subtitle1)
                            Icon(
                                imageVector = Icons.Rounded.ArrowForward,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(start = 4.dp)
                            )
                        }
                    }
                }
            }
        }
        Image(
            painter = painterResource(id = dataModelData.image),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height((120 * scale).dp)
                .padding(end = 32.dp)
                .align(Alignment.BottomEnd)
        )
    }
}
