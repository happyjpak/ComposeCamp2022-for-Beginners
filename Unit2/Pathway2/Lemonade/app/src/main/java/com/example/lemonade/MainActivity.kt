package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import com.example.lemonade.ui.theme.Shapes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeTextAndImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun LemonadeTextAndImage(modifier: Modifier = Modifier) {
    var tapIndex by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    val stringResources = when(tapIndex) {
        1 -> R.string.app_tap1
        2 -> R.string.app_tap2
        3 -> R.string.app_tap3
        else -> R.string.app_tap4
    }

    val stringDecResources = when(tapIndex) {
        1 -> R.string.app_image1
        2 -> R.string.app_image2
        3 -> R.string.app_image3
        else -> R.string.app_image4
    }

    val imageResources = when(tapIndex) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = stringDecResources),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // 버튼을 누를 때 마다 tap index 가 증가
            // tap index 가 2이면 2~4 사이 랜덤한 스퀴즈 카운트 생성하여 해당 개수 만큼 클릭 후 다음 단계 진행
            // tap index 가 4이면 tap index 초기화
            when(tapIndex) {
                1 -> {
                    tapIndex = 2
                    squeezeCount = (2..4).random()
                }
                2 -> {
                    squeezeCount--
                    if (squeezeCount == 0) tapIndex = 3
                }
                3 -> tapIndex = 4
                4 -> tapIndex = 1
            }
        }) {
            Image(
                painter = painterResource(id = imageResources),
                contentDescription = stringResource(id = stringResources),
                modifier = Modifier
                    .wrapContentSize()
                    .border(2.dp, Color(105, 205, 216), shape = Shapes.medium)
            )
        }
    }
}