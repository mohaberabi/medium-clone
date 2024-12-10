package com.mohaberabi.tatbeeq.app.navigation.root


sealed class AppDeepLinkRoutes(
    val deepLinkId: String,
) {
    companion object {
        const val DOMAIN = "tatbeeq-app://"
    }

    data object ArticleDetail : AppDeepLinkRoutes(
        "${DOMAIN}article_detail"
    ) {
        fun generateDeepLink(
            mdPath: String,
            pdfPath: String
        ): String = "${deepLinkId}article_detail?mdPath=$mdPath&pdfPath=$pdfPath"
    }
}