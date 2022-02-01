object Words {

    val list: List<String> = this.javaClass.getResource("words.txt")!!.openStream().bufferedReader().readLines()

}