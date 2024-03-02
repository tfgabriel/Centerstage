package org.firstinspires.ftc.teamcode.camerathings

import com.acmerobotics.dashboard.config.Config
import org.openftc.easyopencv.OpenCvCameraRotation
import kotlin.math.PI

@Config
object CameraObjects{

    @JvmField
    var desenezpatrate: Boolean = true

    @JvmField
    var patratepelungime: Int = 300

    @JvmField
    var patratepelatime: Int = 100

    //offseturi de centru
    @JvmField
    var offx: Int = 300

    @JvmField
    var offy: Int = 300

    @JvmField
    var step: Int = 35




    @JvmField
    var cazuldemijloc: Double = 0.0

    @JvmField
    var patrateminrosii: Int = 0

    @JvmField
    var patrateminalbastre: Int = 0

    @JvmField
    var autored: Boolean = true

    @JvmField
    var autoresult: Int = 1

    @JvmField
    var vreauframe: Boolean = true

    @JvmField
    var folosesccamera: Boolean = true

    @JvmField
    var cameraname: String = "Kamera"

    @JvmField
    var waitforopen: Boolean = true

    @JvmField
    var cameraRotation = OpenCvCameraRotation.UPRIGHT

    @JvmField
    var rosuAng: Double = 4.3
    @JvmField
    var rosuMaxDif: Double = 0.2

    @JvmField
    var diferentadeunghirosu: Double = PI / 9
    @JvmField
    var saturatierosieminima: Double = 50.0
    @JvmField
    var valoarerosieminima: Double = 35.0

    @JvmField
    var alabastruAng : Double  = 4.3
    @JvmField
    var alabastruMaxDiff: Double = 0.2

    @JvmField
    var saturatiealbastraminima: Double = 50.0
    @JvmField
    var valoarealbastramaxima: Double = 35.0
}
