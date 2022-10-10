package tictactoe

fun main() {
    // write your code here
    val ttt = TTT()
    ttt.updateByInput()
    ttt.printfield()
}

class TTT() {
    val row = 3
    val col = 3
    val field = Array(row, {Array(col, {0})})

    fun printfield() {
        println("---------")
        for (i in 0..field.lastIndex) {
            print("| ")
            for (j in 0..field[0].lastIndex) {
                printer(field[i][j])
            }
            print("|")
            println()
        }
        println("---------")
    }

    fun demo() {
        field[0][0] = -1
        field[0][1] = 1
        field[0][2] = -1
        field[1][0] = 1
        field[1][1] = -1
        field[1][2] = 1
        field[2][0] = -1
        field[2][1] = -1
        field[2][2] = 1
    }

    fun updateByInput() {
        val inputMsg = readln()
        var row = 0
        var col = 0
        inputMsg.toCharArray().withIndex().forEach {
            row = it.index / 3
            col = it.index % 3
            field[row][col] =
                when(it.value) {
                    '_' -> 0
                    'X' -> -1
                    'O' -> 1
                    else -> 0
                }
        }
    }

    fun printer(i: Int) {
        if (i == 0) {
            print("_ ")
        } else if (i < 0) {
            print("X ")
        } else {
            print("O ")
        }
    }

}