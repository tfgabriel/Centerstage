package org.firstinspires.ftc.teamcode.autonom

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.DeclarareMotoare
import org.firstinspires.ftc.teamcode.FunctiiMiscare
import org.firstinspires.ftc.teamcode.camerathings.CameraObjects.autoresult

@Autonomous
class AutoRosuScurt : LinearOpMode() {
    override fun runOpMode() {
        DeclarareMotoare.initMotoare(this, ElapsedTime())


        autonomsetup(this, isred = false)

        FunctiiMiscare.MiscareVerticala(1.0)
        sleep(200)
        FunctiiMiscare.move(200.0, autoresult)

        FunctiiMiscare.MiscareDeRotire(1.0)
        sleep(200)




    }


}