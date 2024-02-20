package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

@Autonomous
class AutoRosuRight : LinearOpMode() {
    override fun runOpMode() {
        DeclarareMotoare.initMotoare(this)







        waitForStart()

        FunctiiMiscare.MiscareVerticala(1.0)

        sleep(400)

        FunctiiMiscare.MiscareDeRotire(1.0)

        sleep(200)


    }


}