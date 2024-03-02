package org.firstinspires.ftc.teamcode.hardware

import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.DeclarareMotoare
import org.firstinspires.ftc.teamcode.varsandfuncs.mathfuncs

class drivetrain {


    //angleforstart = 7PI/12
    //angleforstart = 5PI/12
    //anglepentrutras = 2PI/3
    //anglepentruback = PI/3

    fun movefwd(maxtime: Double){
        var elapsedTime: ElapsedTime = ElapsedTime()
        elapsedTime.startTime()
        while(maxtime > elapsedTime.time()){
            DeclarareMotoare.LF.power = mathfuncs.min(elapsedTime.seconds() / maxtime, 1.0)
            DeclarareMotoare.LB.power = mathfuncs.min(elapsedTime.seconds() / maxtime, 1.0)
            DeclarareMotoare.RF.power = mathfuncs.min(elapsedTime.seconds() / maxtime, 1.0)
            DeclarareMotoare.RB.power = mathfuncs.min(elapsedTime.seconds() / maxtime, 1.0)
        }
        elapsedTime.reset()
    }
    fun strafeleft(maxtime: Double){
        var elapsedTime: ElapsedTime = ElapsedTime()
        elapsedTime.startTime()
        while(maxtime > elapsedTime.time()){

            DeclarareMotoare.LB.power = mathfuncs.min(elapsedTime.seconds() / maxtime, 1.0)
            DeclarareMotoare.RF.power = mathfuncs.min(elapsedTime.seconds() / maxtime, 1.0)

            DeclarareMotoare.RB.power = mathfuncs.max(-elapsedTime.seconds() / maxtime, -1.0)
            DeclarareMotoare.LF.power = mathfuncs.max(-elapsedTime.seconds() / maxtime, -1.0)
        }
        elapsedTime.reset()
    }

    fun straferight(maxtime: Double){
        var elapsedTime: ElapsedTime = ElapsedTime()
        elapsedTime.startTime()
        while(maxtime > elapsedTime.time()){

            DeclarareMotoare.LF.power = mathfuncs.min(elapsedTime.seconds() / maxtime, 1.0)
            DeclarareMotoare.RB.power = mathfuncs.min(elapsedTime.seconds() / maxtime, 1.0)

            DeclarareMotoare.RF.power = mathfuncs.max(-elapsedTime.seconds() / maxtime, -1.0)
            DeclarareMotoare.LB.power = mathfuncs.max(-elapsedTime.seconds() / maxtime, -1.0)
        }
        elapsedTime.reset()
    }

    fun rotateleft(maxtime: Double){
        var elapsedTime: ElapsedTime = ElapsedTime()
        elapsedTime.startTime()
        while(maxtime > elapsedTime.time()){

            DeclarareMotoare.RB.power = 1.0
            DeclarareMotoare.RF.power = 1.0

            DeclarareMotoare.LB.power = -1.0
            DeclarareMotoare.LF.power = -1.0
        }
        elapsedTime.reset()
    }
    fun rotateright(maxtime: Double){
        var elapsedTime: ElapsedTime = ElapsedTime()
        elapsedTime.startTime()
        while(maxtime > elapsedTime.time()){

            DeclarareMotoare.LB.power = 1.0
            DeclarareMotoare.LF.power = 1.0

            DeclarareMotoare.RB.power = -1.0
            DeclarareMotoare.RF.power = -1.0
        }
        elapsedTime.reset()
    }

    fun diagmove(angle: Double, maxtime: Double){
        var elapsedTime: ElapsedTime = ElapsedTime()
        elapsedTime.startTime()
        while(maxtime > elapsedTime.time()){

            DeclarareMotoare.LB.power = (Math.cos(angle) + Math.sin(angle)) * mathfuncs.min(elapsedTime.seconds() / maxtime, 1.0)
            DeclarareMotoare.RF.power = (Math.cos(angle) + Math.sin(angle))* mathfuncs.min(elapsedTime.seconds() / maxtime, 1.0)

            DeclarareMotoare.RB.power = (Math.cos(angle) - Math.sin(angle)) * mathfuncs.min(elapsedTime.seconds() / maxtime, 1.0)
            DeclarareMotoare.RF.power = (Math.cos(angle) - Math.sin(angle)) * mathfuncs.min(elapsedTime.seconds() / maxtime, 1.0)
        }
        elapsedTime.reset()
    }

    fun rotationanddiag(){

    }
}