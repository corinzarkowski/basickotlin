package edu.uw.basickotlin

class Library {
    // This is just here as a placeholder, to make sure tests run and pass
    // before you add any code
    fun someLibraryMethod(): Boolean {
        return true
    }
}

// write a "whenFn" that takes an arg of type "Any" and returns a String

fun whenFn(x: Any): String {
    val returnVal = when {
        x == "Hello" -> "world"

        x is String -> "Say what?"

        x == 0 -> "zero"

        x == 1 -> "one"

        x is Int && (x >= 2 && x <= 10) -> "low number"

        x is Int || x is Float -> "a number"

        else -> "I don't understand"
    }

    return returnVal
}

// write an "add" function that takes two Ints, returns an Int, and adds the values

fun add(x: Int, y: Int): Int {
    return x + y
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values

fun sub(x: Int, y: Int): Int {
    return x - y
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments

fun mathOp(x: Int, y: Int, op: (x: Int, y: Int) -> Int): Int {
    return op(x, y)
}

// write a class "Person" with first name, last name and age

class Person (var firstName: String, var lastName: String, var age: Int) {
    val debugString: String = "[Person firstName:" + this.firstName + " lastName:" + this.lastName + " age:" + this.age + "]"
}

// write a class "Money" with amount and currency, and define a convert() method and the "+" operator

class Money (var amount: Int, var currency: String) {
    init {
        if (this.amount < 0) {
            throw IllegalArgumentException()
        }
        if (this.currency !in arrayOf("USD", "GBP", "EUR", "CAN")) {
            throw IllegalArgumentException()
        }
    }
    
    fun convert(newCur: String): Money {
        var newAmount: Double = when {
            this.currency == "USD" && newCur == "GBP" -> this.amount.toDouble() / 2
            this.currency == "USD" && newCur == "EUR" -> this.amount.toDouble() * 1.5
            this.currency == "USD" && newCur == "CAN" -> this.amount.toDouble() * 1.25

            this.currency == "GBP" && newCur == "USD" -> this.amount.toDouble() * 2
            this.currency == "GBP" && newCur == "EUR" -> this.amount.toDouble() * 3
            this.currency == "GBP" && newCur == "CAN" -> this.amount.toDouble() * 2.5

            this.currency == "EUR" && newCur == "GBP" -> this.amount.toDouble() / 3
            this.currency == "EUR" && newCur == "USD" -> (this.amount.toDouble() / 3) * 2
            this.currency == "EUR" && newCur == "CAN" -> (this.amount.toDouble() / 6) * 5

            this.currency == "CAN" && newCur == "GBP" -> this.amount.toDouble() * .4
            this.currency == "CAN" && newCur == "EUR" -> this.amount.toDouble() * 1.16
            this.currency == "CAN" && newCur == "USD" -> this.amount.toDouble() * .8

            else -> this.amount.toDouble()
        }

        var returnAmount = newAmount.toInt()
        return Money(returnAmount, newCur)
    }

    operator fun plus(secondMoney: Money): Money {
        var newSecondMoney = secondMoney.convert(this.currency)

        return Money(this.amount + newSecondMoney.amount, this.currency)
    }
}