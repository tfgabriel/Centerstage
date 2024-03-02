package org.firstinspires.ftc.teamcode.hardware

import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.DeclarareMotoare.RoataBucket
import org.firstinspires.ftc.teamcode.DeclarareMotoare.ServoBucket

class outtake {

    fun rotatefwd(){
        ServoBucket.position = 1.0
    }

    fun drop(maxtime: Double){
        var elapsedTime: ElapsedTime = ElapsedTime()
        elapsedTime.startTime()
        while(maxtime > elapsedTime.time()){
            RoataBucket.power = -0.5
        }
        elapsedTime.reset()
        RoataBucket.power = 0.0
    }

    fun resetposition(){
        ServoBucket.position = 0.0
    }
}