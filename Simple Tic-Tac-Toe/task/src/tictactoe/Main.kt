package tictactoe
import java.lang.Exception
import kotlin.math.abs

fun main() {
    // write your code here
    val ttt = TTT()
    ttt.printfield()
    ttt.vsCtrl()
}

class TTT() {
    val row = 3
    val col = 3
    val field = Array(row, {Array(col, {0})})
    var countOfX = 0
    var countOfO = 0
    var gameOver = false
    var player = 0

    var notFinished = false
    var boardStatus = ""

    fun printfield() {
        println("---------")
        for (i in 0..field.lastIndex) {
            print("| ")
            for (j in 0..field[0].lastIndex) {
                printer(field[i][j])
            }
            println("|")
        }
        println("---------")
    }
    fun printer(i: Int) {
        if (i == 0) {       print("  ")
        } else if (i < 0) { print("X ")
        } else {            print("O ")
        }
    }

    fun checkState() {
        var xwin = false
        var owin = false
        var rowChk = Array<Int>(3) { 0 }
        var colChk = Array<Int>(3) { 0 }
        var diaChk = 0
        var idiChk = 0

        //init check below
        notFinished = false

        for (i in 0..field.lastIndex) {
            field[i].forEach { rowChk[i] += it }
            for (j in 0..field[0].lastIndex) {
                if (field[i][j] == 0) notFinished = true
            }
            for (k in 0..field.lastIndex) colChk[k] += field[i][k]
            diaChk += field[i][i]
        }
        idiChk += field[2][0] + field[1][1] + field[0][2]

        var whChk = rowChk.toList().toMutableList()
        whChk.addAll(colChk.toList())
        whChk.add(diaChk)
        whChk.add(idiChk)

        if (whChk.maxOf { it } == 3) owin = true
        if (whChk.minOf { it } == -3) xwin = true

        if (2 <= abs(countOfX - countOfO)) {
            boardStatus = "Impossible"
        } else {
            if (notFinished)    boardStatus = "Game not finished"
            if (xwin && owin) { boardStatus = "Impossible"
            } else if (xwin) {  boardStatus = "X wins"
            } else if (owin) {  boardStatus = "O wins"
            } else {
                if (!notFinished) boardStatus = "Draw"
            }
        }
        gameOver = xwin || owin || !notFinished
        if(gameOver) println(boardStatus)
    }

    fun updateByInput() {
        val inputMsg = readln()
        var row = 0
        var col = 0
        inputMsg.toCharArray().withIndex().forEach {
            row = it.index / 3
            col = it.index % 3
            if(it.value == 'X') countOfX++
            if(it.value == 'O') countOfO++
            field[row][col] =
                when(it.value) {
                    '_' -> 0
                    'X' -> -1
                    'O' -> 1
                    else -> 0
                }
        }
    }

    fun vsCtrl() {
        var sat = false
        while(!this.gameOver) {
            while(!sat) {
                sat = setByInput()
            }
            this.printfield()
            this.checkState()
            sat = false
        }
    }

    fun setByInput(): Boolean {
        print("..>")
        val inputMsg = readln().split(" ")
        var row = 0
        var col = 0
        try {
            if (inputMsg[0] in "0".."9" && inputMsg[1] in "0".."9") {
            } else {
                println("You should enter numbers!")
                throw Exception("not a number")
            }
            row = inputMsg[0].toInt() - 1
            col = inputMsg[1].toInt() - 1
            if ( field[row][col] != 0 ) {
                println("This cell is occupied! Choose another one!")
                throw Exception("already occupied")
            }
        } catch (ex: IndexOutOfBoundsException) {
            println("Coordinates should be from 1 to 3!")
            return false
        } catch (ex: Exception) {
            return false
        }
        if (this.player == 0) {
            field[row][col] = -1
            this.player = 1
        } else {
            field[row][col] = 1
            this.player = 0
        }

        return true
    }
}