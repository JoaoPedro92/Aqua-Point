package pt.iade.ei.aquapoint

import kotlinx.serialization.Serializable

@Serializable
data class AquaPoint(
    val id: Int,
    val type: Int,
    val name: String,
    val localId: Int,
    val state_id: Int,
    val imageRes: Int = R.drawable.aqua_point_logo,
    val latitude: Double,
    val longitude: Double
)