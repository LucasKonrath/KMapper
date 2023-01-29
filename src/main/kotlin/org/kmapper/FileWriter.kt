package org.kmapper

import com.squareup.kotlinpoet.FileSpec
import java.io.File

class FileWriter {

    fun saveFile(file: FileSpec) {

        val fileToSave = File("src/main/kotlin").apply { mkdir() }

        file
            .writeTo(fileToSave)
    }
}