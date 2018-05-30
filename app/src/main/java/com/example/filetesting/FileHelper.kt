package com.example.filetesting

import android.content.Context
import android.os.Environment
import android.util.Log


class FileHelper private constructor(val context: Context){

    fun readAllFiles(){
        Environment.getExternalStorageDirectory().listFiles { dir, name ->
            Log.d(TAG, "dir : ${dir.absolutePath}")
            Log.d(TAG, "name : name")
            true
        }
    }



    companion object {
        private const val TAG = "FileHelper"
        private var INSTANCE: FileHelper? = null


        fun getInstance(context: Context): FileHelper{
            if (INSTANCE == null){
                INSTANCE = FileHelper(context)
            }
            return INSTANCE!!
        }


    }
}