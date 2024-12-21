package com.softworkshub.newsapp.presentation.navgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.softworkshub.newsapp.presentation.bookmark.BookMarkViewModel
import com.softworkshub.newsapp.presentation.bookmark.BookmarkScreen
import com.softworkshub.newsapp.presentation.home.HomeScreen
import com.softworkshub.newsapp.presentation.home.HomeViewModel
import com.softworkshub.newsapp.presentation.news_navigator.NewsNavigator
import com.softworkshub.newsapp.presentation.onboarding.OnBoardingScreen
import com.softworkshub.newsapp.presentation.onboarding.OnBoardingViewModel
import com.softworkshub.newsapp.presentation.search.SearchScreen
import com.softworkshub.newsapp.presentation.search.SearchViewModel

@Composable
fun NavGraph(
    startDestination: String,
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination){
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ){
            composable(route = Route.OnBoardingScreen.route){
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = {
                        viewModel.onEvent(it)
                    }
                )
            }

        }

        navigation(
            route= Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route){
            composable(route = Route.NewsNavigatorScreen.route){
//                val viewModel :HomeViewModel = hiltViewModel()
//                val articles = viewModel.news.collectAsLazyPagingItems()
//                HomeScreen(articles = articles , navigate = {})
//                val viewModel : BookMarkViewModel = hiltViewModel()
//                BookmarkScreen(state = viewModel.state.value, navigateUp = {})

                NewsNavigator()
            }
        }
    }

}