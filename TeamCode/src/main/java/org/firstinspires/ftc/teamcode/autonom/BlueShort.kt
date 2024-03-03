package org.firstinspires.ftc.teamcode.autonom

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.DeclarareMotoare
import org.firstinspires.ftc.teamcode.autonom.autovars.nottrascaseTime
import org.firstinspires.ftc.teamcode.camerathings.CameraObjects
import org.firstinspires.ftc.teamcode.camerathings.CameraObjects.autoresult

@Autonomous
class BlueShort: LinearOpMode(){

    var trajectory: trajectories = trajectories()

    override fun runOpMode() {
        DeclarareMotoare.initMotoare(this)

        setup.autonomsetup(this, isred = false)
        trajectory.automove(autoresult, isRed = false)
        trajectory.trajBlueShort()
    }
}