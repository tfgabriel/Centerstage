package org.firstinspires.ftc.teamcode.varsandfuncs

object mathfuncs {
    fun min(a: Double, b: Double): Double{
        return if(a > b){
            b
        } else{
            a
        }
    }
}