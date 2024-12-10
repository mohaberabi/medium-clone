package com.mohaberabi.tatbeeq.app.navigation.toplevel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class TopLevelRouteViewModel : ViewModel() {


    private val _currentRoute = MutableStateFlow(TopLevelBottomItem.Home)

    val currentRoute = _currentRoute.asStateFlow()

    fun routeChanged(
        route: TopLevelBottomItem,
    ) = _currentRoute.update { route }


}