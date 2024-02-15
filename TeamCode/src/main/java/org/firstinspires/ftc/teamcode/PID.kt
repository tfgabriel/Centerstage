package org.firstinspires.ftc.teamcode

class PIDFCoef(
    @JvmField var p: Double,
    @JvmField var i: Double,
    @JvmField var d: Double,
    @JvmField var f: Double
)

class PID(var c: PIDFCoef) {
    fun update(err: Double) = err * c.p * c.f * semn(err)

    fun semn(test: Double): Double {
        return if (test < 0) {
            -1.0
        } else if (test > 0) {
            1.0
        } else {
            0.0
        }
    }
}