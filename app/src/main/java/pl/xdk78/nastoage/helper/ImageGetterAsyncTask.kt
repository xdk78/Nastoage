package pl.xdk78.nastoage.helper

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.LevelListDrawable
import android.os.AsyncTask
import android.util.Log
import android.widget.TextView
import com.bumptech.glide.Glide


class ImageGetterAsyncTask(@SuppressLint("StaticFieldLeak") private val context: Context, private val source: String, private val levelListDrawable: LevelListDrawable) : AsyncTask<TextView, Void, Bitmap>() {
    @SuppressLint("StaticFieldLeak")
    private var t: TextView? = null
    private val TAG = "IMG"

    override fun doInBackground(vararg params: TextView): Bitmap? {
        t = params[0]
        return try {
            Log.d(TAG, "Downloading the image from: " + source)
            Glide.with(context).asBitmap().load(source).submit().get()
        } catch (e: Exception) {
            Log.e(TAG, e.message)
            null
        }

    }

    override fun onPostExecute(bitmap: Bitmap) {
        try {
            val d = BitmapDrawable(context.resources, bitmap)
            val size = Point()
            (context as Activity).windowManager.defaultDisplay.getSize(size)
            // Lets calculate the ratio according to the screen width in px
            val multiplier = size.x / bitmap.width
            Log.d(TAG, "multiplier: " + multiplier)
            levelListDrawable.addLevel(1, 1, d)
            // Set bounds width  and height according to the bitmap resized size
            levelListDrawable.setBounds(0, 0, bitmap.width * multiplier, bitmap.height * multiplier)
            levelListDrawable.level = 1
            t!!.text = t!!.text // invalidate() doesn't work correctly...
        } catch (e: Exception) {
            Log.e(TAG, e.message)
        }

    }
}