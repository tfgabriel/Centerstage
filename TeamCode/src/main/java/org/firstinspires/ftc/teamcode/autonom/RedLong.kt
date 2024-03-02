package org.firstinspires.ftc.teamcode.autonom

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.DeclarareMotoare
import org.firstinspires.ftc.teamcode.FunctiiMiscare
import org.firstinspires.ftc.teamcode.FunctiiMiscare.automove
import org.firstinspires.ftc.teamcode.camerathings.CameraObjects
import org.firstinspires.ftc.teamcode.camerathings.CameraObjects.autoresult
import org.firstinspires.ftc.teamcode.hardware.drivetrain
import org.firstinspires.ftc.teamcode.hardware.intake
import org.firstinspires.ftc.teamcode.hardware.outtake
import org.firstinspires.ftc.teamcode.hardware.slides
import kotlin.math.PI

@Autonomous
class RedLong: LinearOpMode(){

    var drivetrain: drivetrain = drivetrain()
    var intake: intake = intake()
    var slides: slides = slides()
    var outtake: outtake = outtake()
    override fun runOpMode() {
        DeclarareMotoare.initMotoare(this)
        waitForStart()
        //autonomsetup(this, isred = false)
        autoresult = 2
        automove(150.0, autoresult)
        drivetrain.rotateright(100.0)
        intake.droppixel(50.0)
        drivetrain.diagmove(2 * PI / 3, 200.0)
        drivetrain.diagmove(PI / 3, 300.0)
        slides.extend(0)
        outtake.rotatefwd()
        outtake.drop(50.0)
        outtake.resetposition()
        slides.retract(0)
        drivetrain.strafeleft(50.0)
        drivetrain.movefwd(10.0)

    }
}