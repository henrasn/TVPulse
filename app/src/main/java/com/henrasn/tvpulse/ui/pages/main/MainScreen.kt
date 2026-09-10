package com.henrasn.tvpulse.ui.pages.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.henrasn.tvpulse.R
import com.henrasn.tvpulse.ui.pages.favorite.FavoriteScreen
import com.henrasn.tvpulse.ui.pages.home.HomeScreen
import com.henrasn.tvpulse.ui.theme.TVPulseTheme
import kotlinx.coroutines.launch
import okhttp3.internal.immutableListOf

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {

    Scaffold { innerPadding ->
        MainContent(modifier = Modifier.padding(innerPadding), pageContent = { currentPage ->
            if (currentPage == 0) HomeScreen() else FavoriteScreen()
        })
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier, pageContent: @Composable PagerScope.(Int) -> Unit) {
    val tabs = immutableListOf("HOME", "FAVORITE")
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(Modifier.size(16.dp))
        PrimaryTabRow(
            modifier = Modifier.fillMaxWidth(),
            selectedTabIndex = pagerState.currentPage,
            indicator = {
                TabRowDefaults.PrimaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(
                        selectedTabIndex = pagerState.currentPage,
                        matchContentSize = false
                    ),
                    width = Dp.Unspecified,
                    color = MaterialTheme.colorScheme.primary
                )
            },
            containerColor = Color.Transparent
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    modifier = Modifier.background(Color.Transparent),
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.outline,
                    text = {
                        Text(text = title)
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            pageContent = pageContent
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMainContent() {
    TVPulseTheme {
        MainContent { currentPage ->
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Current page is $currentPage", color = MaterialTheme.colorScheme.onBackground)
            }
        }
    }
}