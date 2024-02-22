package org.firstinspires.ftc.teamcode

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.TelemetryPacket
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor


@TeleOp


class EncoderTest : LinearOpMode() {

    @Throws(InterruptedException::class)
    override fun runOpMode() {

        val slideTest = hardwareMap.dcMotor["Slide"]

        slideTest.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        slideTest.mode =  DcMotor.RunMode.RUN_USING_ENCODER
        slideTest.zeroPowerBehavior =DcMotor.ZeroPowerBehavior.BRAKE

        waitForStart()
        if (isStopRequested) return
        while (opModeIsActive()) {
            slideTest.power = gamepad2.left_stick_y.toDouble()

            val tp = TelemetryPacket()
            tp.put("slideTestCurPos", slideTest.currentPosition)

            FtcDashboard.getInstance().sendTelemetryPacket(tp)

        }
    }
}