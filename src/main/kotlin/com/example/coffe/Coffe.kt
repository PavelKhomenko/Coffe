package com.example.coffe

var water = 400
var milk = 540
var coffee = 120
var cups = 9
var money = 550
var hasExit = true

const val WTR_CAPPUCCINO = 200
const val WTR_LATTE = 350
const val WTR_ESPRESSO = 250

const val CFE_CAPPUCCINO = 12
const val CFE_LATTE = 20
const val CFE_ESPRESSO = 16

const val MLK_CAPPUCCINO = 100
const val MLK_LATTE = 75

const val ONE_CUP = 1

const val ESPRESSO_COST = 4
const val LATTE_COST = 7
const val CAPPUCCINO_COST = 6

const val ZERO = 0

fun main() {
    while (hasExit) startCoffeeMaker()
}

fun startCoffeeMaker() {
    print("Write action (buy, fill, take, remaining, exit): > ")
    when (readln()) {
        "buy" -> buy()
        "fill" -> fill()
        "take" -> take()
        "remaining" -> remaining()
        "exit" -> hasExit = false
        else -> return
    }
}

fun buy() {
    print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: > ")
    when (readln()) {
        "1" -> makeEspresso()
        "2" -> makeLatte()
        "3" -> makeCappuccino()
        "back" -> return
    }
    println()
}

fun fill() {
    print("Write how many ml of water do you want to add: > ")
    water += readln().toInt()
    print("Write how many ml of milk do you want to add: > ")
    milk += readln().toInt()
    print("Write how many grams of coffee beans do you want to add: > ")
    coffee += readln().toInt()
    print("Write how many disposable cups of coffee do you want to add: > ")
    cups += readln().toInt()

    println()
}

fun remaining() {
    println("The coffee machine has:")
    println("$water ml of water")
    println("$milk ml of milk")
    println("$coffee g of coffee beans")
    println("$cups disposable cups")
    println("\$$money of money")
    println()
}

fun makeEspresso() {
    // 250 ml of water and 16 g of coffee beans. It costs $4.
    if (isSufficient("espresso")) {
        println("I have enough resources, making you a coffee!")
        cups -= ONE_CUP
        water -= WTR_ESPRESSO
        coffee -= CFE_ESPRESSO
        money += ESPRESSO_COST
    } else {
        println(notEnough("espresso"))
    }
}

fun makeLatte() {
    // 350 ml of water, 75 ml of milk, and 20 g of coffee beans. It costs $7.
    if (isSufficient("latte")) {
        println("I have enough resources, making you a coffee!")
        cups -= ONE_CUP
        water -= WTR_LATTE
        milk -= MLK_LATTE
        coffee -= CFE_LATTE
        money += LATTE_COST
    } else {
        println(notEnough("latte"))
    }
}

fun makeCappuccino() {
    // 200 ml of water, 100 ml of milk, and 12 g of coffee. It costs $6.
    if (isSufficient("cappuccino")) {
        println("I have enough resources, making you a coffee!")
        cups -= ONE_CUP
        water -= WTR_CAPPUCCINO
        milk -= MLK_CAPPUCCINO
        coffee -= CFE_CAPPUCCINO
        money += CAPPUCCINO_COST
    } else {
        println(notEnough("cappuccino"))
    }
}

fun take() {
    println("I gave you $money")
    money = ZERO
    println()
}

fun isSufficient(type: String): Boolean {
    var isOkey = false
    when (type) {
        "espresso" -> if (water >= WTR_ESPRESSO && coffee >= CFE_ESPRESSO) isOkey = true
        "latte" -> if (water >= WTR_LATTE && coffee >= CFE_LATTE && milk >= MLK_LATTE) isOkey = true
        "cappuccino" -> if (water >= WTR_CAPPUCCINO && coffee >= CFE_CAPPUCCINO && milk >= MLK_CAPPUCCINO) {
            isOkey = true
        }
    }
    return isOkey
}

fun notEnough(type: String): String {
    var what = ""

    val inLatte = if (water < WTR_LATTE) {
        "water"
    } else if (milk < MLK_LATTE) {
        "milk"
    } else {
        "coffee"
    }
    val inCappuccino = if (water < WTR_CAPPUCCINO) {
        "water"
    } else if (milk < MLK_CAPPUCCINO) {
        "milk"
    } else {
        "coffee"
    }
    when (type) {
        "espresso" -> what = if (water < WTR_ESPRESSO) "water" else "coffee"
        "latte" -> what = inLatte
        "cappuccino" -> what = inCappuccino
    }
    return "Sorry, not enough $what!"
}
