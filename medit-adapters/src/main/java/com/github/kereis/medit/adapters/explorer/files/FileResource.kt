package com.github.kereis.medit.adapters.explorer.files

import java.sql.Timestamp

data class FileResource(
    val fileName: String,
    val fileExtension: String?,
    val filePath: String,
    val lastAccess: Timestamp,
)