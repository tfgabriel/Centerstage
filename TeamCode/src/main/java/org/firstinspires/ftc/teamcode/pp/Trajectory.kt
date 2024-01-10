package org.firstinspires.ftc.teamcode.pp

import com.acmerobotics.dashboard.config.Config
import org.firstinspires.ftc.teamcode.pp.DriveConstants.MAX_ACC
import org.firstinspires.ftc.teamcode.pp.DriveConstants.MAX_DEC
import org.firstinspires.ftc.teamcode.pp.DriveConstants.MAX_FRACTION
import org.firstinspires.ftc.teamcode.pp.DriveConstants.MAX_VEL
import org.firstinspires.ftc.teamcode.utils.Pose
import org.firstinspires.ftc.teamcode.utils.Util.angDiff
import org.firstinspires.ftc.teamcode.utils.Vec2d
import kotlin.math.min

class CubicBezierCurve(
        private val c0: Double,
        private val c1: Double,
        private val c2: Double,
        private val c3: Double) {

    operator fun get(t: Double) = t * (t * (t * (-c0 + 3 * c1 - 3 * c2 + c3) + 3 * c0 - 6 * c1 + 3 * c2) - 3 * c0 + 3 * c1) + c0
    // Desmos friendly version
    // x * (x * (x * (-a + 3 * b - 3 * c + d) + 3 * a - 6 * b + 3 * c) - 3 * a + 3 * b) + a

    fun deriv(t: Double) = t * (t * (-3 * c0 - 9 * c2 + 3 * c3) + 6 * c0 + 6 * c2) - 3 * c0 + c1 * (t * (9 * t - 12) + 3)

    fun dderiv(t: Double) = -6 * (c0 * (t - 1) - c1 * (3 * t - 2) + c2 * (3 * t - 1) - c3 * t)

    fun ddderiv(t: Double) = -6 * c0 + 18 * c1 - 18 * c2 + 6 * c3 + 0 * t
}

@Config
object DriveConstants {
    @JvmField
    var MAX_ACC = 10.0

    @JvmField
    var MAX_DEC = 10.0

    @JvmField
    var MAX_VEL = 20.0

    @JvmField
    var MAX_FRACTION = 0.9

    @JvmField
    var PeruStart: Double = 10.0

    @JvmField
    var PeruEnd: Double = 50.0

    @JvmField
    var PeruMin: Double = 0.5

    @JvmField
    var PeruMax: Double = 1.0
}

class Trajectory(val start: Pose, val initVel: Double, val end: Pose, v1e: Vec2d, v2e: Vec2d, h1: Vec2d, val maxFraction: Double, val checkpoints: Int) {
    constructor(sp: Pose, initVel: Double, ep: Pose, v1e: Vec2d, v2e: Vec2d, h1: Vec2d, maxFraction: Double) : this(sp, initVel, ep, v1e, v2e, h1, maxFraction, 2000)
    constructor(sp: Pose, initVel: Double, ep: Pose, v1e: Vec2d, v2e: Vec2d, h1: Vec2d) : this(sp, initVel, ep, v1e, v2e, h1, MAX_FRACTION)
    constructor(sp: Pose, initVel: Double, ep: Pose, v1x: Double, v1y: Double, v2x: Double, v2y: Double, h1x: Double, h1y: Double) : this(sp, initVel, ep, Vec2d(v1x, v1y), Vec2d(v2x, v2y), Vec2d(h1x, h1y))
    constructor(sp: Pose, initVel: Double, ep: Pose, v1x: Double, v1y: Double, v2x: Double, v2y: Double) : this(sp, initVel, ep, Vec2d(v1x, v1y), Vec2d(v2x, v2y), Vec2d(0.3333, 0.666))
    constructor(sp: Pose, initVel: Double, ep: Pose) : this(sp, initVel, ep, Vec2d(), Vec2d(), Vec2d(0.3333, 0.6666))
    constructor(sp: Pose, ep: Pose) : this(sp, 0.0, ep)
    constructor() : this(Pose(), Pose())

    private val v1 = v1e.polar()
    private val v2 = v2e.polar()
    val checkLen = 1.0 / checkpoints.toDouble()

    private val cubX = CubicBezierCurve(start.x, start.x + v1.x, end.x + v2.x, end.x)
    private val cubY = CubicBezierCurve(start.y, start.y + v1.y, end.y + v2.y, end.y)
    private val cubH = CubicBezierCurve(0.0, h1.x, h1.y, 1.0) // Heading handled in get()

    operator fun get(t: Double) = if (t < 0.0) start else if (t > 1.0) end else Pose(cubX[t], cubY[t], start.h + angDiff(start.h, end.h) * cubH[t])

    override fun toString(): String = "$start:$end"
}