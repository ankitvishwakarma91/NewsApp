package com.softworkshub.newsapp.presentation.news_navigator.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softworkshub.newsapp.R
import com.softworkshub.newsapp.util.Dimens.ExtraSmallPadding2
import com.softworkshub.newsapp.util.Dimens.IconSize
import com.softworkshub.newsapp.ui.theme.NewsAppTheme

@Composable
fun NewsBottomNavigator(
    items:List<BottomNavigationItems>,
    selected : Int,
    onItemClicked:(Int) -> Unit,
) {

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, items ->
            NavigationBarItem(
                selected = index == selected,
                onClick = { onItemClicked(index) },
                icon = {
                    Column(
                        horizontalAlignment = CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = items.icon),
                            contentDescription = null,
                            modifier =  Modifier.size(IconSize)
                        )
                        Spacer(modifier = Modifier.height(ExtraSmallPadding2))
                        Text(text = items.text, style = MaterialTheme.typography.labelSmall)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(id = R.color.body),
                    unselectedTextColor = colorResource(id = R.color.body),
                    indicatorColor = MaterialTheme.colorScheme.background,
                )

            )
        }
    }
}

data class BottomNavigationItems(
    @DrawableRes val icon : Int,
    val text : String,
)

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NewsBottomNavigationPreview() {
    NewsAppTheme(dynamicColor = false) {
        NewsBottomNavigator(items = listOf(
            BottomNavigationItems(icon = R.drawable.baseline_home_24, text = "Home"),
            BottomNavigationItems(icon = R.drawable.baseline_search_24, text = "Search"),
            BottomNavigationItems(icon = R.drawable.baseline_bookmark_border_24, text = "Bookmark"),
        ),
            selected = 0,
            onItemClicked = {}
        )
    }
}



