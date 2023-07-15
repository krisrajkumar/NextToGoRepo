package au.com.neds.app.home.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import au.com.neds.app.R
import au.com.neds.app.home.model.RaceData
import au.com.neds.app.home.viewmodel.HomeViewModel
import au.com.neds.app.ui.theme.NextToGoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val viewModel by viewModels<HomeViewModel>()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NextToGoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = { CustomTopAppBar() },
                        content = { paddingValues ->
                            val raceList = viewModel.getRacesLiveData().observeAsState().value
                            raceList?.let {
                                DisplayList(raceList, paddingValues)
                            }
                        }
                    )
                }
            }
        }
        viewModel.loadRaceListApi()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(0xFFFF6000),
            titleContentColor = Color(0xFFFFFFFF)
        )
    )
}

@Composable
fun DisplayList(list: List<RaceData>, paddingValues: PaddingValues) {
    LazyColumn(contentPadding = paddingValues) {
        // Add 5 items
        items(list) { data ->
            DisplayCard(data)
        }
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun DisplayCard(raceData: RaceData) {
    val paddingModifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp)
    Card(
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = paddingModifier
    ) {
        Column(modifier = paddingModifier) {
            Text(
                text = raceData.title.orEmpty(),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = raceData.category.orEmpty(),
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color.Red,
                            Color.Black
                        )
                    )
                ),
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .background(
                        Color.LightGray
                    )
                    .padding(2.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    NextToGoAppTheme {
        Greeting("Android")
    }
}