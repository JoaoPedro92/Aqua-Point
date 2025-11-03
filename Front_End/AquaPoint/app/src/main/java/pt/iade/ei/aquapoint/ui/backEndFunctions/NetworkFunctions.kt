package pt.iade.ei.aquapoint.ui.backEndFunctions

import android.util.Log
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.serialization.json.Json
import org.json.JSONObject
import pt.iade.ei.aquapoint.AquaPoint
import pt.iade.ei.aquapoint.UserReviews

object NetworkService {

    fun getAquaPoints(onResult: (String) -> Unit) {
        "http://10.0.2.2:8080/api/java/aquapoints/getAllAquaPoints/"
            .httpGet()
            .responseString { _, _, result ->
                val output = when (result) {
                    is Result.Success -> result.get()
                    is Result.Failure -> "Erro: ${result.error}"
                }
                onResult(output)
            }
    }

    fun parseAquaPoints(jsonString: String): List<AquaPoint> {
        return Json.decodeFromString(jsonString)
    }

    fun getAquaPointReviews(pointId: Int?, onResult: (String) -> Unit) {
        "http://10.0.2.2:8080/api/java/usersInteractions/getUserReviewByPointId/"
            .httpPost()
            .header(Headers.CONTENT_TYPE, "application/json")
            .body(pointId.toString())
            .responseString { _, _, result ->
                val output = when (result) {
                    is Result.Success -> result.get()
                    is Result.Failure -> "Erro: ${result.error}"
                }
                onResult(output)
            }
    }

    fun parseUserReviews(jsonString: String): List<UserReviews> {
        return Json.decodeFromString(jsonString)
    }

    /*fun getUsers(onResult: (String) -> Unit) {
        "http://10.0.2.2:8080/api/java/users/getAllUsers/"
            .httpGet()
            .responseString { _, _, result ->
                val output = when (result) {
                    is Result.Success -> result.get()
                    is Result.Failure -> "Erro: ${result.error}"
                }
                onResult(output)
            }
    }*/
}
