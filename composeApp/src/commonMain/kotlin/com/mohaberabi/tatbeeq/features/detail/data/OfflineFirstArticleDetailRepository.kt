package com.mohaberabi.tatbeeq.features.detail.data

import com.mohaberabi.tatbeeq.core.data.network.CommonParams
import com.mohaberabi.tatbeeq.core.data.network.EndPoints
import com.mohaberabi.tatbeeq.core.domain.model.ArticleDetailModel
import com.mohaberabi.tatbeeq.core.domain.model.MimeType
import com.mohaberabi.tatbeeq.core.domain.model.ShareFileModel
import com.mohaberabi.tatbeeq.core.domain.source.remote.DownloadManager
import com.mohaberabi.tatbeeq.core.util.AppResult
import com.mohaberabi.tatbeeq.core.util.EmptyDataResult
import com.mohaberabi.tatbeeq.core.util.error.AppError
import com.mohaberabi.tatbeeq.core.util.error.ErrorModel
import com.mohaberabi.tatbeeq.core.util.foldResult
import com.mohaberabi.tatbeeq.core.util.handleAppResult
import com.mohaberabi.tatbeeq.core.util.map
import com.mohaberabi.tatbeeq.features.detail.domain.repository.ArticleDetailRepository
import com.mohaberabi.tatbeeq.features.detail.domain.source.local.ArticleDetailLocalDataSource
import com.mohaberabi.tatbeeq.platform_module.ShareManager

class OfflineFirstArticleDetailRepository(
    private val downloadManager: DownloadManager,
    private val shareManager: ShareManager,
    private val articleDetailLocalDataSource: ArticleDetailLocalDataSource,
) : ArticleDetailRepository {
    override suspend fun getArticleMarkdown(
        fileName: String,
    ): AppResult<String, ErrorModel> {
        return handleAppResult {
            val localDetail = articleDetailLocalDataSource.getArticleDetail(fileName)
            localDetail?.markdown ?: run {
                val bytes = downloadManager.downloadFile(EndPoints.ARTICLES_BUCKET, fileName)
                val decodedMd = bytes.decodeToString()
                val detail = ArticleDetailModel(fileName, decodedMd)
                articleDetailLocalDataSource.insertArticleDetail(detail)
                decodedMd
            }
        }

    }


    override suspend fun generateArticlePdf(
        pdfPath: String,
    ): EmptyDataResult<ErrorModel> = handleAppResult {
        val bytes = downloadManager.downloadFile(EndPoints.ARTICLES_BUCKET, pdfPath)
        val shared = ShareFileModel(
            mime = MimeType.PDF,
            fileName = pdfPath,
            bytes = bytes
        )
        shareManager.shareFile(shared)
    }

}

