package com.example.task_3.data

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.task_3.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CanteenTopBar(){
    CenterAlignedTopAppBar(

        title = {
            Row (verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = painterResource(id = R.drawable.canteen),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
                Text(
                    text = stringResource(id = R.string.title),
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.offset(x = 0.dp, y = 6.dp),
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }


        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.onTertiary
        )



    )
}

@Composable
fun CanteenBottomBar(totalPrice: Int){
    BottomAppBar (
        containerColor = MaterialTheme.colorScheme.onTertiary,
        tonalElevation = 8.dp

    ) {
        Box (
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier.fillMaxWidth()
        ){
            Row {
                Text(
                    text = stringResource(id = R.string.total),
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.padding(start = 8.dp),
                    color = MaterialTheme.colorScheme.onSecondary
                )
                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = stringResource(id = R.string.rupiah,totalPrice),
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.padding(end = 8.dp),
                    color = MaterialTheme.colorScheme.tertiary
                )

            }

        }


    }

}