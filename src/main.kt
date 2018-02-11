import java.io.*

const val encodedFile = "/users/stepanfurman/documents/4k2s/Крипта/LAB1/encoded.txt"
const val decodedFile = "/users/stepanfurman/documents/4k2s/Крипта/LAB1/decoded.txt"
const val originalFile = "/users/stepanfurman/documents/4k2s/Крипта/LAB1/original_text.txt"

fun main(args: Array<String>) {
    val vigenere = Vigenere()
    vigenere.encryptText(readFrom = originalFile, writeTo = encodedFile)
    vigenere.bruteForceDectypt(readFrom = encodedFile, writeTo = decodedFile)
}

