package pt.iade.ei.aquapoint.ui.classes

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val joined: String,
)