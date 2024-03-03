package org.firstinspires.ftc.teamcode.autonom

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.DeclarareMotoare
import org.firstinspires.ftc.teamcode.camerathings.CameraObjects.autoresult
import org.firstinspires.ftc.teamcode.autonom.setup.autonomsetup

@Autonomous
class RedShort : LinearOpMode() {

    var trajectory: trajectories = trajectories()
    override fun runOpMode() {

        DeclarareMotoare.initMotoare(this)

        autonomsetup(this, isred = true)
        if(autoresult == 0){
            trajectory.trajRedShort0()
        } else if (autoresult == 1){
            trajectory.trajRedShort1()
        } else {
            trajectory.trajRedShort2()
        }
    }
}