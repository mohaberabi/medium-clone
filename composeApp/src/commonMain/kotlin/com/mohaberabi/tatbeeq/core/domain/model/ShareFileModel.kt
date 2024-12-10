package com.mohaberabi.tatbeeq.core.domain.model


enum class MimeType {
    PDF
}

class ShareFileModel(
    val mime: MimeType = MimeType.PDF,
    val fileName: String,
    val bytes: ByteArray
)
