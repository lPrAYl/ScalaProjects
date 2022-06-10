import javax.swing.UIDefaults.LazyValue

case class Position(x: Double, y: Double) {
  def distanceTo(that: Position): Double = ???

  def distanceToLine(line: (Position, Position)): Double = ???
}

object Position {
  val player = Position(0, 1.80)
  val hoop = Position(6.75, 3.048)
}

case class Angle(radians: Double)

case class Speed(meterToSecond: Double)

def isWinningShot(angle: Angle, speed: Speed): Boolean = {
  val v0X = speed.meterToSecond * math.cos(angle.radians)
  val v0Y = speed.meterToSecond * math.sin(angle.radians)
  val p0X = Position.player.x
  val p0Y = Position.player.y
  val g = -9.81

  def goesThroughHoop(line: (Position, Position)): Boolean =
    Position.hoop.distanceToLine(line) < 0.01

  def isNotToFar(position: Position): Boolean =
    position.y > 0 && position.x <= Position.hoop.x + 0.01

  def position(t: Int): Position = {
    val x = p0X + v0X * t
    val y = p0Y + v0Y * t
    Position(x, y)
  }

    def loop(time: Int): Boolean = {
      val currentPosition = position(time)
      if (isNotToFar(currentPosition)) {
        val nextPosition = position(time + 1)
        val line = (currentPosition, nextPosition)
        goesThroughHoop(line) || loop(time + 1)
      } else
        false
    }

  loop(time = 0)
}

val angle = Angle