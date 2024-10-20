package com.manuelklyukvin.core.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manuelklyukvin.core.presentation.navigation.NavigationState
import com.manuelklyukvin.core.presentation.navigation.rememberNavigationState
import com.manuelklyukvin.core.presentation.theme.resources.Colors
import com.manuelklyukvin.core.presentation.theme.resources.Fonts

val LocalNavigationState = compositionLocalOf<NavigationState> {
    error("No NavigationState provided")
}

private val localColorScheme = staticCompositionLocalOf<AppColorScheme> {
    error("No color scheme provided")
}

private val localShapes = staticCompositionLocalOf<AppShapes> {
    error("No shapes provided")
}

private val localTypography = staticCompositionLocalOf<AppTypography> {
    error("No typography provided")
}

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val colorScheme = AppColorScheme(
        black = Colors.black,
        grey1 = Colors.gray1,
        gray2 = Colors.gray2,
        gray3 = Colors.gray3,
        gray4 = Colors.gray4,
        gray5 = Colors.gray5,
        white = Colors.white,
        blue = Colors.blue,
        darkBlue = Colors.darkBlue,
        green = Colors.green,
        darkGreen = Colors.darkGreen,
        red = Colors.red,
        shadows = Colors.shadows
    )

    val shapes = AppShapes(
        paddingExtraSmall = 8.dp,
        paddingSmall = 10.dp,
        paddingMedium = 16.dp,
        paddingLarge = 24.dp,
        paddingExtraLarge = 32.dp,
        sizeExtraSmall = 16.dp,
        sizeSmall = 24.dp,
        sizeMedium = 32.dp,
        sizeLarge = 40.dp,
        sizeExtraLarge = 48.dp,
        radiusSmall = RoundedCornerShape(8.dp),
        radiusMedium = RoundedCornerShape(10.dp),
        radiusLarge = RoundedCornerShape(50.dp)
    )

    val typography = AppTypography(
        title1 = TextStyle(
            fontFamily = Fonts.SFProDisplay,
            fontWeight = FontWeight.SemiBold,
            fontSize = 22.sp
        ),
        title2 = TextStyle(
            fontFamily = Fonts.SFProDisplay,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        ),
        title3 = TextStyle(
            fontFamily = Fonts.SFProDisplay,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        ),
        title4 = TextStyle(
            fontFamily = Fonts.SFProDisplay,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        ),
        text1 = TextStyle(
            fontFamily = Fonts.SFProDisplay,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        buttonText1 = TextStyle(
            fontFamily = Fonts.SFProDisplay,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        ),
        buttonText2 = TextStyle(
            fontFamily = Fonts.SFProDisplay,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        tabText = TextStyle(
            fontFamily = Fonts.SFProDisplay,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp
        ),
        number = TextStyle(
            fontFamily = Fonts.SFProDisplay,
            fontWeight = FontWeight.Normal,
            fontSize = 7.sp
        ),
    )

    CompositionLocalProvider(
        LocalNavigationState provides rememberNavigationState(),
        localColorScheme provides colorScheme,
        localShapes provides shapes,
        localTypography provides typography,
        content = content
    )
}

object AppTheme {

    val colorScheme: AppColorScheme
        @Composable
        get() = localColorScheme.current

    val shapes: AppShapes
        @Composable
        get() = localShapes.current

    val typography: AppTypography
        @Composable
        get() = localTypography.current
}