package com.mohaberabi.tatbeeq.platform_module

import com.mohaberabi.tatbeeq.core.domain.model.ShareFileModel
import com.mohaberabi.tatbeeq.core.util.AppResult
import com.mohaberabi.tatbeeq.core.util.EmptyDataResult
import com.mohaberabi.tatbeeq.core.util.error.AppError


expect class ShareManager {
    suspend fun shareFile(
        data: ShareFileModel
    ): EmptyDataResult<AppError>
}