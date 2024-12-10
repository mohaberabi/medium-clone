package com.mohaberabi.tatbeeq.core.presentation.compose

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mikepenz.markdown.coil3.Coil3ImageTransformerImpl
import com.mikepenz.markdown.compose.Markdown
import com.mikepenz.markdown.compose.components.markdownComponents
import com.mikepenz.markdown.compose.elements.MarkdownHighlightedCodeBlock
import com.mikepenz.markdown.compose.elements.MarkdownHighlightedCodeFence
import com.mikepenz.markdown.model.MarkdownColors
import com.mikepenz.markdown.model.MarkdownTypography
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun rememberMarkdownColors(): MarkdownColors {
    val textColor = MaterialTheme.typography.bodyMedium.color

    val colors = object : MarkdownColors {
        override val codeBackground: Color
            get() = Color(0xFFEFEFEF)
        override val codeText: Color
            get() = Color(0xFF2E7D32)
        override val dividerColor: Color
            get() = Color(0xFFBDBDBD)
        override val inlineCodeBackground: Color
            get() = Color(0xFFD3D3D3)
        override val inlineCodeText: Color
            get() = Color(0xFF1565C0)
        override val linkText: Color
            get() = Color.Blue
        override val text: Color
            get() = textColor
    }

    return remember {
        colors
    }
}


@Composable
fun rememberMarkdownTypography(): MarkdownTypography {
    val textColor = MaterialTheme.typography.bodyMedium.color
    val typo = object : MarkdownTypography {
        override val bullet: TextStyle
            get() = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        override val code: TextStyle
            get() = TextStyle(
                fontFamily = FontFamily.Monospace,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF2E7D32)
            )
        override val h1: TextStyle
            get() = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        override val h2: TextStyle
            get() = TextStyle(
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        override val h3: TextStyle
            get() = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        override val h4: TextStyle
            get() = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = textColor
            )
        override val h5: TextStyle
            get() = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = textColor,
            )
        override val h6: TextStyle
            get() = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = textColor
            )
        override val inlineCode: TextStyle
            get() = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = textColor
            )
        override val link: TextStyle
            get() = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = textColor
            )
        override val list: TextStyle
            get() = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = textColor
            )
        override val ordered: TextStyle
            get() = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = textColor
            )
        override val paragraph: TextStyle
            get() = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = textColor
            )
        override val quote: TextStyle
            get() = TextStyle(
                fontSize = 16.sp,
                color = Color.Gray
            )
        override val text: TextStyle
            get() = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = textColor,
            )
    }
    return remember(MaterialTheme.typography) {
        typo
    }
}


@Composable
@Preview
fun AppMdText(
    content: String
) {

    val colors = rememberMarkdownColors()
    val type = rememberMarkdownTypography()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            Markdown(
                imageTransformer = Coil3ImageTransformerImpl,
                content = content,
                colors = colors,
                typography = type,
                components = markdownComponents(
                    codeBlock = {
                        MarkdownHighlightedCodeBlock(it.content, it.node)
                    },
                    codeFence = {
                        MarkdownHighlightedCodeFence(
                            it.content,
                            it.node,
                        )
                    },
                )
            )
        }
    }
}
