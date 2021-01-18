package tin.novakovic.foodlocator.common

import android.app.Application
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

class ContextWrapper(private val context: Application) {

    fun getColor(@ColorRes colourRes: Int) = ContextCompat.getColor(context, colourRes)

    fun getString(@StringRes stringRes: Int) = context.getString(stringRes)
}