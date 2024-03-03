package org.firstinspires.ftc.teamcode.autonom

import org.firstinspires.ftc.teamcode.autonom.autovars.backboardtoparkTime
import org.firstinspires.ftc.teamcode.autonom.autovars.bluetrastobackAngle
import org.firstinspires.ftc.teamcode.autonom.autovars.finishtrasTime
import org.firstinspires.ftc.teamcode.autonom.autovars.frontcaseTime
import org.firstinspires.ftc.teamcode.autonom.autovars.fronttotrasAngle
import org.firstinspires.ftc.teamcode.autonom.autovars.fronttotrasTime
import org.firstinspires.ftc.teamcode.autonom.autovars.intakedropTime
import org.firstinspires.ftc.teamcode.autonom.autovars.nottrascaseTime
import org.firstinspires.ftc.teamcode.autonom.autovars.nottrastotrasTime
import org.firstinspires.ftc.teamcode.autonom.autovars.outtakedropTime
import org.firstinspires.ftc.teamcode.autonom.autovars.parkfwdTime
import org.firstinspires.ftc.teamcode.autonom.autovars.redtrastobackAngle
import org.firstinspires.ftc.teamcode.autonom.autovars.rotationTime
import org.firstinspires.ftc.teamcode.autonom.autovars.slidemaxPos
import org.firstinspires.ftc.teamcode.autonom.autovars.slideminPos
import org.firstinspires.ftc.teamcode.autonom.autovars.starttonottrasAngle
import org.firstinspires.ftc.teamcode.autonom.autovars.starttotrasAngle
import org.firstinspires.ftc.teamcode.autonom.autovars.trascaseTime
import org.firstinspires.ftc.teamcode.autonom.autovars.trasmovefwdTime
import org.firstinspires.ftc.teamcode.autonom.autovars.trastobackboardTime
import org.firstinspires.ftc.teamcode.hardware.drivetrain
import org.firstinspires.ftc.teamcode.hardware.intake
import org.firstinspires.ftc.teamcode.hardware.outtake
import org.firstinspires.ftc.teamcode.hardware.slides
import kotlin.math.PI

class trajectories {

    var drivetrain: drivetrain = drivetrain()
    var intake: intake = intake()
    var outtake: outtake = outtake()
    var slides: slides = slides()
    fun trajRedShort0(){
        automove(0, isRed = true)
        drivetrain.rotateleft(rotationTime)
        drivetrain.rotateleft(rotationTime)
        drivetrain.movefwd(trasmovefwdTime)
        putpixelonback()
        buildparking(isRed = true)
    }

    fun trajRedShort1(){
        automove(1, isRed = true)
        drivetrain.movefwd(trasmovefwdTime)
        putpixelonback()
        buildparking(isRed = true)
    }

    fun trajRedShort2(){
        automove(2, isRed = true)
        drivetrain.movefwd(trasmovefwdTime)
        putpixelonback()
        buildparking(isRed = true)
    }

    fun trajRedLong0(){
        automove(0, isRed = true)
        drivetrain.rotateleft(rotationTime)
        drivetrain.rotateleft(rotationTime)
        drivetrain.strafeleft(finishtrasTime)
        drivetrain.diagmove(redtrastobackAngle, trastobackboardTime)
        putpixelonback()
        buildparking(isRed = true)
    }

    fun trajRedLong1(){
        automove(1, isRed = true)
        drivetrain.rotateright(rotationTime)
        drivetrain.diagmove(fronttotrasAngle, fronttotrasTime)
        drivetrain.diagmove(redtrastobackAngle, trastobackboardTime)
        putpixelonback()
        buildparking(isRed = true)
    }

    fun trajRedLong2(){
        automove(2, isRed = true)
        drivetrain.diagmove(starttonottrasAngle, nottrastotrasTime)
        drivetrain.diagmove(redtrastobackAngle, trastobackboardTime)
        putpixelonback()
        buildparking(isRed = true)
    }

    fun trajBlueShort0(){
        automove(0, isRed = false)
        drivetrain.rotateleft(rotationTime)
        drivetrain.rotateleft(rotationTime)
        drivetrain.movefwd(trasmovefwdTime)
        putpixelonback()
        buildparking(isRed = false)
    }

    fun trajBlueShort1(){
        drivetrain.movefwd(trasmovefwdTime)
        putpixelonback()
        buildparking(isRed = false)
    }

    fun trajBlueShort2(){
        drivetrain.movefwd(trasmovefwdTime)
        putpixelonback()
        buildparking(isRed = false)
    }

    fun trajBlueLong(){
        drivetrain.diagmove(bluetrastobackAngle, trastobackboardTime)
        putpixelonback()
        buildparking(isRed = false)
    }
    private fun buildparking(isRed: Boolean){
        if(isRed){
                drivetrain.strafeleft(backboardtoparkTime)
                drivetrain.movefwd(parkfwdTime)

        }else{
            drivetrain.strafeleft(backboardtoparkTime)
            drivetrain.movefwd(parkfwdTime)
        }
    }
    private fun putpixelonback(){
        slides.extend(slidemaxPos)
        outtake.rotatefwd()
        outtake.drop(outtakedropTime)
        outtake.resetposition()
        slides.retract(slideminPos)
    }

    fun automove(autoresult: Int, isRed: Boolean){
        if(!isRed){
            starttotrasAngle = 7 * PI / 12
            starttonottrasAngle = 5 * PI/ 12
        }

        if(autoresult == 0){
            //ma duc la tras
            drivetrain.diagmove(starttotrasAngle, trascaseTime)
            drivetrain.rotateright(rotationTime)
            intake.droppixel(intakedropTime)


        } else if(autoresult == 1){
            //ma duc in fata
            drivetrain.movefwd(frontcaseTime)
            intake.droppixel((intakedropTime))
        } else {
            //ma duc in alta parte
            drivetrain.diagmove(starttonottrasAngle, nottrascaseTime)
            drivetrain.rotateleft(rotationTime)
            intake.droppixel(intakedropTime)
        }
    }
}