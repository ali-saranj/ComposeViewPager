package com.example.composelearn

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelearn.ui.theme.ColorBlue
import com.example.composelearn.ui.theme.ColorGreen
import com.example.composelearn.ui.theme.ColorYellow
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroScreen(modifier: Modifier = Modifier) {
    val pages = listOf(
        IntroPage("Page 1", "Description 1", R.drawable.food, ColorYellow),
        IntroPage("Page 2", "Description 2", R.drawable.fruit, ColorBlue),
        IntroPage("Page 3", "Description 3", R.drawable.cooking, ColorGreen)
    )

    val pagerState = rememberPagerState(pageCount = { pages.size }, initialPage = 0)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        IntroPager(pages = pages, pagerState = pagerState)
        BottomSheet(
            pagerState = pagerState,
            pages = pages,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomSheet(modifier: Modifier = Modifier, pagerState: PagerState, pages: List<IntroPage>) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.25f),
        shape = RoundedCornerShape(topEnd = 80.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = pages[pagerState.currentPage].title,
                    fontSize = 20.sp,
                    color = pages[pagerState.currentPage].color,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(100.dp))
                PageIndicator(pagerState = pagerState, pages = pages)
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = pages[pagerState.currentPage].description,
                fontSize = 16.sp
            )
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                val jab = rememberCoroutineScope()
                if (pagerState.currentPage != 0) {
                    TextButton(onClick = {
                        jab.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                    ) {
                        Text(text = "Back", color = pages[pagerState.currentPage].color)
                    }
                } else {
                    Spacer(modifier = Modifier.width(100.dp))
                }
                Card(
                    modifier = Modifier.clickable {
                        jab.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    },
                    colors = CardDefaults.cardColors(containerColor = pages[pagerState.currentPage].color),
                    shape = CircleShape
                ) {
                    Column(
                        modifier = Modifier.padding(4.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(
                            shape = CircleShape
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    modifier = Modifier.size(30.dp),
                                    imageVector = Icons.Rounded.ArrowForward,
                                    contentDescription = null,
                                    tint = pages[pagerState.currentPage].color
                                )
                            }

                        }
                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageIndicator(pagerState: PagerState, pages: List<IntroPage>) {
    Box(contentAlignment = Alignment.CenterStart) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pagerState.pageCount) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(Color.LightGray, RoundedCornerShape(10.dp))
                )
            }
        }

        Box(
            Modifier
                .slidingLineTransition(pagerState, distance = 47f)
                .size(10.dp)
                .background(pages[pagerState.currentPage].color, RoundedCornerShape(10.dp))
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun Modifier.slidingLineTransition(pagerState: PagerState, distance: Float) =
    graphicsLayer {
        val scrollPosition = pagerState.currentPage + pagerState.currentPageOffsetFraction
        translationX = scrollPosition * distance
    }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroPager(pages: List<IntroPage>, pagerState: PagerState) {
    HorizontalPager(state = pagerState) { pageIndex ->
        IntroPageContent(page = pages[pageIndex])
    }
}

@Composable
fun IntroPageContent(page: IntroPage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = page.color)
    ) {
        Image(
            modifier = Modifier.height(700.dp),
            painter = painterResource(page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewIntroScreen() {
    IntroScreen()
}
