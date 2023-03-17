package com.example.myowndictionary.domain

data class ListOfMenu(
    val menus : List<HomeRvItem>
)

data class HomeRvItem(
    val title: String,
    val type: String,
    val actions: List<Action>
)

data class Action(
    val icon : Int,
    val type: String,
    val title : String
)
