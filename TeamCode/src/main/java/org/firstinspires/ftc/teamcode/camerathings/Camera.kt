package org.firstinspires.ftc.teamcode.camerathings

import com.acmerobotics.dashboard.FtcDashboard
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName
import org.firstinspires.ftc.teamcode.varsandfuncs.vars.hardwareMap
import org.openftc.easyopencv.OpenCvCameraFactory
import org.openftc.easyopencv.OpenCvPipeline
import org.openftc.easyopencv.OpenCvWebcam
import org.firstinspires.ftc.teamcode.varsandfuncs.vars.lom
import org.openftc.easyopencv.OpenCvCamera
import java.lang.Thread.sleep

//imi creez o camera
//am nevoie sa imi declar rezolutia ei, numele ei, pipelineul pe care il va urmari, si sa pot sa controlez din dash daca ii dau stream
//mai am nevoie si de waitforopen pentru ca pana sa o ia robotu de nebun tre sa astepte camera sa calculeze autoresultu din pipeline
class Camera(name: String, resolutionx: Int, resolutiony: Int, pipeline: OpenCvPipeline, streaming: Boolean, waitforopen: Boolean) {

    //cateva variabile stupide, ai sa vezi de ce le folosesc
    var camera: OpenCvWebcam

    var errorcode: Int = 0
    var opened: Boolean = false

    //ca sa controlez streamu pe dash
    private var dashboardStreaming = false

    init{

        //construiesc camera, nimic mult de inteles, efectiv copy-paste de cum se defineste o camera luat de pe net
        val cameraMonitorViewId: Int = hardwareMap.appContext.resources.getIdentifier("cameraMonitorViewId", "id", lom.hardwareMap.appContext.packageName)
        val webcamName: WebcamName = hardwareMap.get(WebcamName::class.java, name)
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId)


        camera.setPipeline(pipeline)

        dashboardStreaming = streaming

        //construiesc un obiect ce ma ajuta sa determin starea camerei (deschisa, inchisa, sau pur si simplu n-a vrut sa se deschida)
        //ideea cu obiectul asta e ca nu imi trebuie neaparat si "onerror" numai ca el e un obiect definit dintr-o librarie externa, si ma obliga sa ii descriu toate functiile
        val cameraListener = object : OpenCvCamera.AsyncCameraOpenListener{


            override fun onOpened() {
                //imi setez la ce dau stream pe dash
                camera.startStreaming(resolutionx, resolutiony)
                if (streaming) {
                    //dau stream pe dash
                    FtcDashboard.getInstance().startCameraStream(camera, 30.0)
                }
                opened = true
            }

            //imi scot eroarea in cazu in care n-a vrut sa se deschida
            override fun onError(errorCode: Int) {
                errorcode = errorCode
            }
        }

        //aici asincronizez camera
        camera.openCameraDeviceAsync(cameraListener)
        synccamwithlom(waitforopen, lom)
    }

    //inchid streamul de la camera
    fun stop() {
        if (opened) {
            camera.closeCameraDeviceAsync { }
            if (dashboardStreaming) {
                FtcDashboard.getInstance().stopCameraStream()
            }
        }
    }

    //functia de asincronizare
    private fun synccamwithlom(waitforopen: Boolean, lom: LinearOpMode){
        //astept cate 5 milisecunde pana cand se calculeaza pipelineul
        while(waitforopen && !opened && !lom.isStarted && !lom.isStopRequested){
            lom.telemetry.addLine("Waiting on cam open")
            lom.telemetry.update()
            sleep(5)
        }
        lom.telemetry.addLine("Cam opened")
        lom.telemetry.update()
    }
}