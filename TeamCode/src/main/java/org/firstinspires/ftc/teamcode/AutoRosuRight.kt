package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.Servo

@Autonomous
class AutoRosuRight :LinearOpMode(){

    override fun runOpMode() {


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




        val slidePID = PID(Teste.pSlide)

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        LF.direction = DcMotorSimple.Direction.REVERSE
        LB.direction = DcMotorSimple.Direction.REVERSE
        RidicareIntake.position = 0.33
        var Incetinire = 1.0

        val ServoSlideStanga = hardwareMap.servo["ServoSlideStanga"]
        val ServoSlideDreapta = hardwareMap.servo["ServoSlideDreapta"]


        ServoSlideDreapta.direction = Servo.Direction.REVERSE

        var slidConditionUp : Boolean = false
        var slidConditionDown : Boolean = false

        ServoBucket.position = 0.25
        ServoSlideDreapta.position = 0.15
        ServoSlideStanga.position = 0.52



        waitForStart()







    }

    class


}