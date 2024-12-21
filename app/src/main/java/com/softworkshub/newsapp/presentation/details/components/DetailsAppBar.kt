package com.softworkshub.newsapp.presentation.details.components


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.softworkshub.newsapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsAppBar(
    onBrowsingClick:() -> Unit,
    onBookmarkClick:() -> Unit,
    onShareClick:() -> Unit,
    onBackClick:() -> Unit,
) {

    TopAppBar(
        title = { },
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            navigationIconContentColor = colorResource(id = R.color.body),
            actionIconContentColor = colorResource(id = R.color.body)
        ),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = onBookmarkClick) {
                Icon(painter = painterResource(id = R.drawable.baseline_bookmark_border_24),contentDescription = null)
            }
            IconButton(onClick = onShareClick) {
                Icon(imageVector = Icons.Default.Share,contentDescription = null)
            }
            IconButton(onClick = onBrowsingClick) {
                Icon(painter = painterResource(id = R.drawable.baseline_browsing),contentDescription = null)
            }
        }

    )
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DetailsAppBarPreview(){

    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)){
        DetailsAppBar(
            onBrowsingClick = {  },
            onBookmarkClick = {  },
            onShareClick = { },
            onBackClick = {}
        )
    }

}
