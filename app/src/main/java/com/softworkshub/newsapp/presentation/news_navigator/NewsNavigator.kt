package com.softworkshub.newsapp.presentation.news_navigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.softworkshub.newsapp.R
import com.softworkshub.newsapp.domain.model.Article
import com.softworkshub.newsapp.presentation.bookmark.BookMarkViewModel
import com.softworkshub.newsapp.presentation.bookmark.BookmarkScreen
import com.softworkshub.newsapp.presentation.details.DetailsScreen
import com.softworkshub.newsapp.presentation.details.DetailsViewModel
import com.softworkshub.newsapp.presentation.home.HomeScreen
import com.softworkshub.newsapp.presentation.home.HomeViewModel
import com.softworkshub.newsapp.presentation.navgraph.Route
import com.softworkshub.newsapp.presentation.news_navigator.components.BottomNavigationItems
import com.softworkshub.newsapp.presentation.news_navigator.components.NewsBottomNavigator
import com.softworkshub.newsapp.presentation.search.SearchScreen
import com.softworkshub.newsapp.presentation.search.SearchViewModel

@Composable
fun NewsNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItems(icon = R.drawable.baseline_home_24, text = "Home"),
            BottomNavigationItems(icon = R.drawable.baseline_search_24, text = "Search"),
            BottomNavigationItems(icon = R.drawable.baseline_bookmark_border_24, text = "Bookmark"),
        )
    }

    val navController = rememberNavController()

    val backStackState = navController.currentBackStackEntryAsState().value

    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    selectedItem = when(backStackState?.destination?.route){
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.BookmarkScreen.route -> 2
        else -> 0
    }

    val isBottomVarVisible = remember(key1 = backStackState){
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.BookmarkScreen.route
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomVarVisible) {
                NewsBottomNavigator(
                    items = bottomNavigationItems,
                    selected = selectedItem,
                    onItemClicked = { idx ->
                        when (idx) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = Route.HomeScreen.route
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = Route.SearchScreen.route
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = Route.BookmarkScreen.route
                            )
                        }
                    }
                )
            }
        }
    ){
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ){

            composable(route = Route.HomeScreen.route){
                val viewModel : HomeViewModel = hiltViewModel()
                val article = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = article ,
                    navigateToSearch = {
                        navigateToTab(navController = navController,
                            route = Route.SearchScreen.route)
                    },
                    navigateToDetails = { article->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }

            composable(route = Route.SearchScreen.route){
                val viewModel : SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }

            composable(route = Route.DetailsScreen.route){
                val viewModel : DetailsViewModel = hiltViewModel()

                navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")
                    ?.let {article ->
                        DetailsScreen(
                            article = article,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() }
                        )
                    }
            }

            composable(route = Route.BookmarkScreen.route){
                val viewModel : BookMarkViewModel = hiltViewModel()

                BookmarkScreen(
                    state = viewModel.state.value,
                    navigateToDetails = { article ->
                        navigateToDetails(navController = navController,
                            article = article
                        )
                    }
                )
            }

        }
    }
}


private fun navigateToTab(
    navController : NavController,
    route:String,
){
    navController.navigate(route = route){
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen){
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetails(
    navController: NavController,
    article: Article
){
    navController.currentBackStackEntry?.savedStateHandle?.set("article",article)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}