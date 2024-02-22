package org.firstinspires.ftc.teamcode.camerathings


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.core.Point
import org.opencv.core.Scalar
import org.opencv.imgproc.Imgproc
import org.openftc.easyopencv.OpenCvPipeline
import kotlin.math.PI
import kotlin.math.sqrt

//Pipeline gen

//mie imi trebuie sa separ ce vede in camera in patratele
//impart grila de patratele in 2: dreapta si centru
//daca nu e dreapta, verific centru, daca nu e nici centru, inseamna ca e stanga
//verificarea se face prin a lua culoarea patratului, daca e rosu sau albastru dupa caz
//daca sunt n patrate colorate intr-o sectiune a grilei, si mai multe decat in cealalta sectiune, atunci robotul se va duce pe cazul respectiv

class Box2D(xoffset: Int, yoffset: Int, width: Int, height: Int) : Comparable<Box2D> {
    //creez patratele

    var boxwidth: Int = 0
    var boxheight: Int = 0
    var offx: Int = 0
    var offy: Int = 0

    //aici, constructorul de fapt imi permite ca eu sa apelez clasa asta fara sa ii pun valori, asta e intr-un fel un caz default cand apelez clasa
    constructor() : this(0, 0, 0, 0)

    init {
        boxheight = height
        boxwidth = width
        offx = xoffset
        offy = yoffset
    }

    //fac distanta dintre 2 patrate ca sa pot sa le compar pozitiile / sa le aliniez
    fun getdistancebetweenboxes(): Double {
        val xdist = (offx.toDouble() + boxwidth.toDouble()) / 2
        val ydist = (offy.toDouble() + boxheight.toDouble()) / 2

        return sqrt(xdist * xdist + ydist * ydist)
    }

    //compararea patratelor
    override fun compareTo(other: Box2D): Int {
        if (getdistancebetweenboxes() < other.getdistancebetweenboxes()) {
            return -1
        } else {
            return 1
        }
    }
}


object CameraControls {
    lateinit var lom: LinearOpMode

    @JvmField
    var WIDTH: Int = 15

    @JvmField
    var LUP: Int = 9

    @JvmField
    var LUT: Int = 19

    @JvmField
    var XOFF: Int = 0

    @JvmField
    var YOFF: Int = 20






    @JvmField
    var DRAW_BOXES: Boolean = true


    @JvmField
    var CameraMidPos: Double = 400.0

    @JvmField
    var CameraRightPos: Double = 400.0

    @JvmField
    var isprocessed: Boolean = true

    @JvmField
    var autored: Boolean = false

    @JvmField
    var autoresult: Int = 0

    @JvmField
    var autominblocksred: Int = 20

    @JvmField
    var autominblocksblue: Int = 45
}

class Pipeline(width: Int, height: Int) : OpenCvPipeline() {
    //am nevoie de ceva in care sa tin toate patratele si sa pot sa le verific starile (culorile) in mod independent
    //asa ca imi fac un vector (lista) care sa le tina pe toate
    var griladepatrate: Array<Box2D> = arrayOf(Box2D())

    //aici imi adaug patrate in grila
    private fun construiestegrila(
        patratemaxheight: Int,
        patratemaxwidth: Int,
        width: Int,
        xoff: Int,
        yoff: Int,
        h: Int,
        w: Int
    ): Array<Box2D> {

        //lungimea vectorului = cate patrate incap = aria dreptunghiului
        //eu masor lungimea si latimea dreptunghiului de la un patrat din centru, pana la o latura
        //sa zicem ca eu am lungimea de 7 patrate, eu incep de la patratul din mijloc (4), si iau distanta de la el la capat (care e de 4), o inmultesc cu 2, si scad 1, pentru ca am adunat patratul 4 de 2 ori si trebuie sa il scad
        var lungimeagrilei = (patratemaxheight * 2 - 1) * (patratemaxwidth * 2 - 1)

        var grila = Array(lungimeagrilei) { Box2D() }
        var indexpatrat: Int = 0

        for (i in -(patratemaxheight - 1) until patratemaxheight) {
            for (j in -(patratemaxwidth - 1) until patratemaxheight) {
                grila[indexpatrat] = Box2D(
                    i * width + h / 2 - width / 2 + xoff,
                    j * width + w / 2 - width / 2 + yoff,
                    width,
                    width
                )
                ++indexpatrat
            }
        }

        griladepatrate.sort()
        return grila
    }

    private fun draw(frame: Mat, cb: Box2D, culoare: Double) {
        //fac patratul
        val p1 = Point(
            cb.offx.toDouble(),
            cb.offy.toDouble()
        )
        val p3 = Point(
            cb.offx.toDouble() + cb.boxwidth.toDouble(),
            cb.offy.toDouble() + cb.boxheight.toDouble()
        )

        //generalizez culoarea patratului
        val color = Scalar(culoare, culoare, culoare, 200.0)
        //pot sa pun si alta culoare (gen sa fac marginile intre patrate da eu nu vreau for simplicitys sake

        //desenez in frame/grila patratul
        Imgproc.rectangle(frame, p1, p3, color, -1)
    }

    //if check pixel, no more submatrix needed
    private fun subm(img: Mat, box: Box2D): Mat {
        return img.submat(box.offx, box.offx + box.boxheight, box.offx, box.offx + box.boxwidth)
    }

    private fun check(img: Mat, box: Box2D): DoubleArray {
        val subm = subm(img, box)
        return Core.mean(subm).`val`
    }

    private fun isRed(col: DoubleArray): Boolean {

        //valori pentru constrangerile culorii, adica din ce vede camera, selectez care sunt limitele pentru care culoarea vazuta e catalogata drept rosu
        var redangle: Double = PI / 9 //20 de grade
        var minredsaturation: Double = 50.0
        var maxredvalue: Double = 35.0

        //red is defined between 0 - 20 and 340 - 360 (as ang (h)), s > arbitrary value, v < arbitrary value
        val h = (col[0] / 255.0) * PI * 2
        val s = col[1]
        val v = col[2]

        return (h < redangle || (PI * 2 - h) < redangle) && s > minredsaturation && v < maxredvalue

    }

    private fun isBlue(col: DoubleArray): Boolean {

        //valori pentru constrangerile culorii, adica din ce vede camera, selectez care sunt limitele pentru care culoarea vazuta e catalogata drept albastru
        var blueanglestanga: Double = PI / 9 //30 de grade
        var blueangledreapta: Double = PI / 12 //15 grade
        var minbluesaturation: Double = 50.0
        var maxbluevalue: Double = 35.0

        val h = (col[0] / 255.0) * PI * 2
        val s = col[1]
        val v = col[2]

        return (h > 4 * PI / 3 - blueanglestanga && h < 4 * PI / 3 + blueangledreapta) && s > minbluesaturation && v < maxbluevalue


    }

    private fun checkCol(col: DoubleArray): Boolean {
        return if (CameraControls.autored) {
            isRed(col)
        } else {
            isBlue(col)
        }
    }

    private fun getautoresult(autored: Boolean, midblocks: Int, rightblocks: Int): Int{
        if(autored)
            return if(midblocks > CameraControls.autominblocksred){
                1
            }else if (rightblocks > CameraControls.autominblocksred){
                0
            }else{
                2
            }
        else{
            return if(midblocks > CameraControls.autominblocksblue){
                1
            }else if (rightblocks > CameraControls.autominblocksblue){
                2
            }else{
                0
            }
        }
    }

    override fun processFrame(input: Mat): Mat {

        if (input.empty()) {
            return input
        }
        if (CameraControls.isprocessed) {

            //init frame
            val frame = Mat()
            input.copyTo(frame)

            //change colors from rgb to hsv
            if (!CameraControls.autored) {
                Imgproc.cvtColor(frame, frame, Imgproc.COLOR_RGB2HSV)
            }


            val finalframe = Mat()
            if (CameraControls.DRAW_BOXES) {
                frame.copyTo(finalframe)
            }


            var midBlocks = 0
            var rightBlocks = 0

            //to thimk
            //frame[y,x]
            for ((ci, element) in construiestegrila(
                CameraControls.LUP,
                CameraControls.LUT,
                CameraControls.WIDTH,
                CameraControls.XOFF,
                CameraControls.YOFF, height, width).withIndex()) {
                val vl = check(frame, element)
                val cx = element.offx + element.boxwidth / 2
                val cy = element.offx + element.boxwidth / 2

                if (checkCol(vl)) {
                    if (cy < CameraControls.CameraMidPos) {
                        ++midBlocks
                    } else if (cy > CameraControls.CameraRightPos) {
                        ++rightBlocks
                    }


                }
            }

            CameraControls.lom.telemetry.addData("MidBoxes", midBlocks)
            CameraControls.lom.telemetry.addData("RightBoxes", rightBlocks)

            CameraControls.autoresult = getautoresult(CameraControls.autored, midBlocks, rightBlocks)

            CameraControls.lom.telemetry.addData("GOT RESULT", CameraControls.autoresult)
            CameraControls.lom.telemetry.update()


            Imgproc.line(
                finalframe,
                Point(CameraControls.CameraMidPos, 80.0),
                Point(CameraControls.CameraMidPos, 380.0),
                Scalar(0.0, 0.0, 0.0, 255.0),
                9
            )

            return if ((CameraControls.DRAW_BOXES) && !finalframe.empty()) {
                finalframe
            } else {
                frame
            }
        } else {
            return input
        }

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