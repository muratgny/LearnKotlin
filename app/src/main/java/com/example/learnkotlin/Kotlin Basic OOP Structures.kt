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
