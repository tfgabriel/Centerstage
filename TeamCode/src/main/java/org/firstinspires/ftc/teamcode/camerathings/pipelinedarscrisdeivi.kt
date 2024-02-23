package org.firstinspires.ftc.teamcode.camerathings

import org.firstinspires.ftc.teamcode.camerathings.CameraObjects.autored
import org.firstinspires.ftc.teamcode.camerathings.CameraObjects.autoresult
import org.firstinspires.ftc.teamcode.camerathings.CameraObjects.cazuldemijloc
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc
import org.openftc.easyopencv.OpenCvPipeline
import org.firstinspires.ftc.teamcode.camerathings.CameraObjects.desenezpatrate
import org.firstinspires.ftc.teamcode.camerathings.CameraObjects.offx
import org.firstinspires.ftc.teamcode.camerathings.CameraObjects.offy
import org.firstinspires.ftc.teamcode.camerathings.CameraObjects.patrateminalbastre
import org.firstinspires.ftc.teamcode.camerathings.CameraObjects.patrateminrosii
import org.firstinspires.ftc.teamcode.camerathings.CameraObjects.patratepelatime
import org.firstinspires.ftc.teamcode.camerathings.CameraObjects.patratepelungime
import org.firstinspires.ftc.teamcode.camerathings.CameraObjects.step
import org.firstinspires.ftc.teamcode.camerathings.CameraObjects.vreauframe
import org.firstinspires.ftc.teamcode.varsandfuncs.mathfuncs.max
import org.opencv.core.Rect
import org.opencv.core.Scalar
import org.firstinspires.ftc.teamcode.varsandfuncs.vars.lom
import org.opencv.core.Point
import kotlin.math.PI



//vreau sa imi impart o parte din ceea ce vede camera in o grila cu patrate
//ce vei vedea pe dash:
//patratele in sine vor avea 2 culori - neagra daca ceea ce se afla in patrat nu e rosu sau albastru
//sau alb daca sunt rosii sau albe (bine, poti sa le customizezi cum vrei sunt patratele tale)
//in afara de ele, va mai exista o linie verticala care iti separa streamul de la camera ca sa vezi accurately in ce caz pica fiecare patrat

//pentru toate chestiile astea, trebuie sa definesc un patrat, o grila in care sa imi tin patratele
//o functie care sa imi deseneze patratele si sa mi le arate pe stream
//o functie care sa vada ce culori se afla in cadrul fiecarui patrat si sa compare cu albastru si rosu
//o functie care se uita in grila de patrate si numara cate patrate sunt in fiecare dintre sectiunile facute de linia de mai sus
//apoi la sfarsit compara numarul de patrate din fiecare sectiune si iti da cazul
class pipelinedarscrisdeivi(resolutionx: Int, resolutiony: Int): OpenCvPipeline(){

    override fun processFrame(input: Mat): Mat {
        if(input.empty()){
            return input
        }
        if(vreauframe){
            val frametohsv = Mat()
            input.copyTo(frametohsv)
            Imgproc.cvtColor(frametohsv, frametohsv, Imgproc.COLOR_RGB2HSV)

            val finalframe = Mat()
            if(desenezpatrate){

                frametohsv.copyTo(finalframe)
            }

            var patrateincentru: Int = 0
            var patrateladreapta: Int = 0

            for(patratpelungime in -patratepelungime+offx .. patratepelungime+offx step step ){
                for(patratpelatime in -patratepelatime+offy .. patratepelatime+offy step step){

                    val patrat = frametohsv[patratpelungime, patratpelatime] ?: continue

                    if(operatiicupatrate.verificaculoarea(patrat)){
                        if(patratpelungime > cazuldemijloc)
                            patrateladreapta++
                        else
                            patrateincentru++

                        if(desenezpatrate)
                            Imgproc.rectangle(finalframe, Rect(patratpelungime, patratpelatime, step, step), Scalar(
                                max(patrat[0]-10.0, 0.0),
                                max(patrat[0]-10.0, 0.0),
                                max(patrat[0]-10.0, 0.0),
                            ), -1
                            )

                    }else{
                        if(desenezpatrate){
                            Imgproc.rectangle(finalframe, Rect(patratpelungime, patratpelatime, step, step), Scalar(255.0, 255.0, 255.0))
                        }

                    }

                    lom.telemetry.addData("Patrate in Mijloc", patrateincentru)
                    lom.telemetry.addData("Patrate in centru", patrateladreapta)
                    lom.telemetry.update()

                    autoresult = if(autored){
                        if(patrateladreapta > patrateminrosii)
                            0
                        else if (patrateincentru > patrateminrosii)
                            1
                        else
                            2
                    }else{
                        if(patrateladreapta > patrateminalbastre)
                            2
                        else if (patrateincentru > patrateminalbastre)
                            1
                        else
                            0
                    }

                    lom.telemetry.addData("GOT RESULTS: ", autoresult)
                    lom.telemetry.update()

                    val w = frametohsv.width()
                    Imgproc.line(finalframe, Point(cazuldemijloc, 80.0), Point(cazuldemijloc, 320.0), Scalar(182.0, 23.0, 240.0), 4)
                }
            }

            return if (desenezpatrate && !finalframe.empty()) {
                finalframe
            } else {
                frametohsv
            }
        }
        else{
            return input
        }


    }
}

object operatiicupatrate{
    fun verificaculoarea(patrat: DoubleArray): Boolean{
        return if(autored){
            isred(patrat)
        } else{
            isblue(patrat)
        }
    }

    fun isred(patrat: DoubleArray): Boolean{

        val diferentadeunghirosu: Double = PI/9
        val saturatierosieminima: Double = 50.0
        val valoarerosiemaxima: Double = 35.0

        val h = (patrat[0]/255.0) * PI * 2
        val s = patrat[1]
        val v = patrat[2]

        return (h <= 2*PI - diferentadeunghirosu || h <= diferentadeunghirosu) && s > saturatierosieminima && v < valoarerosiemaxima
    }

    fun isblue(patrat: DoubleArray): Boolean{
        val diferentadeunghialbastruinstanga: Double = PI/6
        val diferentadeunghialbastruindreapta: Double = PI/12
        val saturatiealbastraminima: Double = 50.0
        val valoarealbastramaxima: Double = 35.0

        val h = (patrat[0]/255.0) * PI * 2
        val s = patrat[1]
        val v = patrat[2]

        return (h >= 4*PI/3 - diferentadeunghialbastruinstanga || h <= 4*PI/3 + diferentadeunghialbastruindreapta) && s > saturatiealbastraminima && v < valoarealbastramaxima
    }
}

//imi definesc aici niste variabile cu care o sa lucrez prin pipeline
object CameraObjects{

    @JvmField
    var desenezpatrate: Boolean = true

    @JvmField
    var patratepelungime: Int = 0

    @JvmField
    var patratepelatime: Int = 0

    @JvmField
    var offx: Int = 0

    @JvmField
    var offy: Int = 0

    @JvmField
    var step: Int = 0

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
}