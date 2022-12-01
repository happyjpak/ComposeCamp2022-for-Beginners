package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme
import com.example.artspace.ui.theme.Shapes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var currentIndex by remember { mutableStateOf(1) }

    val artNames = when(currentIndex) {
        2 -> R.drawable.dh_chicago_1976
        3 -> R.drawable.dh_bigger_splash
        4 -> R.drawable.dh_town
        else -> R.drawable.art_cow
    }

    val artTitles = when(currentIndex) {
        2 -> R.string.art_title2
        3 -> R.string.art_title3
        4 -> R.string.art_title4
        else -> R.string.art_title1
    }

    val artArtists = when(currentIndex) {
        2 -> R.string.art_artist_and_year2
        3 -> R.string.art_artist_and_year3
        4 -> R.string.art_artist_and_year4
        else -> R.string.art_artist_and_year1
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, top = 5.dp, end = 20.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtImage(
            artName = artNames,
            artTitle = artTitles,
            modifier = Modifier
                .padding(15.dp)
                .border(width = 2.dp, color = Color.Gray, shape = Shapes.small)
                .weight(0.65f),
        )

        ArtContents(
            artTitle = artTitles,
            artArtist = artArtists,
            modifier = Modifier
                .fillMaxHeight()
                .padding(10.dp)
                .weight(0.15f),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .weight(0.2f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                currentIndex++
                if (currentIndex > 4) currentIndex = 1
            },
                modifier = Modifier
                    .padding(start = 20.dp, end = 4.dp)
                    .size(width = 140.dp, height = 38.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
            ) {
                Text(text = stringResource(id = R.string.btn_prev))
            }
            Button(onClick = {
                currentIndex--
                if (currentIndex < 1) currentIndex = 4
            },
                modifier = Modifier
                    .padding(start = 2.dp, end = 20.dp)
                    .size(width = 140.dp, height = 38.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
            ) {
                Text(text = stringResource(id = R.string.btn_next))
            }
        }
    }
}

@Composable
fun ArtImage(
    @DrawableRes artName: Int,
    @StringRes artTitle: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = artName),
            contentDescription = stringResource(id = artTitle),
            modifier = Modifier
                .padding(20.dp)
        )
    }
}

@Composable
fun ArtContents(
    @StringRes artTitle: Int,
    @StringRes artArtist: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = artTitle),
            modifier = Modifier
                .padding(10.dp),
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = artArtist),
            modifier = Modifier
                .padding(10.dp),
            fontSize = 14.sp,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}