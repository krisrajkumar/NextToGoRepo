package au.com.neds.app.home.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
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
import au.com.neds.app.utils.CATEGORY
import au.com.neds.app.ui.theme.NextToGoAppTheme
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import dagger.hilt.android.AndroidEntryPoint

/**
 * Activity for Home screen UI.
 */
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
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(topBar = { HomeTopAppBar() }, content = { paddingValues ->
                        ComposeLottieLoaderAnimation()

                        val toggleLoader =
                            viewModel.getToggleProgressLiveData().observeAsState().value ?: true
                        if (toggleLoader) {
                            ComposeLottieLoaderAnimation()
                        } else {
                            val raceList = viewModel.getRacesLiveData().observeAsState().value
                            raceList?.let {
                                DisplayList(raceList, paddingValues)
                            }
                        }
                    })
                }
            }
        }
        viewModel.loadRaceListApi()
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
                Box(
                    modifier = Modifier
                        .clip(CutCornerShape(4.dp))
                        .background(
                            Color(0xFFE5E4E2)
                        )
                        .padding(3.dp)
                        .wrapContentWidth()
                ) {
                    Row(
                        modifier = Modifier.wrapContentWidth()
                    ) {
                        raceData.logo?.let { logo ->
                            val imageModifier = Modifier
                                .size(24.dp)
                            Image(
                                painter = painterResource(id = logo),
                                contentDescription = raceData.category,
                                modifier = imageModifier
                            )
                        }
                        Text(
                            text = raceData.category.orEmpty(), style = TextStyle(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color.Red, Color.Black
                                    )
                                )
                            ), fontStyle = FontStyle.Italic, modifier = Modifier
                                .padding(2.dp)
                        )
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HomeTopAppBar(
        modifier: Modifier = Modifier,
    ) {
        var menuExpanded by remember {
            mutableStateOf(false)
        }
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )

            }, colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = colorResource(id = R.color.orange),
                titleContentColor = colorResource(id = R.color.white)
            ), actions = {
                IconButton(onClick = { menuExpanded = !menuExpanded }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_filter_list_on),
                        contentDescription = stringResource(id = R.string.menu_filter),
                        tint = Color.White
                    )
                }
                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false },
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(stringResource(id = R.string.category_all))
                        },
                        onClick = {
                            menuExpanded = false
                            viewModel.getRaceByCategory(CATEGORY.ALL)
                        },
                    )
                    DropdownMenuItem(
                        text = {
                            Text(stringResource(id = R.string.category_greyhound_racing))
                        },
                        onClick = {
                            menuExpanded = false
                            viewModel.getRaceByCategory(CATEGORY.GREYHOUND_RACING)
                        },
                    )
                    DropdownMenuItem(
                        text = {
                            Text(stringResource(id = R.string.category_harness_racing))
                        },
                        onClick = {
                            menuExpanded = false
                            viewModel.getRaceByCategory(CATEGORY.HARNESS_RACING)
                        },
                    )
                    DropdownMenuItem(
                        text = {
                            Text(stringResource(id = R.string.category_horse_racing))
                        },
                        onClick = {
                            menuExpanded = false
                            viewModel.getRaceByCategory(CATEGORY.HORSE_RACING)
                        },
                    )
                }
            }, modifier = modifier
        )
    }
}

@Composable
fun ComposeLottieLoaderAnimation() {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_loader))

    LottieAnimation(
        modifier = Modifier
            .size(5.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    NextToGoAppTheme {
        Greeting("Android")
    }
}