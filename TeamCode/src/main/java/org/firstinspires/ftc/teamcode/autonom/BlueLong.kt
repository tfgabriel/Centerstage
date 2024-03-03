package org.firstinspires.ftc.teamcode.autonom

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.DeclarareMotoare
import org.firstinspires.ftc.teamcode.camerathings.CameraObjects

@Autonomous
class BlueLong: LinearOpMode() {

    var trajectory: trajectories = trajectories()
    override fun runOpMode() {
        DeclarareMotoare.initMotoare(this)

        setup.autonomsetup(this, isred = false)
        trajectory.automove(CameraObjects.autoresult, isRed = false)
        trajectory.trajBlueShort()
    }
}