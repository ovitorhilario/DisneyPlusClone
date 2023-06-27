package com.vitorhilarioapps.disneyplus.ui.destinations.login

object LoginData {

    data class Profile(val id: Int, val name: String, val model: String)

    val avatarContent = listOf(
        Profile(
            0,
            "Mulan",
            "https://i.pinimg.com/564x/82/26/0f/82260ffa474e9b0b47313d15b03c213a.jpg"
        ),
        Profile(
            1,
            "Kiara",
            "https://i.pinimg.com/564x/0c/36/23/0c36234625958f87f6ab398934e3157f.jpg"
        ),
        Profile(
            2,
            "Stich",
            "https://i.pinimg.com/236x/a6/fc/c6/a6fcc63e3c6922ec3301165bd8396fa5.jpg"
        )
    )
}