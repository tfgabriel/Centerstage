package org.firstinspires.ftc.teamcode.autonom

import com.acmerobotics.dashboard.FtcDashboard
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.DeclarareMotoare.lom
import org.firstinspires.ftc.teamcode.camerathings.Camera
import org.firstinspires.ftc.teamcode.camerathings.CameraObjects.autored
import org.firstinspires.ftc.teamcode.camerathings.CameraObjects.autoresult
import org.firstinspires.ftc.teamcode.camerathings.CameraObjects.cameraname
import org.firstinspires.ftc.teamcode.camerathings.CameraObjects.folosesccamera
import org.firstinspires.ftc.teamcode.camerathings.CameraObjects.waitforopen
import org.firstinspires.ftc.teamcode.camerathings.pipelinedarscrisdeivi
import org.firstinspires.ftc.teamcode.varsandfuncs.vars.camera
import org.firstinspires.ftc.teamcode.varsandfuncs.vars.dashboard
import org.openftc.easyopencv.OpenCvPipeline
import java.lang.Thread.sleep

object setup {


    fun autonomsetup(lom: LinearOpMode, isred: Boolean): Int {

        autored = isred
        dashboard = FtcDashboard.getInstance()

        if (folosesccamera) {
            var pipeline: OpenCvPipeline
            pipeline = pipelinedarscrisdeivi(640, 480)
            camera = Camera(cameraname, 640, 480, pipeline, streaming = true, waitforopen = true)
        }

        val result = checkCamera()
        lom.waitForStart()

        if (folosesccamera) {
            camera.stop()
        }

        return result
    }

    fun checkCamera(): Int {

        var loadautoresult: Int = 0

        if (folosesccamera) {
            while (!lom.isStarted) {
                loadautoresult = autoresult
                sleep(5)
            }
        }

        return loadautoresult
    }

}