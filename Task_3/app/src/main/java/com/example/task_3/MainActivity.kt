package com.example.task_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.task_3.data.CanteenBottomBar
import com.example.task_3.data.CanteenTopBar
import com.example.task_3.data.FoodData
import com.example.task_3.data.foods
import com.example.task_3.ui.theme.Task_3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Task_3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CanteenApp()
                }
            }
        }
    }
}




@Composable
fun CanteenApp(){
    var orderedItems by remember {  mutableStateOf(listOf<FoodData>()) }
    val totalPrice = orderedItems.sumOf { it.foodPrice }

    Scaffold (
        topBar = { CanteenTopBar() },
        bottomBar = { CanteenBottomBar(totalPrice) }
    ) { ip ->
        LazyColumn(contentPadding = ip) {
            items(foods) {food ->
                CanteenItem(
                    foodData = food,
                    ordered = orderedItems.contains(food),
                    notOrdered = {ordered ->
                        orderedItems =
                            if (ordered){
                                orderedItems + food
                            }else{
                                orderedItems - food
                            }

                    }

                )
            }
        }
    }
}



@Composable
fun CanteenItem(
    foodData: FoodData,
    ordered: Boolean,
    notOrdered: (Boolean) -> Unit,
    modifier: Modifier = Modifier
){
    var expanded by remember { mutableStateOf(false) }

    val color by animateColorAsState(
        targetValue =
        if (expanded)
            MaterialTheme.colorScheme.onPrimary
        else
            MaterialTheme.colorScheme.secondary,
        label = "",
    )

    Card (
        modifier = modifier.padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = color
        )
    ) {
        Column (
            modifier = modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )

        ){
            Box {
                CanteenImage(foodImage = foodData.foodPicture)
                Box (modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd){
                    CanteenButton(expanded = expanded, onClick = {expanded = !expanded})
                }

            }
            CanteenDetails(foodName = foodData.foodName, foodPrice = foodData.foodPrice)
            if (expanded){
                Spacer(modifier = Modifier.height(15.dp))
                CanteenSelect(foodStory = foodData.foodStory, ordered = ordered, notOrdered = notOrdered)
            }

        }

    }

}

@Composable
fun CanteenImage(
    @DrawableRes foodImage: Int,
    modifier: Modifier = Modifier
) {
    Box (modifier = Modifier.fillMaxWidth()){
        Image(
            modifier = modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(8.dp)
                .clip(MaterialTheme.shapes.large),
            painter = painterResource(foodImage),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }

}

@Composable
fun CanteenButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector =
                if (expanded){
                    Icons.Default.KeyboardArrowUp
                }else{
                    Icons.Default.KeyboardArrowDown
                },
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .padding(end = 8.dp)

        )
    }

}

@Composable
fun CanteenDetails(
    @StringRes foodName: Int,
    foodPrice: Int
) {
    Row {
        Text(
            text = stringResource(foodName),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colorScheme.onSecondary
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(R.string.rupiah,foodPrice),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colorScheme.tertiary
        )
    }

}

@Composable
fun CanteenSelect(
    @StringRes foodStory: Int,
    ordered: Boolean,
    notOrdered: (Boolean) -> Unit
) {
    Column {
        Text(
            text = stringResource(foodStory),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row (verticalAlignment = Alignment.CenterVertically){
            Text(
                text = stringResource(R.string.add),
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Checkbox(
                checked = ordered,
                onCheckedChange = notOrdered
            )
        }
    }


}

