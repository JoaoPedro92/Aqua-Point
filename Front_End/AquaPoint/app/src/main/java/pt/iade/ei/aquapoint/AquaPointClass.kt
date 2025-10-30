package pt.iade.ei.aquapoint

import kotlinx.serialization.Serializable

@Serializable
data class AquaPoint(
    val id: Int,
    val type: Int,
    val name: String,
    val distance: String = "18min - 3.5KM",
    val rating: Int = 3,
    val localId: Int,
    val imageRes: Int = R.drawable.aqua_point_logo,
    val latitude: Double,
    val longitude: Double
)