package pt.iade.ei.aquapoint.ui.backEndFunctions

import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpUpload
import com.github.kittinunf.result.Result
import kotlinx.serialization.json.Json
import org.json.JSONObject
import pt.iade.ei.aquapoint.ui.classes.AquaPoint
import pt.iade.ei.aquapoint.ui.classes.LocalsDataClass
import pt.iade.ei.aquapoint.ui.classes.UserData
import pt.iade.ei.aquapoint.ui.classes.UserReviews
import com.github.kittinunf.fuel.core.BlobDataPart

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

    fun getFavoriteAquaPoints(userId: Int?, onResult: (String) -> Unit) {
        "http://10.0.2.2:8080/api/java/aquapoints/getFavoriteAquaPointsByUserId/"
            .httpPost()
            .header(Headers.CONTENT_TYPE, "application/json")
            .body(userId.toString())
            .responseString { _, _, result ->
                val output = when (result) {
                    is Result.Success -> result.get()
                    is Result.Failure -> "Erro: ${result.error}"
                }
                onResult(output)
            }
    }

    fun addAquaPointToFavorite(userId: Int?, pointId: Int?, onResult: (String) -> Unit) {
        val json = JSONObject()
        json.put("userId", userId)
        json.put("pointId", pointId)

        "http://10.0.2.2:8080/api/java/aquapoints/addAquaPointToFavorite/"
            .httpPost()
            .header(Headers.CONTENT_TYPE, "application/json")
            .body(json.toString())
            .responseString { _, _, result ->
                val output = when (result) {
                    is Result.Success -> result.get()
                    is Result.Failure -> "Erro: ${result.error}"
                }
                onResult(output)
            }
    }

    fun removeAquaPointFromFavorites(userId: Int?, pointId: Int?, onResult: (String) -> Unit) {
        val json = JSONObject()
        json.put("userId", userId)
        json.put("pointId", pointId)

        "http://10.0.2.2:8080/api/java/aquapoints/removeAquaPointFromFavorites/"
            .httpPost()
            .header(Headers.CONTENT_TYPE, "application/json")
            .body(json.toString())
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

    fun parseAquaPoint(jsonString: String): AquaPoint {
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

    fun getUserByEmail(email: String, onResult: (String) -> Unit) {
        "http://10.0.2.2:8080/api/java/users/getUserDataByEmail/"
            .httpPost()
            .header(Headers.CONTENT_TYPE, "application/json")
            .body(email)
            .responseString { _, _, result ->
                val output = when (result) {
                    is Result.Success -> result.get()
                    is Result.Failure -> "Erro: ${result.error}"
                }
                onResult(output)
            }
    }

    fun createNewUser(
        name: String?,
        email: String?,
        password: String?,
        onResult: (String) -> Unit
    ) {
        val json = JSONObject()
        json.put("name", name)
        json.put("email", email)
        json.put("password", password)

        "http://10.0.2.2:8080/api/java/users/createNewUser/"
            .httpPost()
            .header(Headers.CONTENT_TYPE, "application/json")
            .body(json.toString())
            .responseString { _, _, result ->
                val output = when (result) {
                    is Result.Success -> result.get()
                    is Result.Failure -> "Erro: ${result.error}"
                }
                onResult(output)
            }
    }

    fun parseUser(jsonString: String): UserData {
        return Json.decodeFromString(jsonString)
    }

    fun createNewReview(userId: Int?, pointId: Int?, rating: Int?, comment: String?, onResult: (String) -> Unit) {
        val json = JSONObject()
        json.put("userId", userId)
        json.put("pointId", pointId)
        json.put("rating", rating)
        json.put("comment", comment)

        "http://10.0.2.2:8080/api/java/usersInteractions/addNewAquaPointReview/"
            .httpPost()
            .header(Headers.CONTENT_TYPE, "application/json")
            .body(json.toString())
            .responseString { _, _, result ->
                val output = when (result) {
                    is Result.Success -> result.get()
                    is Result.Failure -> "Erro: ${result.error}"
                }
                onResult(output)
            }
    }

    fun updateUser(
        id: Int,
        email: String,
        newName: String,
        newPassword: String,
        joined: String,
        onResult: (String) -> Unit
    ) {
        val json = JSONObject()
        json.put("id", id)
        json.put("email", email)
        json.put("name", newName)
        json.put("password", newPassword)
        json.put("joined", joined)

        "http://10.0.2.2:8080/api/java/users/editUserData/".httpPost()
            .header(Headers.CONTENT_TYPE, "application/json")
            .body(json.toString())
            .responseString { _, _, result ->
                val output = when (result) {
                    is Result.Success -> result.get()
                    is Result.Failure -> "Erro: ${result.error}"
                }
                onResult(output)
            }
    }

    fun createNewAquaPoint(pointName: String?, pointType: Int?, pointLatitude: Double?, pointLongitude: Double?, localId: Int?, onResult: (String) -> Unit) {
        val json = JSONObject()
        json.put("point_name", pointName)
        json.put("point_type", pointType)
        json.put("latitude", pointLatitude)
        json.put("longitude", pointLongitude)
        json.put("local_id", localId)

        "http://10.0.2.2:8080/api/java/aquapoints/createNewAquaPoint/"
            .httpPost()
            .header(Headers.CONTENT_TYPE, "application/json")
            .body(json.toString())
            .responseString { _, _, result ->
                val output = when (result) {
                    is Result.Success -> result.get()
                    is Result.Failure -> "Erro: ${result.error}"
                }
                onResult(output)
            }
    }

    fun getLocalsData(onResult: (String) -> Unit) {
        "http://10.0.2.2:8080/api/java/locals/getAllLocals/"
            .httpPost()
            .header(Headers.CONTENT_TYPE, "application/json")
            .responseString { _, _, result ->
                val output = when (result) {
                    is Result.Success -> result.get()
                    is Result.Failure -> "Erro: ${result.error}"
                }
                onResult(output)
            }
    }

    fun parseLocalsData(jsonString: String): List<LocalsDataClass> {
        return Json.decodeFromString(jsonString)
    }

    fun uploadNewImage(imageData: ByteArray, imageName: String, onResult: (String) -> Unit) {
        val inputStream = imageData.inputStream()

        "http://10.0.2.2:8080/api/java/imagesManager/uploadAquaPointImage/"
            .httpUpload()
            .add(
                BlobDataPart(
                    inputStream,
                    name = "file",
                    filename = imageName,
                    contentType = "image/png"
                )
            )
            .responseString { _, _, result ->
                val out = result.fold(
                    success = { it },
                    failure = { "Erro: ${it.message}" }
                )
                onResult(out)
            }
    }
}