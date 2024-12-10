package com.mohaberabi.tatbeeq.platform_module

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import com.mohaberabi.tatbeeq.core.domain.model.MimeType
import com.mohaberabi.tatbeeq.core.domain.model.ShareFileModel
import com.mohaberabi.tatbeeq.core.util.AppResult
import com.mohaberabi.tatbeeq.core.util.EmptyDataResult
import com.mohaberabi.tatbeeq.core.util.error.AppError
import com.mohaberabi.tatbeeq.core.util.error.LocalError
import kotlinx.coroutines.withContext
import java.io.File


actual class ShareManager(
    private val context: Context,
    private val dispatchers: DispatchersProvider,
) {


    actual suspend fun shareFile(
        data: ShareFileModel
    ): EmptyDataResult<AppError> {
        return try {
            val pdfFile = saveFileToCacheDir(
                fileName = data.fileName,
                fileData = data.bytes
            )
            val uri = FileProvider.getUriForFile(
                context,
                context.packageName + ".provider",
                pdfFile
            )

            val intent = Intent(Intent.ACTION_SEND).apply {
                type = data.mime.toAndroidMimeType()
                putExtra(Intent.EXTRA_STREAM, uri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

            withContext(dispatchers.main) {
                context.startActivity(
                    intent
                )
            }
            AppResult.Done(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            AppResult.Error(LocalError.FILE_SAVE_ERROR)

        }
    }

    private suspend fun saveFileToCacheDir(
        fileName: String,
        fileData: ByteArray
    ): File {
        return withContext(dispatchers.io) {
            val cacheDir = context.cacheDir
            val file = File(cacheDir, fileName)
            file.writeBytes(fileData)
            file
        }
    }
}

private fun MimeType.toAndroidMimeType(): String = when (this) {
    MimeType.PDF -> "application/pdf"
}
