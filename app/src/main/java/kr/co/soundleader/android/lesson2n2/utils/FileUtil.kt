package kr.co.soundleader.android.lesson2n2.utils

import android.content.Context
import android.net.Uri
import android.os.Environment
import java.io.File
import java.io.FileOutputStream

class FileUtil {

    companion object{

        //==============================================================================
        // File / Uri 처리
        //==============================================================================
        fun createTempFile(context: Context, fileName: String): File {
            val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)  //DIRECTORY_DOWNLOADS | DIRECTORY_PICTURES
            return File(storageDir, fileName)
        }

        fun copyToFile(context: Context, uri: Uri, file: File) {
            val inputStream = context.contentResolver.openInputStream(uri)
            val outputStream = FileOutputStream(file)

            val buffer = ByteArray(4 * 1024)
            while (true) {
                val byteCount = inputStream!!.read(buffer)
                if (byteCount < 0) break
                outputStream.write(buffer, 0, byteCount)
            }
            outputStream.flush()
        }

        // get file name & extension
        fun getFileName(context: Context, uri: Uri): String {
            val name = uri.toString().split("/").last()
            val ext = context.contentResolver.getType(uri)!!.split("/").last()

            return "$name.$ext"
        }

    }
}