package org.firstinspires.ftc.teamcode.hardware

import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.DeclarareMotoare.Intake

class intake {
    fun takepixel(maxtime: Double){
        var elapsedTime: ElapsedTime = ElapsedTime()
        elapsedTime.startTime()
        while(maxtime > elapsedTime.time()){
            Intake.power = -0.7
        }
        elapsedTime.reset()
    }

    fun droppixel(maxtime: Double){
        var elapsedTime: ElapsedTime = ElapsedTime()
        elapsedTime.startTime()
        while(maxtime > elapsedTime.time()){
            Intake.power = -0.7
        }
        Intake.power = 0.0
        elapsedTime.reset()
    }
}