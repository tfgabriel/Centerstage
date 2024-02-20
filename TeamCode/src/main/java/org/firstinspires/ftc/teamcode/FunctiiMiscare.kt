package org.firstinspires.ftc.teamcode

import org.firstinspires.ftc.teamcode.DeclarareMotoare.LB
import org.firstinspires.ftc.teamcode.DeclarareMotoare.LF
import org.firstinspires.ftc.teamcode.DeclarareMotoare.RB
import org.firstinspires.ftc.teamcode.DeclarareMotoare.RF


object FunctiiMiscare{


    fun MiscareVerticala(putere : Double = 0.0) {






        val y = -putere
        val x = 0 * 1.1 // Counteract imperfect strafing
        val rx = 0

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        val denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1.0)
        val frontLeftPower = (y + x + rx) / denominator
        val backLeftPower = (y - x + rx) / denominator
        val frontRightPower = (y - x - rx) / denominator
        val backRightPower = (y + x - rx) / denominator

        LF.power = frontLeftPower
        LB.power = backLeftPower
        RF.power = frontRightPower
        RB.power = backRightPower


    }


    fun MiscareDeRotire(putere: Double){


        val y = -0
        val x = 0 * 1.1 // Counteract imperfect strafing
        val rx = putere

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        val denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1.0)
        val frontLeftPower = (y + x + rx) / denominator
        val backLeftPower = (y - x + rx) / denominator
        val frontRightPower = (y - x - rx) / denominator
        val backRightPower = (y + x - rx) / denominator

        LF.power = frontLeftPower
        LB.power = backLeftPower
        RF.power = frontRightPower
        RB.power = backRightPower



    }
}