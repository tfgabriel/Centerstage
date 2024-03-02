package org.firstinspires.ftc.teamcode.hardware

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.DeclarareMotoare.Slide

class slides {

    fun extend(targetpos: Int){
        if(Slide.currentPosition < targetpos){
            Slide.targetPosition = targetpos
            Slide.mode = DcMotor.RunMode.RUN_TO_POSITION
            Slide.power = 0.75
        }
    }

    fun retract(targetpos: Int){
        if(Slide.currentPosition > targetpos){
            Slide.targetPosition = targetpos
            Slide.mode = DcMotor.RunMode.RUN_TO_POSITION
            Slide.power = 0.75
        }
    }
}