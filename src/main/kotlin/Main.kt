
fun main(args: Array<String>) {
    println("Wordle Helper")
    println()
    println("Los resultados se deben escribir asi: vagvv")
    println(" - v para los verdes")
    println(" - a para los amarillos")
    println(" - g para los grises")

    round(Words.list,6)

}

fun round(words: List<String>, attempts: Int) {

    println()
    print("Escribe la palabra: ")
    val word = readln().trim()
    if (!word.matches(Regex("^[a-zñ]{5}$"))) return round(words, attempts)

    print("Escribe el resultado (o dejalo en blanco para salir): ")
    val result = readln().trim()
    if (!result.matches(Regex("^[vag]{5}$"))) return round(words, attempts)

    val pairs = word.zip(result).withIndex()

    val filteredList = words
        .filter { w ->  pairs.all { pair -> isValid(
            w,
            pair,
            repeated = pairs.count{ c -> c.value.first == pair.value.first} > 1) }
        }

    println()
    filteredList.forEach{
       println(it)
    }

    if (attempts > 1) round(filteredList, attempts - 1)
}

fun isValid(word: String, pair: IndexedValue<Pair<Char, Char>>, repeated: Boolean): Boolean {
  return when (pair.value.second) {
      'v' -> word[pair.index] == pair.value.first
      'a' -> word.contains(pair.value.first)
      else -> if (repeated) {
          word[pair.index] != pair.value.first
      } else !word.contains(pair.value.first)
  }
}