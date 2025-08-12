package com.ll

data class WiseSaying(val id: Int, val content: String, val author: String)
var wiseSayings = mutableListOf<WiseSaying>()
var lastIndex = 0

fun main() {
    println("==명언 앱==")
    //Scanner scanner = Scanner(System.in);

    while (true) {
        print("명령) ")
        val input = readLine()?.trim()

        if (input=="종료") {
            break
        } else if (input == "등록") {
            actionWrite()
        } else if (input == "목록") {
            actionList()
        }
    }
}

fun actionWrite() {

    print("명언 : ")
    val content = readLine()?.trim()
    print("작가 : ")
    val author = readLine()?.trim()

    lastIndex++
    val wiseSaying = WiseSaying(lastIndex, content ?:"" , author ?:"")

    wiseSayings.add(wiseSaying)


    println("${lastIndex}번 명언이 등록되었습니다.")
}

fun actionList(){
    println("번호/ 작가/ 명언")
    println("---------------------")

    var forListWiseSayings = findForList()
    for(wiseSaying in forListWiseSayings){
        println("${wiseSaying.id} / ${wiseSaying.author} / ${wiseSaying.content}")
    }
}

fun findForList(): MutableList<WiseSaying>{

    var forListWiseSayings = mutableListOf<WiseSaying>()
    for (i in wiseSayings.size -1 downTo 0){
        forListWiseSayings.add(wiseSayings.get(i))
    }
    return forListWiseSayings
}
