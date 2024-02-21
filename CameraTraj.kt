package org.firstinspires.ftc.teamcode

import org.opencv.core.Mat
import org.openftc.easyopencv.OpenCvPipeline
import kotlin.math.sqrt

//Pipeline gen

//mie imi trebuie sa separ ce vede in camera in patratele
//impart grila de patratele in 2: dreapta si centru
//daca nu e dreapta, verific centru, daca nu e nici centru, inseamna ca e stanga
//verificarea se face prin a lua culoarea patratului, daca e rosu sau albastru dupa caz
//daca sunt n patrate colorate intr-o sectiune a grilei, si mai multe decat in cealalta sectiune, atunci robotul se va duce pe cazul respectiv

class Box2D(xoffset: Int, yoffset: Int, width: Int, height: Int): Comparable<Box2D>{
    //creez patratele

    var boxwidth: Int = 0
    var boxheight: Int = 0
    var offx: Int = 0
    var offy: Int = 0

    //aici, constructorul de fapt imi permite ca eu sa apelez clasa asta fara sa ii pun valori, asta e intr-un fel un caz default cand apelez clasa
    constructor() : this(0, 0, 0, 0)

    init{
        boxheight = height
        boxwidth = width
        offx = xoffset
        offy = yoffset
    }

    //fac distanta dintre 2 patrate ca sa pot sa le compar pozitiile / sa le aliniez
    fun getdistancebetweenboxes(): Double{
        val xdist = (offx.toDouble() + boxwidth.toDouble()) / 2
        val ydist = (offy.toDouble() + boxheight.toDouble()) / 2

        return sqrt(xdist*xdist + ydist*ydist)
    }

    //compararea patratelor
    override fun compareTo(other: Box2D): Int {
        if(getdistancebetweenboxes() < other.getdistancebetweenboxes()){
            return -1
        }
        else{
            return 1
        }
    }
}


object CameraControls{
    //setez dimensiunile dreptunghiului in care am sa impart pixelii
    @JvmStatic
    var cameraheight: Int = 0

    @JvmStatic
    var camerawidth: Int = 0

    //setez in cate patrate impart dreptunghiul
    @JvmStatic
    var boxheight: Int = 0

    @JvmStatic
    var boxwidth: Int = 0
}

class Pipeline(width: Int, height: Int): OpenCvPipeline() {
    //am nevoie de ceva in care sa tin toate patratele si sa pot sa le verific starile (culorile) in mod independent
    //asa ca imi fac un vector (lista) care sa le tina pe toate
    var griladepatrate: Array<Box2D> = arrayOf(Box2D())

    //aici imi adaug patrate in grila
    private fun construiestegrila(patratemaxheight: Int, patratemaxwidth: Int, width: Int, xoff: Int, yoff: Int, h: Int, w: Int): Array<Box2D>{

        //lungimea vectorului = cate patrate incap = aria dreptunghiului
        //eu masor lungimea si latimea dreptunghiului de la un patrat din centru, pana la o latura
        //sa zicem ca eu am lungimea de 7 patrate, eu incep de la patratul din mijloc (4), si iau distanta de la el la capat (care e de 4), o inmultesc cu 2, si scad 1, pentru ca am adunat patratul 4 de 2 ori si trebuie sa il scad
        var lungimeagrilei = (patratemaxheight * 2 - 1) * (patratemaxwidth * 2 - 1)

        var grila = Array(lungimeagrilei) {Box2D()}
        var indexpatrat: Int = 0

        for(i in -(patratemaxheight - 1) until patratemaxheight){
            for(j in -(patratemaxwidth - 1) until patratemaxheight){
                grila[indexpatrat] = Box2D(i * width + h / 2 - width / 2 + xoff, j * width + w / 2 - width / 2 + yoff, width, width)
                ++indexpatrat
            }
        }

        griladepatrate.sort()
        return grila
    }

    fun draw(frame: Mat, )
    override fun processFrame(input: Mat?): Mat {
        TODO("Not yet implemented")
    }

}

//NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN
//NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN  NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN
//NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN  NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN
//NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN
//NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN  NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN
//NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN  NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN
//NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN
//NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN  NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN
//NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN  NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN NU MAI SUPORT KOTLIN

//CINE PULA MEA SCRIE SINTAXA ASA ???????????????
//DE CE PIZDA MASII AI Int IN LOC DE int ESTI ANIMAL???? BOU???? MAIMUTA INVOLUATA MINTAL??
//WHO THE FUCK SAYS FUN FOR FUNCTIONS???? WHAT PART OF THIS SHIT IS FUN????
//cine a dezvoltat kotlin i will find you and i will fuck your mother in front of you