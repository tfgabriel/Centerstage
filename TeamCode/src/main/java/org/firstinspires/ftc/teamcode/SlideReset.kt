package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor

@TeleOp
class SlideReset : LinearOpMode() {

    @Throws(InterruptedException::class)
    override fun runOpMode() {


        val slid = hardwareMap.dcMotor["Slide"]

        slid.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        slid.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE

        waitForStart()
        if (isStopRequested) return
        while (opModeIsActive()) {
            slid.power = gamepad2.left_stick_y.toDouble()
        }
    }
}