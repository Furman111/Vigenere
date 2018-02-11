import java.io.*

class Vigenere {

    companion object {
        val ALPHABET_CAPACITY = 26
        val ALPHABET = 'A'..'Z'
        val INITIAL_LETTER = 'Z'
    }

    var previousChar = INITIAL_LETTER

    var vigenereTable: MutableList<MutableList<Char>>

    /**
     * Инициализация таблицы Вижинера
     */
    init {
        vigenereTable = ArrayList(ALPHABET_CAPACITY)
        for (i in ALPHABET) {
            val innerList = ArrayList<Char>()
            for (j in 0 until ALPHABET_CAPACITY) {
                val position = (j + i.toInt() - 'A'.toInt()) % ALPHABET_CAPACITY
                innerList.add(ALPHABET.elementAt(position))
            }
            vigenereTable.add(innerList)
        }
        println("Vigenere's table:")
        for (row in vigenereTable) {
            println(row)
        }
    }

    /**
     * @param readFrom Текстовый файл, который необходимо зашифровать
     * @param writeTo Файл, в который будет записан зашифрованный текст
     */

    fun encryptText(readFrom: String, writeTo: String) {
        val reader = BufferedReader(FileReader(readFrom))
        val writer = BufferedWriter(FileWriter(File(writeTo)))
        writer.write("")

        var line: String?
        do {
            line = reader.readLine()
            if (line != null) {
                writer.appendln(line.encryptStr())
            }
            writer.flush()
        } while (line != null)
        reader.close()
        writer.close()
    }

    /**
     * Метод для грубой расшфровки
     * @param readFrom Текстовый файл, который необходимо расшифровать
     * @param writeTo Файл, в который будет записан расшифрованный текст
     */

    fun bruteForceDectypt(readFrom: String, writeTo: String) {
        val writer = BufferedWriter(FileWriter(File(writeTo)))
        writer.write("")


        for (firstLetter in ALPHABET) {
            writer.appendln("With first letter '$firstLetter':")
            writer.appendln()

            decryptText(readFrom, writer, firstLetter)

            writer.appendln()
            writer.appendln()
        }

        writer.close()
    }


    /**
     * @return зашифрованная строка
     */
    private fun String.encryptStr(): String {
        val sb = StringBuilder()
        for (letter in this) {
            var res = letter
            if (res.toUpperCase() in ALPHABET) {
                val row = letter.toUpperCase().toInt() - 'A'.toInt()
                val column = ALPHABET.indexOf(previousChar)
                res = vigenereTable[row][column]
                if (letter.isLowerCase())
                    res = res.toLowerCase()
                previousChar = letter.toUpperCase()
            }
            sb.append(res)
        }
        return sb.toString()
    }

    private fun decryptText(readFrom: String, writer: BufferedWriter, firstLetter: Char) {
        val reader = BufferedReader(FileReader(readFrom))
        bruteForcePreviousChar = firstLetter
        var line: String?
        do {
            line = reader.readLine()
            if (line != null) {
                writer.appendln(line.dectyptStr())
            }
            writer.flush()
        } while (line != null)
        reader.close()
    }

    var bruteForcePreviousChar = 'A'

    private fun String.dectyptStr(): String {
        val sb = StringBuilder()
        for (letter in this) {
            var res = letter
            if (res.toUpperCase() in ALPHABET) {
                val column = ALPHABET.indexOf(bruteForcePreviousChar)
                for (i in 0 until ALPHABET_CAPACITY) {
                    if (vigenereTable[i][column].toInt() == letter.toUpperCase().toInt()) {
                        res = ALPHABET.elementAt(i)
                        break
                    }
                }
                if (letter.isLowerCase())
                    res = res.toLowerCase()
                bruteForcePreviousChar = res.toUpperCase()
            }
            sb.append(res)
        }
        return sb.toString()
    }

}