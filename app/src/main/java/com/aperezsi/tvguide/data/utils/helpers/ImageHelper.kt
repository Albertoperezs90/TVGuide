package com.aperezsi.tvguide.data.utils.helpers

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.aperezsi.tvguide.data.data.User
import java.io.ByteArrayOutputStream

object ImageHelper {

    fun encodeBitmap(bitmap: Bitmap) : String {
        val byteArray = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray)
        val photoByte = byteArray.toByteArray()

        val encodedBitmap = Base64.encodeToString(photoByte, Base64.DEFAULT)
        return encodedBitmap
    }

    fun decodeBitmap(user: User): Bitmap {
        val encodedBitmap = user.avatar
        val decodedBytes = Base64.decode(user.avatar.substring(user.avatar.indexOf(",") + 1), Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }
}