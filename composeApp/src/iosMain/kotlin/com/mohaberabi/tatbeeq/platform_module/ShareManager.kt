package com.mohaberabi.tatbeeq.platform_module

import com.mohaberabi.tatbeeq.core.domain.model.ShareFileModel
import com.mohaberabi.tatbeeq.core.util.AppResult
import com.mohaberabi.tatbeeq.core.util.EmptyDataResult
import com.mohaberabi.tatbeeq.core.util.error.AppError
import com.mohaberabi.tatbeeq.core.util.error.LocalError
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.set
import platform.Foundation.NSData
import platform.Foundation.NSFileManager
import platform.Foundation.NSTemporaryDirectory
import platform.Foundation.NSURL
import platform.Foundation.create
import platform.Foundation.writeToFile
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIViewController
import kotlinx.cinterop.*
import kotlinx.coroutines.withContext
import platform.Foundation.*
import platform.UIKit.UIActivityItemSourceProtocol
import platform.UIKit.UIActivityType
import platform.UIKit.UIApplication
import platform.darwin.NSObject
import kotlin.coroutines.suspendCoroutine

actual class ShareManager(
    private val dispatchers: DispatchersProvider,
) {


    @OptIn(ExperimentalForeignApi::class)
    actual suspend fun shareFile(
        data: ShareFileModel
    ): EmptyDataResult<AppError> {
        return try {
            val bytes = data.bytes
            val fileName = data.fileName
            val fileUrl = withContext(dispatchers.io) {
                val tempDir = NSTemporaryDirectory()
                val sharedFile = tempDir + fileName
                bytes.usePinned {
                    val nsData = NSData.dataWithBytes(it.addressOf(0), bytes.size.toULong())
                    if (!nsData.writeToFile(sharedFile, true)) {
                        return@withContext AppResult.Error(LocalError.FILE_SAVE_ERROR)
                    }
                }
                val fileUrl = NSURL.fileURLWithPath(sharedFile)
                if (!fileUrl.checkResourceIsReachableAndReturnError(null)) {
                    return@withContext AppResult.Error(LocalError.FILE_SAVE_ERROR)
                }
                fileUrl
            }

            withContext(dispatchers.main) {
                val activityViewController = UIActivityViewController(
                    listOf(fileUrl),
                    null
                )

                UIApplication.sharedApplication.keyWindow?.rootViewController?.presentViewController(
                    activityViewController,
                    animated = true,
                    completion = null
                )
            }
            AppResult.Done(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            return AppResult.Error(LocalError.FILE_SAVE_ERROR)
        }
    }
}

