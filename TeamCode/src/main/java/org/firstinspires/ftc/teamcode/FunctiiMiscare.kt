package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.DeclarareMotoare.LB
import org.firstinspires.ftc.teamcode.DeclarareMotoare.LF
import org.firstinspires.ftc.teamcode.DeclarareMotoare.RB
import org.firstinspires.ftc.teamcode.DeclarareMotoare.RF
import org.firstinspires.ftc.teamcode.DeclarareMotoare.timptrecut
import org.firstinspires.ftc.teamcode.varsandfuncs.mathfuncs


object
FunctiiMiscare{


    fun MiscareVerticala(putere : Double = 0.0, timp: Int = 0) {


        timptrecut.reset()

        while (timptrecut.milliseconds() < timp) {


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


            LF.power = frontLeftPower / timp * timptrecut.milliseconds()
            LB.power = backLeftPower / timp * timptrecut.milliseconds()
            RF.power = frontRightPower / timp * timptrecut.milliseconds()
            RB.power = backRightPower / timp * timptrecut.milliseconds()
        }
        LB.power = 0.0
        LB.power = 0.0
        RF.power = 0.0
        RB.power = 0.0

    }

    //
    fun move(maxtime: Double, autoresult: Int){


        if(autoresult == 0){
            //ma duc la tras
            DeclarareMotoare.timptrecut.startTime()
            while(maxtime > timptrecut.time()){
                LF.power = mathfuncs.min(timptrecut.seconds() / maxtime, 1.0)
                RB.power = mathfuncs.min(timptrecut.seconds() / maxtime, 1.0)
            }
            DeclarareMotoare.timptrecut.reset()
        } else if(autoresult == 1){
            //ma duc in fata
            DeclarareMotoare.timptrecut.startTime()
            while(maxtime > DeclarareMotoare.timptrecut.time()){
                LF.power = mathfuncs.min(timptrecut.seconds() / maxtime, 1.0)
                LB.power = mathfuncs.min(timptrecut.seconds() / maxtime, 1.0)
                RF.power = mathfuncs.min(timptrecut.seconds() / maxtime, 1.0)
                RB.power = mathfuncs.min(timptrecut.seconds() / maxtime, 1.0)
            }
            DeclarareMotoare.timptrecut.reset()
        } else {
            //ma duc in alta parte
            DeclarareMotoare.timptrecut.startTime()
            while(maxtime > timptrecut.time()){
                RF.power = mathfuncs.min(timptrecut.seconds() / maxtime, 1.0)
                LB.power = mathfuncs.min(timptrecut.seconds() / maxtime, 1.0)
            }
            DeclarareMotoare.timptrecut.reset()
        }
    }



    fun MiscareDeRotire(putere: Double, timp: Int = 0){


        timptrecut.reset()
        while(timptrecut.milliseconds() < timp) {

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

            LF.power = frontLeftPower / timp * timptrecut.milliseconds()
            LB.power = backLeftPower / timp * timptrecut.milliseconds()
            RF.power = frontRightPower / timp * timptrecut.milliseconds()
            RB.power = backRightPower / timp * timptrecut.milliseconds()


        }

        LB.power = 0.0
        LB.power = 0.0
        RF.power = 0.0
        RB.power = 0.0

    }
}