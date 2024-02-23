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

    //iti dau eu sa citesti si sa iti explic cate ceva despre documentatia de pipeline, opencv si frameuri
    //functia in care lucrez si verific lucrurile din frame (de acum, mat = frameul cu care lucrez)
    override fun processFrame(input: Mat): Mat {

        if(input.empty()){
            return input
        }
        //vreauframe pur si simplu ma ajuta sa controlez daca fac grila si patratele din dash
        if(vreauframe){

            //imi mut frameul din rgb in hsv pentru a prelucra culorile
            val frametohsv = Mat()
            input.copyTo(frametohsv)
            Imgproc.cvtColor(frametohsv, frametohsv, Imgproc.COLOR_RGB2HSV)

            //la fel ca la frame, aici controlez din dash daca desenez patratele
            val finalframe = Mat()
            if(desenezpatrate){

                frametohsv.copyTo(finalframe)
            }

            //aici retin cate patrate de culoarea pe care o vreau sunt in fiecare parte
            var patrateincentru: Int = 0
            var patrateladreapta: Int = 0

            //aici imi pun patratele in grila, ca sa intelegi, o sa iti arat o foaie [CAND VII LA ROBO]
            for(patratpelungime in -patratepelungime+offx .. patratepelungime+offx step step ){
                for(patratpelatime in -patratepelatime+offy .. patratepelatime+offy step step){

                    //patratul efectiv
                    val patrat = frametohsv[patratpelungime, patratpelatime] ?: continue

                    //verific culoarea
                    if(operatiicupatrate.verificaculoarea(patrat)){
                        //verific unde sunt patratele in functie de o linie in care impart frameul
                        if(patratpelungime > cazuldemijloc)
                            patrateladreapta++
                        else
                            patrateincentru++

                        //daca desenez patrate, le pun culorile respective (daca am patrat de culoare dorita, il pun pe alb
                        if(desenezpatrate)
                            Imgproc.rectangle(finalframe, Rect(patratpelungime, patratpelatime, step, step), Scalar(
                                max(patrat[0]-10.0, 0.0),
                                max(patrat[0]-10.0, 0.0),
                                max(patrat[0]-10.0, 0.0),
                            ), -1
                            )

                    }else{
                        //daca nu e ce culoare vreau dar totusi desenez patrate, desenez patratul de culoare neagra
                        if(desenezpatrate){
                            Imgproc.rectangle(finalframe, Rect(patratpelungime, patratpelatime, step, step), Scalar(255.0, 255.0, 255.0))
                        }

                    }

                    lom.telemetry.addData("Patrate in Mijloc", patrateincentru)
                    lom.telemetry.addData("Patrate in centru", patrateladreapta)
                    lom.telemetry.update()

                    //autoresultul, iti explic de ce sunt cum sunt cazurile [CAND VII LA ROBO]
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

                    //linia care imi imparte cazurile (dreapta centru stanga)
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

        //transform culorile primite din rgb in hsv o sa iti arat cum functioneaza [CAND VII LA ROBO]
        val diferentadeunghirosu: Double = PI/9
        val saturatierosieminima: Double = 50.0
        val valoarerosiemaxima: Double = 35.0

        val h = (patrat[0]/255.0) * PI * 2
        val s = patrat[1]
        val v = patrat[2]

        return (h <= 2*PI - diferentadeunghirosu || h <= diferentadeunghirosu) && s > saturatierosieminima && v < valoarerosiemaxima
    }

    fun isblue(patrat: DoubleArray): Boolean{

        //transform culorile primite din rgb in hsv o sa iti arat cum functioneaza [CAND VII LA ROBO]
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

//Can we pretend that
//Airplanes in the night sky are like
//Shooting stars? I could really use a
//Wish right now, wish right now
//Wish right now, can we pretend that
//Airplanes in the night sky are like
//Shooting stars? I could really use a
//Wish right now, wish right now
//Wish right now (Yeah)

//I could use a dream or a genie or a wish
//To go back to a place much simpler than this
//'Cause after all the partyin' and smashin' and crashin'
//And all the glitz and the glam and the fashion
//And all the pandemonium and all the madness
//There comes a time where you fade to the blackness
//And when you starin' at that phone in your lap
//And you hopin', but them people never call you back
//But that's just how the story unfolds
//You get another hand soon after you fold
//And when your plans unravellin', they're sayin'
//"What would you wish for if you had one chance?"
//So airplane, airplane, sorry, I'm late
//I'm on my way, so don't close that gate
//If I don't make that, then I switch my flight
//And I'll be right back at it by the end of the night

//Can we pretend that
//Airplanes in the night sky are like
//Shooting stars? (Shooting stars) I could really use a
//Wish right now, wish right now
//Wish right now, can we pretend that
//Airplanes in the night sky are like
//Shooting stars? (Shooting stars) I could really use a
//Wish right now, wish right now
//Wish right now (Yeah, yeah)

//Somebody take me back to the days
//Before this was a job, before I got paid
//Before it ever mattered what I had in my bank
//Yeah, back when I was tryna get a tip at Subway
//And back when I was rappin' for the hell of it
//But, nowadays, we rappin' to stay relevant
//I'm guessin' that if we can make some wishes outta airplanes
//Then maybe, oh, maybe I'll go back to the days (Days)
//Before the politics that we call the rap game
//And back when ain't nobody listen to my mixtape
//And back before I tried to cover up my slang
//But this is for Decatur—what's up, Bobby Ray?
//So, can I get a wish to end the politics? (Oh)
//And get back to the music that started this shit?
//So here I stand, and then again, I say
//I'm hopin' we can make some wishes out of airplanes

//Can we pretend that
//Airplanes in the night sky are like
//Shooting stars? (Shooting stars) I could really use a
//Wish right now, wish right now
//Wish right now, can we pretend that
//Airplanes in the night sky are like
//Shooting stars? (Shooting stars) I could really use a
//Wish right now, wish right now
//Wish right now

//I could really use a wish right now
//(Oh, oh, oh, oh)
//I, I, I could really use a wish right now
//(Mm) Like, like
//Like shooting stars (Ah)
//I, I could
//I could, I could really use a wish right now
//A wish, a wish right now (A wish right now)
