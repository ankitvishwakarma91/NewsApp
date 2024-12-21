package com.softworkshub.newsapp.presentation.onboarding.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.softworkshub.newsapp.R
import com.softworkshub.newsapp.util.Dimens
import com.softworkshub.newsapp.presentation.onboarding.Page
import com.softworkshub.newsapp.presentation.onboarding.pages
import com.softworkshub.newsapp.ui.theme.NewsAppTheme

@Composable
fun OnBoardingPage(
    modifier : Modifier= Modifier,
    page:Page
) {
    Column(
        modifier = modifier
    ) {
        Image(
            modifier= Modifier
                .fillMaxWidth()
                .fillMaxSize(fraction = 0.6f),
            painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        
        Spacer(modifier = modifier.height(Dimens.MediumPadding1))
        
        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = Dimens.MediumPadding2),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.black)
        )
        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = Dimens.MediumPadding2),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.black)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun OnBoardingPagePreview() {
    NewsAppTheme {
        OnBoardingPage(page = pages[0])
    }
}