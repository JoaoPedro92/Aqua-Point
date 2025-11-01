package pt.iade.ei.aquapoint

import kotlinx.serialization.Serializable

@Serializable
data class UserReviews(
    val id: Int,
    val name: String,
    val rating: Int,
    val comment: String,
    val date: String,
)