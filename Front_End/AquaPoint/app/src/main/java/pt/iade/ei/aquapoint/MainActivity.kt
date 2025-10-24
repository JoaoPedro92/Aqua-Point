package pt.iade.ei.aquapoint

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.kittinunf.fuel.httpGet
import pt.iade.ei.aquapoint.ui.theme.AquaPointTheme
import pt.iade.ei.aquapoint.ui.components.CreateHomePage
import pt.iade.ei.aquapoint.ui.components.CreateLoginPage
import pt.iade.ei.aquapoint.ui.components.MapScreen
import pt.iade.ei.aquapoint.ui.components.Place


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AquaPointTheme {
                val places = listOf(
                    Place("Posto 1 - Santa Maria", "15min -3.8km", 4, R.drawable.aqua_point_logo, latitude = 38.78049309176663, longitude = -9.102677320690447),
                    Place("Posto 2 - Moscavide", "25min - 5km", 3, R.drawable.aqua_point_logo, latitude = 38.78105763775865, longitude = -9.103412245980696),
                    Place("Posto 3 - Santos", "35min - 7km", 3, R.drawable.aqua_point_logo, latitude = 38.77977799376074, longitude = -9.103589271780537),
                    Place("Posto 4 - Lumiar", " 10min - 2km", 3, R.drawable.aqua_point_logo, latitude = 38.78053909197767, longitude =  -9.101379131491612)
                )

                /* exemplo da utilização do metodo do back end com GET
                "http://10.0.2.2:8080/api/java/aquapoints/getAllAquaPoints/".httpGet().response{
                        request, response, result ->

                    Log.d("TESTES", response.toString())
                }
                 */

                MapScreen(places)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    AquaPointTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CreateHomePage()

        }
    }
}