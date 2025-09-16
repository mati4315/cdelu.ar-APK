package com.example.cdeluapk.ui.screens.feed

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
// import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cdeluapk.R
import com.example.cdeluapk.ui.components.*
import com.example.cdeluapk.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(
    navController: NavController,
    viewModel: FeedViewModel = FeedViewModel() // Instancia temporal sin DI
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                actions = {
                    IconButton(onClick = { /* TODO: Notificaciones */ }) {
                        Icon(Icons.Default.Notifications, contentDescription = null)
                    }
                    IconButton(onClick = { navController.navigate(Screen.Profile.route) }) {
                        Icon(Icons.Default.Person, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = { /* Ya estamos en feed */ }) {
                        Icon(Icons.Default.Home, contentDescription = null)
                    }
                    IconButton(onClick = { navController.navigate(Screen.Survey.route) }) {
                        Icon(Icons.Default.List, contentDescription = null)
                    }
                    IconButton(onClick = { navController.navigate(Screen.Lottery.route) }) {
                        Icon(Icons.Default.Star, contentDescription = null)
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Crear publicación */ }
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Encuestas activas
            if (uiState.activeSurveys.isNotEmpty()) {
                item {
                    ActiveSurveysCard(
                        surveys = uiState.activeSurveys,
                        onVote = { surveyId, optionId ->
                            viewModel.voteSurvey(surveyId, optionId)
                        }
                    )
                }
            }
            
            // Transmisión en vivo
            if (uiState.liveStatus.isLive) {
                item {
                    LivePlayerCard(
                        liveStatus = uiState.liveStatus,
                        onFacebookClick = { /* TODO: Abrir Facebook */ }
                    )
                }
            }
            
            // Pestañas del feed
            item {
                FeedTabs(
                    currentTab = uiState.currentTab,
                    onTabChange = { viewModel.setCurrentTab(it) }
                )
            }
            
            // Contenido del feed
            when {
                uiState.isLoading -> {
                    items(3) {
                        NewsCardSkeleton()
                    }
                }
                uiState.error != null -> {
                    item {
                        ErrorCard(
                            message = uiState.error!!,
                            onRetry = { viewModel.loadFeed() }
                        )
                    }
                }
                uiState.news.isEmpty() -> {
                    item {
                        EmptyStateCard(
                            message = "No hay contenido disponible",
                            onRefresh = { viewModel.loadFeed() }
                        )
                    }
                }
                else -> {
                    items(uiState.news) { news ->
                        NewsCard(
                            news = news,
                            onLike = { viewModel.toggleLike(news.id) },
                            onComment = { navController.navigate("${Screen.NewsDetail.route}/${news.id}") },
                            onShare = { /* TODO: Compartir */ },
                            onNewsClick = { navController.navigate("${Screen.NewsDetail.route}/${news.id}") }
                        )
                    }
                }
            }
        }
    }
    
    // Observar efectos
    LaunchedEffect(Unit) {
        viewModel.loadFeed()
        viewModel.loadActiveSurveys()
        viewModel.loadLiveStatus()
    }
}
