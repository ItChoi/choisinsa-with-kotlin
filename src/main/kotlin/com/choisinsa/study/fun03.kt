package com.choisinsa.study

fun main() {
    val number1 = 3
    val number2: Long = number1.toLong()

    var value : String? = null
    println(value as? String)
    println(11 as Integer)
    //println(11 as String) // exception

    val tec = "tec111"
    println("$tec")
    println("${tec}")

    val trimIndent = """
        오호...
        에헤...
        하하하하
    """.trimIndent()
    println(trimIndent)

    var sasa = "ABC"
    println(sasa[0])
    println(sasa[2])
    //println(sasa[22]) -> exception
    // is ?is as as?

    /**
     * Any -> java Object
     * Unit -> java void
     * Nothing -> 함수 정상 종료 X -> 코드상 예외만 존재, 많이 사용 X
     *
     */
}

fun printAgeIfPerson(obj: Any) {
    if (obj is String) {
        val str = obj as String
        println(str.toString())
    }

    var a = null
    if (obj !is String) {
        
    }
}
