package tictactoe

fun main() {
    // write your code here
    val ttt = TTT()
//  ttt.printfield()
    ttt.demo()
    ttt.printfield()
}

class TTT() {
    val row = 3
    val col = 3
    val field = Array(row, {Array(col, {0})})

    fun printfield() {
        for (i in 0..field.lastIndex) {
            for (j in 0..field[0].lastIndex) {
                printer(field[i][j])
            }
            println()
        }
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

    fun printer(i: Int) {
        if (i == 0) {
            print("□ ")
        } else if (i < 0) {
            print("X ")
        } else {
            print("O ")
        }
    }

}