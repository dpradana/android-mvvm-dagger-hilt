package daniel.base.application.core.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import daniel.base.application.core.base.BaseFragment
import daniel.base.application.core.constant.Constant
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

fun Context.savePref(key: String, value: String) {
    val sharedPref = this.getSharedPreferences(Constant.PREFERENCE, Context.MODE_PRIVATE)
    sharedPref.edit().putString(key, value).apply()
}

fun Context.getPref(key: String): String? {
    this.getSharedPreferences(Constant.PREFERENCE, Context.MODE_PRIVATE).apply {
        return getString(key, "")
    }
}

fun Context.saveBooleanPref(key: String, value: Boolean) {
    val sharedPref = this.getSharedPreferences(Constant.PREFERENCE, Context.MODE_PRIVATE)
    sharedPref.edit().putBoolean(key, value).apply()
}

fun Context.getBooleanPref(key: String): Boolean {
    this.getSharedPreferences(Constant.PREFERENCE, Context.MODE_PRIVATE).apply {
        return getBoolean(key, false)
    }
}

fun Context.getJsonParam(json: String): RequestBody {
    return json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
}

fun BaseFragment.getJsonParam(json: String): RequestBody {
    return json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
}

fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url).apply(
        RequestOptions().diskCacheStrategy(
            DiskCacheStrategy.ALL)).into(this)
}

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.gone(){
    visibility = View.GONE
}

fun View.invisible(){
    visibility = View.INVISIBLE
}

