package pt.iade.ei.aquapoint.ui.classes

import kotlinx.serialization.Serializable

@Serializable
data class UserReviews(
    val id: Int,
    val name: String,
    val rating: Int,
    val comment: String,
    val date: String,
)