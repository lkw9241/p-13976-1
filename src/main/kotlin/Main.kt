package com.ll

data class WiseSaying(val id: Int, var content: String, var author: String)
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
        } else if (input?.startsWith("삭제")==true) {
            actionDelete(input)
        }else if (input?.startsWith("수정")==true) {
            actionModify(input)
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

fun actionDelete(input: String){
    val cmdBits = input.split("=", limit = 2)
    if(cmdBits.size < 2 || cmdBits[1].isEmpty()){
        println("id를 다시 입력해주세요.")
        return
    }

    val id = cmdBits[1].toInt()

    val deleted = delete(id)
    if(!deleted){
        println("${id}번 명언은 존재하지 않습니다.")
    }else{
        println("${id}가 삭제되었습니다.")
    }
}

fun delete(id: Int): Boolean {

//    for(wiseSaying in wiseSayings){
//        if(wiseSaying.id == id){
//            wiseSayings.remove(wiseSaying)
//            return true
//        }
//    }
    return wiseSayings.removeIf {it.id == id}
//    return false
}

fun actionModify(input: String){
    val cmdBits = input.split("=", limit = 2)
    if(cmdBits.size < 2 || cmdBits[1].isEmpty()){
        println("id를 다시 입력해주세요.")
        return
    }

    val id = cmdBits[1].toIntOrNull()
    if(id == null){
        println("유효한 숫자르 입력해주세요.")
        return
    }

    val wiseSaying = findById(id)
    if(wiseSaying == null){
        println("${id}번 명언은 존재하지 않습니다.")
        return
    }

    println("명언(기존): ${wiseSaying.content}")
    print("명언:")
    val content = readLine()?.trim()

    println("명언(기존): ${wiseSaying.author}")
    print("작가:")
    val author = readLine()?.trim()

    modify(wiseSaying, content, author)
}

fun findById(id : Int): WiseSaying?{
    for(wiseSaying in wiseSayings){
        if(wiseSaying.id == id){
            return wiseSaying
        }
    }
    return null
}

fun modify(wiseSaying: WiseSaying, content: String?, author: String?){
    if (!content.isNullOrBlank()) {
        wiseSaying.content = content
    }
    if (!author.isNullOrBlank()) {
    wiseSaying.author = author
    }
    println("${wiseSaying.id}번 명언이 수정되었습니다.")
}


