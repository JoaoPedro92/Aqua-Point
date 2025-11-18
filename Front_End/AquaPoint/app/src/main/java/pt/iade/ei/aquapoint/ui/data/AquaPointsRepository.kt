package pt.iade.ei.aquapoint.data

import pt.iade.ei.aquapoint.AquaPoint
import pt.iade.ei.aquapoint.ui.backEndFunctions.NetworkService
import kotlin.collections.List

object AquaPointsRepository {
    private var cache: List<AquaPoint>? = null
    private var favoriteCache: List<AquaPoint>? = null

    fun getCached(): List<AquaPoint>? {
        return cache
    }

    fun getFavoriteCached(): List<AquaPoint>? {
        return favoriteCache
    }

    fun setCache(list: List<AquaPoint>) {
        cache = list
    }

    fun setFavoriteCache(list: List<AquaPoint>) {
        favoriteCache = list
    }

    fun clearCache() {
        cache = null
    }

    fun clearFavoriteCache() {
        favoriteCache = null
    }

    fun updateAquaPoints(onComplete: (List<AquaPoint>) -> Unit) {
        NetworkService.getAquaPoints { jsonString ->
            try {
                val points = NetworkService.parseAquaPoints(jsonString)
                setCache(points)
                onComplete(points)
            } catch (e: Exception) {
                onComplete(emptyList())
            }
        }
    }

    fun updateFavoriteAquaPoints(userId: Int?, onComplete: (List<AquaPoint>) -> Unit) {
        NetworkService.getFavoriteAquaPoints(userId) { jsonString ->
            try {
                val points = NetworkService.parseAquaPoints(jsonString)
                setFavoriteCache(points)
                onComplete(points)
            } catch (e: Exception) {
                onComplete(emptyList())
            }
        }
    }

    fun getPointById(id: Int): AquaPoint? {
        val list: List<AquaPoint> = getCached() ?: emptyList()

        return list.find { aquaPoint -> aquaPoint.id == id }
    }

    fun isFavorite(id: Int?): Boolean {
        return favoriteCache?.any { it.id == id } ?: false
    }

    fun hasCache(): Boolean {
        return cache != null
    }

    fun hasFavoriteCache(): Boolean {
        return favoriteCache != null
    }
}
