package com.example.cdeluapk.ui.screens.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cdeluapk.data.model.*
import com.example.cdeluapk.data.repository.MainRepository
// import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
// import javax.inject.Inject

// @HiltViewModel
class FeedViewModel /* @Inject constructor(
    private val repository: MainRepository
) */ : ViewModel() {
    
    // Instancia temporal sin DI
    private val repository: MainRepository = MainRepository()

    private val _uiState = MutableStateFlow(FeedUiState())
    val uiState: StateFlow<FeedUiState> = _uiState.asStateFlow()

    init {
        loadFeed()
        loadActiveSurveys()
        loadLiveStatus()
    }

    fun loadFeed() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            
            repository.getNews().collect { result ->
                result.fold(
                    onSuccess = { newsWithPagination ->
                        _uiState.update { 
                            it.copy(
                                news = newsWithPagination.data,
                                isLoading = false,
                                error = null
                            )
                        }
                    },
                    onFailure = { exception ->
                        _uiState.update { 
                            it.copy(
                                isLoading = false,
                                error = exception.message ?: "Error desconocido"
                            )
                        }
                    }
                )
            }
        }
    }

    fun loadActiveSurveys() {
        viewModelScope.launch {
            val result = repository.getActiveSurveys()
            result.fold(
                onSuccess = { surveys ->
                    _uiState.update { it.copy(activeSurveys = surveys) }
                },
                onFailure = { /* Manejar error silenciosamente */ }
            )
        }
    }

    fun loadLiveStatus() {
        viewModelScope.launch {
            val result = repository.getLiveStatus()
            result.fold(
                onSuccess = { liveStatus ->
                    _uiState.update { it.copy(liveStatus = liveStatus) }
                },
                onFailure = { /* Manejar error silenciosamente */ }
            )
        }
    }

    fun voteSurvey(surveyId: Int, optionId: Int) {
        viewModelScope.launch {
            val result = repository.voteSurvey(surveyId, optionId)
            result.fold(
                onSuccess = {
                    // Recargar encuestas después de votar
                    loadActiveSurveys()
                },
                onFailure = { exception ->
                    _uiState.update { 
                        it.copy(error = exception.message ?: "Error al votar")
                    }
                }
            )
        }
    }

    fun toggleLike(newsId: Int) {
        viewModelScope.launch {
            val currentNews = _uiState.value.news.find { it.id == newsId }
            if (currentNews != null) {
                val result = if (currentNews.isLiked) {
                    repository.unlikeNews(newsId)
                } else {
                    repository.likeNews(newsId)
                }
                
                result.fold(
                    onSuccess = {
                        // Actualizar el estado local
                        val updatedNews = _uiState.value.news.map { news ->
                            if (news.id == newsId) {
                                news.copy(
                                    isLiked = !news.isLiked,
                                    likesCount = if (news.isLiked) news.likesCount - 1 else news.likesCount + 1
                                )
                            } else {
                                news
                            }
                        }
                        _uiState.update { it.copy(news = updatedNews) }
                    },
                    onFailure = { exception ->
                        _uiState.update { 
                            it.copy(error = exception.message ?: "Error al actualizar like")
                        }
                    }
                )
            }
        }
    }

    fun setCurrentTab(tab: FeedTab) {
        _uiState.update { it.copy(currentTab = tab) }
        // TODO: Filtrar contenido según la pestaña seleccionada
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}

data class FeedUiState(
    val news: List<News> = emptyList(),
    val activeSurveys: List<Survey> = emptyList(),
    val liveStatus: LiveStatus = LiveStatus(),
    val currentTab: FeedTab = FeedTab.ALL,
    val isLoading: Boolean = false,
    val error: String? = null
)

enum class FeedTab {
    ALL, NEWS, COMMUNICATIONS, COMMUNITY
}
