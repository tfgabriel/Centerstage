package org.firstinspires.ftc.teamcode.autonom

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.util.ElapsedTime
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
import org.firstinspires.ftc.teamcode.autonom.trajectories
import org.firstinspires.ftc.teamcode.autonom.autovars
import org.firstinspires.ftc.teamcode.autonom.autovars.backboardtoparkTime
import org.firstinspires.ftc.teamcode.autonom.autovars.intakedropTime
import org.firstinspires.ftc.teamcode.autonom.autovars.nottrascaseTime
import org.firstinspires.ftc.teamcode.autonom.autovars.outtakedropTime
import org.firstinspires.ftc.teamcode.autonom.autovars.parkfwdTime
import org.firstinspires.ftc.teamcode.autonom.autovars.rotationTime
import org.firstinspires.ftc.teamcode.autonom.autovars.slidemaxPos
import org.firstinspires.ftc.teamcode.autonom.autovars.slideminPos
import org.firstinspires.ftc.teamcode.autonom.autovars.trasmovefwdTime

@Autonomous
class RedShort : LinearOpMode() {

    var drivetrain: drivetrain = drivetrain()
    var intake: intake = intake()
    var slides: slides = slides()
    var outtake: outtake = outtake()
    override fun runOpMode() {
        DeclarareMotoare.initMotoare(this)
        waitForStart()

        //autonomsetup(this, isred = false)
        autoresult = 2
        automove(nottrascaseTime, autoresult)
        drivetrain.rotateright(rotationTime)
        intake.droppixel(outtakedropTime)
        drivetrain.movefwd(trasmovefwdTime)
        slides.extend(slidemaxPos)
        outtake.rotatefwd()
        outtake.drop(intakedropTime)
        outtake.resetposition()
        slides.retract(slideminPos)
        drivetrain.strafeleft(backboardtoparkTime)
        drivetrain.movefwd(parkfwdTime)

    }
}