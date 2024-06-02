package com.example.task_3.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.task_3.R

data class FoodData(
    @DrawableRes val foodPicture: Int,
    @StringRes val foodName: Int,
    val foodPrice: Int,
    @StringRes val foodStory: Int
)



val foods = listOf(
    FoodData(R.drawable.bakso, R.string.food_1, 16000, R.string.story_1),
    FoodData(R.drawable.mi_ayam, R.string.food_2, 12000, R.string.story_2),
    FoodData(R.drawable.soto, R.string.food_3, 10000, R.string.story_3),
    FoodData(R.drawable.nasgor, R.string.food_4, 12000, R.string.story_4),
    FoodData(R.drawable.uduk, R.string.food_5, 8000, R.string.story_5)
)







