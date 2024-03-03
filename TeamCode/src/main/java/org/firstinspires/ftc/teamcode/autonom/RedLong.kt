package org.firstinspires.ftc.teamcode.autonom

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.DeclarareMotoare
import org.firstinspires.ftc.teamcode.camerathings.CameraObjects.autoresult

@Autonomous
class RedLong: LinearOpMode(){

    var trajectory: trajectories = trajectories()
    override fun runOpMode() {
        DeclarareMotoare.initMotoare(this)

        setup.autonomsetup(this, isred = true)
        trajectory.automove(autoresult, isRed = true)
        if(autoresult == 0){
            trajectory.trajRedLong0()
        } else if (autoresult == 1){
            trajectory.trajRedLong1()
        } else {
            trajectory.trajRedLong2()
        }
    }
}