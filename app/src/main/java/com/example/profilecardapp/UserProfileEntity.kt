package com.example.profilecardapp

data class Profile(val id: Int, var name: String, var status: Boolean, var profilePictureUrl: String )

var profileList = listOf(
    Profile( id = 1,name = "Paris Novielli", status = true, profilePictureUrl = "https://xsgames.co/randomusers/assets/avatars/male/57.jpg"),
    Profile( id = 2,name = "Caleb Nokes", status = false, profilePictureUrl = "https://xsgames.co/randomusers/assets/avatars/male/47.jpg"),
    Profile( id = 3,name = "Kareem Shooter", status = false, profilePictureUrl = "https://xsgames.co/randomusers/assets/avatars/male/21.jpg"),
    Profile( id = 4,name = "Shawn Niss", status = false, profilePictureUrl = "https://xsgames.co/randomusers/assets/avatars/male/18.jpg"),
    Profile( id = 5,name = "Jean Bonsack", status = true, profilePictureUrl = "https://xsgames.co/randomusers/assets/avatars/male/10.jpg"),
    Profile( id = 6,name = "Lindsey Besore", status = false, profilePictureUrl = "https://xsgames.co/randomusers/assets/avatars/male/44.jpg"),
    Profile( id = 7,name = "Jacques Fanene", status = true, profilePictureUrl = "https://xsgames.co/randomusers/assets/avatars/male/11.jpg"),
    Profile( id = 8,name = "Vern Kapellen", status = false, profilePictureUrl = "https://xsgames.co/randomusers/assets/avatars/male/67.jpg"),
    Profile( id = 9,name = "Vance Delgenio", status = true, profilePictureUrl = "https://xsgames.co/randomusers/assets/avatars/male/8.jpg"),
    Profile( id = 10,name = "Ferdinand Ohnmacht", status = true, profilePictureUrl = "https://xsgames.co/randomusers/assets/avatars/male/42.jpg"),
    Profile( id = 11,name = "Camila Farist", status = true, profilePictureUrl = "https://xsgames.co/randomusers/assets/avatars/female/77.jpg"),
    Profile( id = 12,name = "Honey Moakley", status = false, profilePictureUrl = "https://xsgames.co/randomusers/assets/avatars/female/62.jpg"),
    Profile( id = 13,name = "Erinn Panicker", status = true, profilePictureUrl = "https://xsgames.co/randomusers/assets/avatars/female/17.jpg"),
    Profile( id = 14,name = "Alexis Roddewig", status = false, profilePictureUrl = "https://xsgames.co/randomusers/assets/avatars/female/46.jpg"),

)