package com.manuelklyukvin.core.presentation.ui.theme.resources

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.manuelklyukvin.core.presentation.R

internal object Fonts {

    val SFProDisplay = FontFamily(
        Font(
            resId = R.font.sf_pro_display_regular,
            weight = FontWeight.Normal
        ),
        Font(
            resId = R.font.sf_pro_display_medium,
            weight = FontWeight.Medium
        ),
        Font(
            resId = R.font.sf_pro_display_semibold,
            weight = FontWeight.SemiBold
        )
    )
}