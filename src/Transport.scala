/**
 * Transport — a small object-oriented Scala exercise.
 *
 * Concepts shown:
 *   - traits (abstract and concrete methods)
 *   - class inheritance, override and super
 *   - mixing several traits into one class
 *   - polymorphism through a List[Vehicle]
 *   - collection functions: map, filter, maxBy, flatMap
 *   - Option instead of null or fake default values
 */
object Transport {

  // ---------------------------------------------------------------------------
  // Capabilities
  // ---------------------------------------------------------------------------

  trait Vehicle {
    def name: String
    def maxSpeed: Int
    def move(): String

    def summary(): String = s"$name | max speed: $maxSpeed km/h | ${move()}"
  }

  trait Electric {
    def rangeKm: Int

    def charge(): String = s"charging (range: $rangeKm km)"
  }

  // ---------------------------------------------------------------------------
  // Concrete vehicles
  // ---------------------------------------------------------------------------

  class Car(val name: String) extends Vehicle {
    def maxSpeed: Int = 180
    def move(): String = s"$name drives on the road"
  }

  class Bike(val name: String) extends Vehicle {
    def maxSpeed: Int = 25
    def move(): String = s"$name rides on the bike lane"
  }

  class Train(val name: String) extends Vehicle {
    def maxSpeed: Int = 320
    def move(): String = s"$name runs on the rails"
  }

  /** An electric car IS a car (inheritance) AND is electric (trait). */
  class ElectricCar(name: String) extends Car(name) with Electric {
    override def maxSpeed: Int = 160
    override def move(): String = super.move() + " silently"
    def rangeKm: Int = 400
  }

  // ---------------------------------------------------------------------------
  // Analysis
  // ---------------------------------------------------------------------------

  /** Name of the fastest vehicle, or None if the list is empty. */
  def fastest(vehicles: List[Vehicle]): Option[String] =
    if (vehicles.isEmpty) None
    else Some(vehicles.maxBy(v => v.maxSpeed).name)

  /** Names of the vehicles strictly faster than the threshold. */
  def fasterThan(vehicles: List[Vehicle], threshold: Int): List[String] =
    vehicles.filter(v => v.maxSpeed > threshold).map(v => v.name)

  /** Average max speed, or None if the list is empty. */
  def averageSpeed(vehicles: List[Vehicle]): Option[Double] =
    if (vehicles.isEmpty) None
    else Some(vehicles.map(v => v.maxSpeed).sum.toDouble / vehicles.size)

  /** One charging line per electric vehicle. */
  def chargingReport(vehicles: List[Vehicle]): List[String] =
    vehicles.flatMap(v => v match {
      case e: Electric => List(s"${v.name}: ${e.charge()}")
      case _           => Nil
    })

  // ---------------------------------------------------------------------------
  // Program
  // ---------------------------------------------------------------------------

  def main(args: Array[String]): Unit = {
    val vehicles: List[Vehicle] = List(
      new Car("Audi"),
      new Bike("Btwin"),
      new Train("TGV"),
      new ElectricCar("Tesla")
    )

    printSection("Summaries")
    vehicles.foreach(v => println(v.summary()))

    printSection("Analysis")
    println(s"Fastest:          ${fastest(vehicles).getOrElse("none")}")
    println(s"Faster than 100:  ${fasterThan(vehicles, 100).mkString(", ")}")
    println(s"Average speed:    ${averageSpeed(vehicles).getOrElse(0.0)} km/h")

    printSection("Electric vehicles")
    chargingReport(vehicles).foreach(line => println(line))

    printSection("Empty list")
    println(s"Fastest:          ${fastest(List()).getOrElse("none")}")
  }

  private def printSection(title: String): Unit =
    println(s"\n=== $title ===")
}
