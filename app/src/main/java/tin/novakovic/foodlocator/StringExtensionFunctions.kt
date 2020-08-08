package tin.novakovic.foodlocator

fun String?.removeWhiteSpaces(minimumLength : Int): String {
    return if (this.isNullOrEmpty() || this.count() <= minimumLength) ""
    else this.replace("\\s".toRegex(), "")
}