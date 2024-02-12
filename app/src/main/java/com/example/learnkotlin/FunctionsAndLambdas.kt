package com.example.learnkotlin

fun main() {
    //val trickFunction = ::trick//"::" is a function reference operator. We use it before a function to show that it is a function
    //or we could say trick() instead of ::trick

   /* val trickFunction = trick//trick is a function but it is defined as a variable with a lambda expression
    trickFunction()//returns "No treats"
    trick()//returns "No treats"

    val treatFunction = trickOrTreat(false)
    val trickFunction1 = trickOrTreat(true)

    treatFunction()//returns "Have a treat"
    trickFunction1()//returns "No treats"*/

    /*val coins: (Int) -> String = { quantity ->//we write anything instead of "quantity" if we write the same in beginning and in the expression
        "$quantity quarters"
    }*/

    val coins: (Int) -> String = {
        "$it quarters"//I can write "it" instead of passing parameter here explicitly. I directly takes parameter to the expression
        //But it is better to pass parameter explicitly to make code more understandable
    }

    val cupcake: (Int) -> String = {//I didn't pass the parameter to the lambda expression
        "Have a cupcake!"
    }

    val treatFunction = trickOrTreat(false, coins) // I can directly write a lambda expression instead of passing the function
                                                            //"{ "$it quarters" }" instead of "coins". It does same job "coins" do
    //val treatFunction = trickOrTreat(false) { "$it quarters" }  //Here I can move the lambda expression to the outside of the function
    //Doc says it becomes more readable but I think It becomes more confusing

    //val trickFunction = trickOrTreat(true, cupcake)

    repeat(4) {
        treatFunction()//In the definition of treatFunction, it returns 5 quarters because coins function works.
                        //But here coins function not works but treat function in the trickOTreat function works.
        //These examples just are examples to show how to use but not a good programming examples.
    }

    //trickFunction()


}

/*fun trick() {
    println("No treats!")
}*/

//Lambda expressions provide a concise syntax to define a function without the fun keyword. You can store a
// lambda expression directly in a variable without a function reference on another function.
//When you define a function with a lambda expression, you have a variable that refers to the function
val trick = {//here begins lambda expression
    println("No treats!")
}

val treat: () -> Unit = { //"()" is empty because it takes no parameter, and return type is Unit because it is returning nothing.
    //this is the explicit version of definition and the same as trick function upside.
    println("Have a treat!")
}

/*fun trickOrTreat(isTrick: Boolean): () -> Unit {//return type here is "() -> Unit" because we are returning function.
    //This is a function that takes boolean as parameter and return type is function.
    if (isTrick) {
        return trick
    } else {
        return treat
    }
}*/ //If I assign this function to a variable, It returns nothing. Only return when I run it

fun trickOrTreat(isTrick: Boolean, extraTreat: (Int) -> String): () -> Unit {//extraTreat is a parameter that we can put here a any function that
    // takes int as parameter and returns a string
    //******  "fun trickOrTreat(isTrick: Boolean, extraTreat: ((Int) -> String)?): () -> Unit {" I made the extraTreat function nullable by adding ?
    if (isTrick) {
        println(extraTreat(155))//here I used cupCake function as argument bu it never used "155" because I didn't used the parameter in cumCake function
        return trick
    } else {
        println(extraTreat(5))
        return treat
    }
}//If I assign this function to variable, even if  I don't run it, Println lines works in it, displayed extraTreat parameter results.