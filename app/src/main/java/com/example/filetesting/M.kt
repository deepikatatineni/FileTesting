package com.example.filetesting

import java.io.File
import java.text.DecimalFormat

object M {
    private const val TAG = "M"
    private val format = DecimalFormat("#.##")
    private const val MiB = 1024 * 1024
    private const val KiB = 1024

    fun listFilesAndFilesSubDirectories(directory: File, files: ArrayList<FileModel>): ArrayList<FileModel> {
        //get all the files from a directory
        val fList = directory.listFiles()
        if (fList != null){
            for (file in fList) {
                if (file?.isFile == true) {
                    //Log.d(TAG, "path: ${file.absolutePath} name: ${file.name} size: ${file.length()}")

                    files.add(FileModel(file.name, file.length(), M.getFileSize(file.length())))
                } else if (file?.isDirectory == true) {
                    listFilesAndFilesSubDirectories(file, files)
                }
            }
        }

        return files
    }

    fun getFileSize(length: Long): String{
        if (length > MiB) {
            return format.format(length / MiB) + " MB"
        }
        if (length > KiB) {
            return format.format(length / KiB) + " KB"
        }
        return format.format(length) + " B"
    }
}