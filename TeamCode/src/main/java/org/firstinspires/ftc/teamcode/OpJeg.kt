package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.dashboard.telemetry.TelemetryPacket
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.MotoareCheck.LBC
import org.firstinspires.ftc.teamcode.MotoareCheck.LFC
import org.firstinspires.ftc.teamcode.MotoareCheck.RBC
import org.firstinspires.ftc.teamcode.MotoareCheck.RFC
import org.firstinspires.ftc.teamcode.Teste.PozSlide
import org.firstinspires.ftc.teamcode.Teste.SBPoz
import org.firstinspires.ftc.teamcode.Teste.SSDPoz
import org.firstinspires.ftc.teamcode.Teste.SSPoz
import org.firstinspires.ftc.teamcode.Teste.SSSPoz


@Config
object MotoareCheck {
    @JvmField
    var LFC = 1
    @JvmField
    var LBC = 1
    @JvmField
    var RFC = 1
    @JvmField
    var RBC = 1
}
@Config
object  Teste{
    @JvmField
    var PozSlide = 0

    @JvmField
    var SBPoz = 0.0

    @JvmField
    var SSSPoz = 0.0

    @JvmField
    var SSDPoz = 0.0

    @JvmField
    var SSPoz = 0.0

}


@TeleOp(name="Lu adi nu-i placea numele vechi 3:c")

class OpJeg : LinearOpMode() {
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        // Declare our motors
        // Make sure your ID's match your configuration

        val LF = hardwareMap.dcMotor["LF"]
        val LB = hardwareMap.dcMotor["LB"]
        val RF = hardwareMap.dcMotor["RF"]
        val RB = hardwareMap.dcMotor["RB"]
        val Slide = hardwareMap.get(DcMotorEx::class.java, "Slide")
        val RidicareIntake = hardwareMap.servo["RidicareIntake"]
        val Intake = hardwareMap.get(DcMotorEx::class.java, "Intake")
        val ticks = 2786.2
        var newTarget: Double
        val ServoBucket = hardwareMap.servo["ServoBucket"]
        val RoataBucket = hardwareMap.crservo["RoataBucket"]

        LF.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        LB.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        RF.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        RB.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        Slide.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        Slide.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        Slide.mode = DcMotor.RunMode.RUN_USING_ENCODER

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        LF.direction = DcMotorSimple.Direction.REVERSE
        LB.direction = DcMotorSimple.Direction.REVERSE
        RidicareIntake.position = 0.60
        var Incetinire = 1.0

        val ServoSlideStanga = hardwareMap.servo["ServoSlideStanga"]
        val ServoSlideDreapta = hardwareMap.servo["ServoSlideDreapta"]


        ServoSlideDreapta.direction = Servo.Direction.REVERSE

        var slidConditionUp : Boolean = false
        var slidConditionDown : Boolean = false

//wow
        //wow2


        waitForStart()
        if (isStopRequested) return
        while (opModeIsActive()) {

            val y = -gamepad1.left_stick_y.toDouble() // Remember, Y stick value is reversed
            val x = gamepad1.left_stick_x * 1.1 // Counteract imperfect strafing
            val rx = gamepad1.right_stick_x.toDouble()

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            val denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1.0)
            val frontLeftPower = (y + x + rx) / denominator
            val backLeftPower = (y - x + rx) / denominator
            val frontRightPower = (y - x - rx) / denominator
            val backRightPower = (y + x - rx) / denominator
            Incetinire = 1.0 - gamepad1.right_trigger * 0.5
            LF.power = frontLeftPower * LFC * Incetinire
            LB.power = backLeftPower  * LBC * Incetinire
            RF.power = frontRightPower * RFC * Incetinire
            RB.power = backRightPower * RBC * Incetinire



            if(gamepad2.left_trigger >= 0.01){
                Intake.power = -0.7
            }
            else{
                if(gamepad2.right_trigger >= 0.01){
                    Intake.power= 0.7
                    RoataBucket.power = -1.0
                }
                else{
                    Intake.power = 0.0
                }
            }
            if(gamepad2.dpad_down){
                RoataBucket.power = 0.7

            }
            else if(gamepad2.dpad_up){
                RoataBucket.power = 0.0
            }

            val tp = TelemetryPacket()
            tp.put("SlideCurPos", Slide.currentPosition)

            FtcDashboard.getInstance().sendTelemetryPacket(tp)

            //slide SUUUUUUUUUUUUUUUUUUUUUUUUUS
            if(gamepad2.right_bumper){
                slidConditionUp = true
                slidConditionDown = false
            }

            if(slidConditionUp && Slide.currentPosition <= 2040){
                Slide.targetPosition = 2040
                Slide.mode = DcMotor.RunMode.RUN_TO_POSITION
                Slide.power = 0.75
            }
            else if(Slide.currentPosition > 2040 && slidConditionDown == false){
                slidConditionUp = false
                Slide.power = 0.0
            }

            //SLIDE JOOOOOOOOOOOOOOOOOOOOOOOS
            if(gamepad2.left_bumper){
                slidConditionDown = true
                slidConditionUp = false
            }

            if(slidConditionDown && Slide.currentPosition >= 0){
                Slide.targetPosition = 0
                Slide.mode = DcMotor.RunMode.RUN_TO_POSITION
                Slide.power = 0.75
            }
            else if(Slide.currentPosition < 1 && slidConditionUp == false){
                slidConditionDown = false
                Slide.power = 0.0
            }
           if(gamepad2.a){
               ServoBucket.position = 0.25
               ServoSlideDreapta.position = 0.15
               ServoSlideStanga.position = 0.52
           }
            if(gamepad2.y){
                ServoBucket.position = 0.28
                ServoSlideDreapta.position = 0.62
                ServoSlideStanga.position = 0.59
            }
            if(gamepad2.b){
                Slide.power = 0.0
                slidConditionDown = false
                slidConditionUp = false
            }
        }
    }
}


//LB e RRB
//LF e RF
//RB e LBw
//RF e LF



