package com.softworkshub.newsapp.presentation.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.softworkshub.newsapp.util.Dimens.MediumPadding2
import com.softworkshub.newsapp.util.Dimens.PageIndicatorWidth
import com.softworkshub.newsapp.presentation.common.NewsTextButton
import com.softworkshub.newsapp.presentation.onboarding.component.OnBoardingPage
import com.softworkshub.newsapp.presentation.onboarding.component.PageIndicator
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(
    event: (OnBoardingEvent) -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }

        val buttonState = remember{
            derivedStateOf {
                when (pagerState.currentPage){
                    0 -> listOf("","Next")
                    1 -> listOf("Back","Next")
                    2 -> listOf("Back","Get Started")
                    else -> listOf("","")
                }
            }
        }

        HorizontalPager(state = pagerState) {index ->
            OnBoardingPage(page = pages[index])

        }
        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2)
                .navigationBarsPadding()
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PageIndicator(modifier = Modifier.width(PageIndicatorWidth),pageSize = pages.size, selectedPage = pagerState.currentPage)

            val scope = rememberCoroutineScope()
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (buttonState.value[0].isNotEmpty()) {
                    NewsTextButton(
                        text = buttonState.value[0],
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage -1
                                )
                            }
                        }
                    )
                }
                
                NewsTextButton(
                    text = buttonState.value[1],
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage == 2){
                                // NAVIGATE TO THE MAIN SCREEN AND SAVE A VALUE IN DATASTORE REFERENCE

                                event(OnBoardingEvent.SaveAppEntry)
                            }else{
                                pagerState.animateScrollToPage(
                                    page =  pagerState.currentPage+1
                                )
                            }
                        }
                    }
                )


            }

        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}