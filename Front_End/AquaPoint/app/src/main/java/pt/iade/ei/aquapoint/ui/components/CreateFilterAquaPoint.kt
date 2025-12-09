package pt.iade.ei.aquapoint.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import pt.iade.ei.aquapoint.Screen
import pt.iade.ei.aquapoint.data.AquaPointsRepository
import pt.iade.ei.aquapoint.data.UserDataRepository
import pt.iade.ei.aquapoint.ui.classes.AquaPoint
import pt.iade.ei.aquapoint.ui.backEndFunctions.NetworkService
import pt.iade.ei.aquapoint.ui.backEndFunctions.NetworkService.parseUserReviews

@Composable
fun CreateFilterAquaPoint(
    navController: NavHostController,
    isFavorite: Boolean = false
) {
    var searchText by remember { mutableStateOf("") }
    var places by remember { mutableStateOf(AquaPointsRepository.getCached() ?: emptyList()) }

    LaunchedEffect(Unit) {
        if (isFavorite) {
            AquaPointsRepository.updateFavoriteAquaPoints(UserDataRepository.getUserId()) {
                places = it
            }
        } else {
            AquaPointsRepository.updateAquaPoints {
                places = it
            }
        }
    }

    // ---- ESTADOS DO FILTRO ----
    var showFilter by remember { mutableStateOf(false) }

    var reviewCounts by remember { mutableStateOf<Map<Int, Int>>(emptyMap()) }
    var avgRatings by remember { mutableStateOf<Map<Int, Double>>(emptyMap()) }

    var selectedRating by remember { mutableStateOf(0) }
    var orderLess by remember { mutableStateOf(false) }
    var orderMore by remember { mutableStateOf(false) }

    val hasFilters = selectedRating > 0 || orderLess || orderMore

    val filteredPlaces = remember(
        searchText, places, reviewCounts, avgRatings,
        selectedRating, orderLess, orderMore
    ) {
        applyFilters(
            all = places,
            searchText = searchText,
            ratings = avgRatings,
            reviewCounts = reviewCounts,
            minRating = selectedRating,
            orderLess = orderLess,
            orderMore = orderMore
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        CreateSearchBox(
            searchText = searchText,
            onSearchTextChange = { searchText = it },
            enabled = true,
            filterButton = true,
            filterActive = hasFilters,
            onFilterClick = {
                loadReviewsForFilter(places) { counts, avgs ->
                    reviewCounts = counts
                    avgRatings = avgs
                    showFilter = true
                }
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (filteredPlaces.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Bebedouro não encontrado", color = Color.Gray)
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(filteredPlaces) { place ->
                    CreatePointCard(
                        place = place,
                        isFavorite = isFavorite,
                        onClick = { navController.navigate(Screen.Detail.createRoute(place.id)) }
                    )
                }
            }
        }
    }

    if (showFilter) {
        FilterBottomSheet(
            selectedRating = selectedRating,
            onRatingSelected = { selectedRating = it },

            orderLess = orderLess,
            orderMore = orderMore,
            onOrderLess = {
                orderLess = true
                orderMore = false
            },
            onOrderMore = {
                orderMore = true
                orderLess = false
            },

            onApply = { showFilter = false },
            onClear = {
                selectedRating = 0
                orderLess = false
                orderMore = false
            },
            onDismiss = { showFilter = false }
        )
    }
}

fun loadReviewsForFilter(
    points: List<AquaPoint>,
    onComplete: (Map<Int, Int>, Map<Int, Double>) -> Unit
) {
    if (points.isEmpty()) {
        onComplete(emptyMap(), emptyMap())
        return
    }

    val counts = mutableMapOf<Int, Int>()
    val avgs = mutableMapOf<Int, Double>()
    var pending = points.size

    points.forEach { p ->
        NetworkService.getAquaPointReviews(p.id) { json ->
            val reviews = parseUserReviews(json)
            counts[p.id] = reviews.size
            avgs[p.id] = if (reviews.isEmpty()) 0.0 else reviews.map { it.rating }.average()

            pending--
            if (pending == 0) onComplete(counts, avgs)
        }
    }
}

fun applyFilters(
    all: List<AquaPoint>,
    searchText: String,
    ratings: Map<Int, Double>,
    reviewCounts: Map<Int, Int>,
    minRating: Int,
    orderLess: Boolean,
    orderMore: Boolean
): List<AquaPoint> {

    var result = all.filter { point ->
        (ratings[point.id] ?: 0.0) >= minRating &&
                point.name.contains(searchText, ignoreCase = true)
    }

    result = result.sortedBy { ratings[it.id] ?: 0.0 }

    if (orderLess) result = result.sortedBy { reviewCounts[it.id] ?: 0 }
    if (orderMore) result = result.sortedByDescending { reviewCounts[it.id] ?: 0 }

    return result
}