package com.manuelklyukvin.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.manuelklyukvin.core.presentation.R
import com.manuelklyukvin.core.presentation.navigation.Screen
import com.manuelklyukvin.core.presentation.theme.AppTheme
import com.manuelklyukvin.core.presentation.theme.LocalNavigationState
import com.manuelklyukvin.core.presentation.utils.noIndicationClickable

@Composable
fun AppNavBar() {
    Column {
        HorizontalDivider(
            color = AppTheme.colorScheme.gray2,
            thickness = 0.2.dp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colorScheme.black)
                .padding(
                    vertical = AppTheme.shapes.paddingExtraSmall,
                    horizontal = AppTheme.shapes.paddingMedium
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppNavBarItem(
                icon = painterResource(id = R.drawable.search),
                label = stringResource(id = R.string.core_nav_bar_search),
                route = Screen.Home,
                mainRoute = Screen.Feed
            )
            AppNavBarItem(
                icon = painterResource(id = R.drawable.favorite),
                label = stringResource(id = R.string.core_nav_bar_favorite),
                route = Screen.Favorite
            )
            AppNavBarItem(
                icon = painterResource(id = R.drawable.replies),
                label = stringResource(id = R.string.core_nav_bar_replies),
                route = Screen.Replies
            )
            AppNavBarItem(
                icon = painterResource(id = R.drawable.messages),
                label = stringResource(id = R.string.core_nav_bar_messages),
                route = Screen.Messages
            )
            AppNavBarItem(
                icon = painterResource(id = R.drawable.profile),
                label = stringResource(id = R.string.core_nav_bar_profile),
                route = Screen.Profile
            )
        }
    }
}

@Composable
private fun AppNavBarItem(
    icon: Painter,
    label: String,
    route: Any,
    mainRoute: Any? = null
) {
    val navigationState = LocalNavigationState.current
    val navBackStackEntry by navigationState.navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route
    val isSelected = navBackStackEntry?.destination?.hierarchy?.any {
        it.route?.contains(route.toString()) == true
    } ?: false

    val currentColor = if (isSelected) {
        AppTheme.colorScheme.blue
    } else {
        AppTheme.colorScheme.gray4
    }

    Column(
        modifier = Modifier.noIndicationClickable {
            if (!isSelected) {
                navigationState.navigate(route)
            }
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppIcon(
            modifier = Modifier.size(AppTheme.shapes.sizeSmall),
            painter = icon,
            tint = currentColor
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            style = AppTheme.typography.tabText,
            color = currentColor
        )
    }
}