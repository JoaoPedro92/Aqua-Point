package pt.iade.ei.aquapoint.ui.classes

import kotlinx.serialization.Serializable

@Serializable
data class LocalsDataClass(
    val id: Int,
    val local_name: String,
    val zone_id: Int,
)