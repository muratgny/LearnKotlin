package com.example.learnkotlin
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/*
* This is a base class and also it has a constructor in the definition. It is called primary constructor.
* Definition of it can be seen in main function.
* */
open class SmartDevice(val name: String, val category: String) {

    open class SmartDevice protected constructor (val name: String, val category: String, val channel:Int) {

        // ...
        // This is the second constructor. it is overrideable, it is protected. We put here to show how it is written

    }

    var deviceStatus = "online" //mutable variable
        protected set
        /*Open version of this setter
        * protected set(value) {
           field = value
          }
        * We made it as protected because we want to reach it from subclasses but not from outside
        * */

    open val deviceType = "unknown" //immutable but can be override in the subclasses.

    open fun turnOn() { //overrideable function "open keyword defines it as overrideable"
        deviceStatus = "on"
    }

    open fun turnOff() {
        deviceStatus = "off"
    }
}

/*
* This class inherited from SmartDevice class. It takes directly same parameters from base class.
* within the parameter assignments in base class parenthesis, parameters moved to subclass
* */
class SmartTvDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart TV" //overriding of a variable

    //"by" keyword is like "provided by". Here works as a getter and setter with the help of class "RangeRegulator
    //After "by" keyword, comes a delegate and can be delegation for class, property or parameters.
    private var speakerVolume by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)

    private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)

    //Example of usage of setters but we did the same job upside 2 lines
    /*private var speakerVolume = 2
        set(value) {
            if (value in 0..100) {
                field = value
            }
        }

    private var channelNumber = 1
        set(value) {
            if (value in 0..200) {
                field = value
            }
        }
*/

    fun increaseSpeakerVolume() {//function that is special to this class
        speakerVolume++
        println("Speaker volume increased to $speakerVolume.")
    }

    fun nextChannel() {//function that is special to this class
        channelNumber++
        println("Channel number increased to $channelNumber.")
    }

    override fun turnOn() {//overriding a function
        super.turnOn()//With super keyword, we can run functions in subclasses.
        println(
            "$name is turned on. Speaker volume is set to $speakerVolume and channel number is " +
                    "set to $channelNumber."
        )
    }

    override fun turnOff() {
        super.turnOff()
        println("$name turned off")
    }
}

class SmartLightDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart Light"

    private var brightnessLevel by RangeRegulator(initialValue = 0, minValue = 0, maxValue = 100)

    fun increaseBrightness() {
        brightnessLevel++
        println("Brightness increased to $brightnessLevel.")
    }

    override fun turnOn() {
        super.turnOn()
        brightnessLevel = 2
        println("$name turned on. The brightness level is $brightnessLevel.")
    }

    override fun turnOff() {
        super.turnOff()
        brightnessLevel = 0
        println("Smart Light turned off")
    }
}

/*
* This class uses other classes. It has full control. All jobs that are visible to user is done by this class
* */
class SmartHome(
    val smartTvDevice: SmartTvDevice,//defining a device
    val smartLightDevice: SmartLightDevice//defining other device
) {

    var deviceTurnOnCount = 0//mutable variable creation that belongs to this class
        private set

    fun turnOnTv() {//function to turn on tvDevice. Here it uses other class's function and reaches it with
                    // the device it creates in the beginning
        deviceTurnOnCount++
        smartTvDevice.turnOn()
    }

    fun turnOffTv() {
        deviceTurnOnCount--
        smartTvDevice.turnOff()
    }

    fun increaseTvVolume() {
        smartTvDevice.increaseSpeakerVolume()
    }

    fun changeTvChannelToNext() {
        smartTvDevice.nextChannel()
    }

    fun turnOnLight() {
        deviceTurnOnCount++
        smartLightDevice.turnOn()
    }

    fun turnOffLight() {
        deviceTurnOnCount--
        smartLightDevice.turnOff()
    }

    fun increaseLightBrightness() {
        smartLightDevice.increaseBrightness()
    }

    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
}

class RangeRegulator(
    initialValue: Int,
    private val minValue: Int,
    private val maxValue: Int
) : ReadWriteProperty<Any?, Int> {//ReadWriteProperty is an interface and we use its feature in this class

    var fieldData = initialValue

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in minValue..maxValue) {
            fieldData = value
        }
    }
}

fun main() {
    var smartDevice : SmartDevice = SmartTvDevice("Android TV", "Entertainment")
    smartDevice.turnOn()

    //We assign a new device as SmartDevice but type of is different than the first. I could do it because
    // their base class is the same class "SmartDevice"
    smartDevice = SmartLightDevice("Google Light", "Utility")
    smartDevice.turnOn()

    //------------------------------------------- I tried a different usage of classes with SmartHome class
    /*val smartDevice = SmartTvDevice("Android TV", "Entertainment")
    smartDevice.turnOn()

    val smartDevice2 = SmartLightDevice("Google Light", "Utility")
    smartDevice2.turnOn()


    val smartHome = SmartHome(smartDevice, smartDevice2 )
    println(smartHome.smartTvDevice.deviceStatus)
    smartHome.increaseTvVolume()
    println(smartHome.smartTvDevice.deviceType)
    smartHome.turnOffAllDevices()
    println(smartHome.smartTvDevice.deviceStatus + " " + smartHome.smartTvDevice.name)
    println(smartHome.smartLightDevice.deviceStatus + " " + smartHome.smartLightDevice.name)
    println(smartHome.deviceTurnOnCount)*/

}

//Overriding topics
open class Try
class Success : Try()//in order to inherit from Try class, we have to define Try class as open


open class Try1 {
    open fun isSuccess(): Boolean = false
}
class Success1 : Try1() {
    override fun isSuccess(): Boolean = true//we can override isSuccess function because we defined it as open in base class
}

class Success2 : Try1() {
    final override fun isSuccess(): Boolean = true//Subclasses of Success2 class cannot override isSuccess function. Because it has final keyword
}

//Sealed keyword usage
sealed interface Error // has implementations only in same package and module

sealed class IOError(): Error // extended only in same package and module
open class CustomError(): Error // can be extended wherever it's visible

//Inner class
//A nested class marked as inner can access the members of its outer class. Inner classes carry a reference to an object of an outer class:

class Outer {
    private val bar: Int = 1
    inner class Inner {
        fun foo() = bar
    }
}

val demo = Outer().Inner().foo() // == 1

/*********************************************************/
//Generics in class
class Question<T>(val questionText: String, val answer: T)


class Quiz {
    val question1 = Question<String>("Quoth the raven ___", "nevermore")
    val question2 = Question<Boolean>("The sky is green. True or false", false)
    val question3 = Question<Int>("How many days are there between full moons?", 28)

    //Singlaton object usage
//We define is as an object and it only keep one instance. it is a class but hold only one object. We cannot write methods for it but we
//can reach its properties directly. for ex: println("${StudentProgress.answered} of ${StudentProgress.total} answered.")
    companion object StudentProgress {
        var total: Int = 10
        var answered: Int = 3
    }

    //An extension property for Quiz class. We have to write a getter for extension properties
    val Quiz.StudentProgress.progressText: String
        get() = "${answered} of ${total} answered"

    //This is an extension function.
    fun Quiz.StudentProgress.printProgressBar() {
        repeat(Quiz.answered) { print("▓") }//answered property is not belong to quiz class but we put companion keyword for singleton
                                            // object StudentProgress
        repeat(Quiz.total - Quiz.answered) { print("▒") }
        println()
        println(Quiz.progressText)
    }

}
//***********************************************************************************************
//Interface Implementation
//We can override interfaces' properties and functions
interface ProgressPrintable {
    val progressText: String
    fun printProgressBar()
}

class Quiz2 : ProgressPrintable {
    override val progressText: String
        get() = "${answered} of ${total} answered"


    val question1 = Question<String>("Quoth the raven ___", "nevermore")
    val question2 = Question<Boolean>("The sky is green. True or false", false)
    val question3 = Question<Int>("How many days are there between full moons?", 28)

    //Singlaton object usage
//We define is as an object and it only keep one instance. it is a class but hold only one object. We cannot write methods for it but we
//can reach its properties directly. for ex: println("${StudentProgress.answered} of ${StudentProgress.total} answered.")
    companion object StudentProgress {
        var total: Int = 10
        var answered: Int = 3
    }

    override fun printProgressBar() {
        repeat(Quiz.answered) { print("▓") }
        repeat(Quiz.total - Quiz.answered) { print("▒") }
        println()
        println(progressText)
    }
}

//**************************************
//Definition of array. Array are limited for expanding its size. We will use Lists for it.
fun arrayDefinitionAndUsage(){
    val rockPlanets = arrayOf<String>("Mercury", "Venus", "Earth", "Mars")
    val rockPlanets2 = arrayOf("Mercury", "Venus", "Earth", "Mars")
    rockPlanets[3] = "Little Earth"
    println(rockPlanets[3])

}

//Usage of Lists
// List is an interface that defines properties and methods related to a read-only ordered collection of items.
//MutableList extends the List interface by defining methods to modify a list, such as adding and removing elements.
fun listDefinitionAndUsage(){
    val solarSystem = listOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
    println(solarSystem.size)
    println(solarSystem[2])
    println(solarSystem.indexOf("Earth"))
    println(solarSystem.indexOf("Pluto"))//Returns -1, because item doesnt exist

    for (planet in solarSystem) {
        println(planet)
    }

    //We can modify mutable list
    val mutableSolarSystem = mutableListOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
    mutableSolarSystem.add("Pluto")
    mutableSolarSystem.add(3, "Theia")//Add item to among others
    mutableSolarSystem.removeAt(9)
    mutableSolarSystem.remove("Future Moon")

    //These two lines check the same thing
    println(mutableSolarSystem.contains("Pluto"))
    println("Future Moon" in solarSystem)

}

//Sets: In a set, item doesn't  have an index but has a unique hash code.
//The benefit of sets is ensuring uniqueness. If you were writing a program to keep track of newly discovered planets, a set provides
// a simple way to check if a planet has already been discovered. With large amounts of data, this is often preferable to checking if
// an element exists in a list, which requires iterating over all the elements.
fun setDefinitionAndUsage(){
    val solarSystem = mutableSetOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
    println(solarSystem.size)
    solarSystem.add("Pluto")
    println(solarSystem.contains("Pluto"))

}

//Maps: Classic key value pairing like json format
fun mapsDefinitionAndUsage(){
    val solarSystem = mutableMapOf(
        "Mercury" to 0,
        "Venus" to 0,
        "Earth" to 1,
        "Mars" to 2,
        "Jupiter" to 79,
        "Saturn" to 82,
        "Uranus" to 27,
        "Neptune" to 14
    )
    solarSystem["Pluto"] = 5
    println(solarSystem["Jupiter"])


}

//*******************************************************************************************
class Cookie(
    val name: String,
    val softBaked: Boolean,
    val hasFilling: Boolean,
    val price: Double
)

val cookies = listOf(
    Cookie(
        name = "Chocolate Chip",
        softBaked = false,
        hasFilling = false,
        price = 1.69
    ),
    Cookie(
        name = "Banana Walnut",
        softBaked = true,
        hasFilling = false,
        price = 1.49
    ),
    Cookie(
        name = "Vanilla Creme",
        softBaked = false,
        hasFilling = true,
        price = 1.59
    ),
    Cookie(
        name = "Chocolate Peanut Butter",
        softBaked = false,
        hasFilling = true,
        price = 1.49
    ),
    Cookie(
        name = "Snickerdoodle",
        softBaked = true,
        hasFilling = false,
        price = 1.39
    ),
    Cookie(
        name = "Blueberry Tart",
        softBaked = true,
        hasFilling = true,
        price = 1.79
    ),
    Cookie(
        name = "Sugar and Sprinkles",
        softBaked = false,
        hasFilling = false,
        price = 1.39
    )
)


fun main2() {
    //foreach statement
    cookies.forEach {
        println("Menu item: ${it.name}")//It write the names of items
    }

    //map statement
    val fullMenu = cookies.map {
        "${it.name} - $${it.price}"
    }

    println("Full menu:")
    fullMenu.forEach {
        println(it)
    }

    //filter statement
    val softBakedMenu = cookies.filter {
        it.softBaked
    }

    println("Soft cookies:")
    softBakedMenu.forEach {
        println("${it.name} - $${it.price}")
    }

    //groupBy statement
    //The groupBy() function can be used to turn a list into a map, based on a function. Each unique return value of the function
    // becomes a key in the resulting map. The values for each key are all the items in the collection that produced that unique
    // return value.
    val groupedMenu = cookies.groupBy { it.softBaked }
    val softBakedMenu1 = groupedMenu[true] ?: listOf()
    val crunchyMenu = groupedMenu[false] ?: listOf()

    //fold statement: it is used for sum up the values in a list
    //The fold() function is used to generate a single value from a collection. This is most commonly used for things like calculating
    // a total of prices, or summing all the elements in a list to find an average.
    val totalPrice = cookies.fold(0.0) {total, cookie ->
        total + cookie.price
    }

    //sortedby statement
    val alphabeticalMenu = cookies.sortedBy {
        it.name
    }

    println("Alphabetical menu:")
    alphabeticalMenu.forEach {
        println(it.name)
    }



}







