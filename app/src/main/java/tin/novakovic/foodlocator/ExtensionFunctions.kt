package tin.novakovic.foodlocator

import android.view.View

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.show(boolean: Boolean) {
    visibility = if (boolean) View.VISIBLE else View.INVISIBLE
}
