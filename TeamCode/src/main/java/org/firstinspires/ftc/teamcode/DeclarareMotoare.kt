package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.CRServo
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.utils.RobotFuncs.hardwareMap

object DeclarareMotoare{

    @JvmStatic
    lateinit var LF : DcMotor
    @JvmStatic
    lateinit var LB : DcMotor
    @JvmStatic
    lateinit var RF : DcMotor
    @JvmStatic
    lateinit var  RB : DcMotor
    @JvmStatic
    lateinit var Slide : DcMotor
    @JvmStatic
    lateinit var RidicareIntake : Servo
    @JvmStatic
    lateinit var  Intake : DcMotor
    @JvmStatic
    lateinit var ServoBucket : Servo
    @JvmStatic
    lateinit var RoataBucket : CRServo
    @JvmStatic
    lateinit var  ServoSlideStanga : Servo
    @JvmStatic
    lateinit var  ServoSlideDreapta : Servo





    @JvmStatic
    fun initMotoare() {


        LF = hardwareMap.dcMotor["LF"]
        LB = hardwareMap.dcMotor["LB"]
        RF = hardwareMap.dcMotor["RF"]
        RB = hardwareMap.dcMotor["RB"]


        Slide = hardwareMap.get(DcMotorEx::class.java, "Slide")
        RidicareIntake = hardwareMap.servo["RidicareIntake"]
        Intake = hardwareMap.get(DcMotorEx::class.java, "Intake")

        ServoBucket = hardwareMap.servo["ServoBucket"]
        RoataBucket = hardwareMap.crservo["RoataBucket"]



        LF.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        LB.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        RF.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        RB.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE


        Slide.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        Slide.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        Slide.mode = DcMotor.RunMode.RUN_USING_ENCODER

        LF.direction = DcMotorSimple.Direction.REVERSE
        LB.direction = DcMotorSimple.Direction.REVERSE

        RidicareIntake.position = 0.33

        ServoSlideStanga = hardwareMap.servo["ServoSlideStanga"]
        ServoSlideDreapta = hardwareMap.servo["ServoSlideDreapta"]


        ServoSlideDreapta.direction = Servo.Direction.REVERSE

        ServoBucket.position = 0.25
        ServoSlideDreapta.position = 0.15
        ServoSlideStanga.position = 0.52



    }
}