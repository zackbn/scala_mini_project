# Scala learning exercises

My exercises while learning Scala, as a first step toward data engineering and Spark.

## Contents

| File | Topic |
|------|-------|
| `Transport.scala` | Traits, inheritance, `override`, `super`, polymorphism, `Option` |

## What `Transport.scala` covers

- **Traits** with abstract methods (`move`, `maxSpeed`) and a shared concrete method (`summary`)
- **Inheritance**: `ElectricCar` extends `Car`, overrides `maxSpeed` and extends `move` with `super`
- **Mixing traits**: `ElectricCar` is both a `Vehicle` and `Electric`
- **Polymorphism**: one `List[Vehicle]`, each element runs its own `move()`
- **Collections**: `map`, `filter`, `maxBy`, `flatMap`
- **`Option`** for results that may not exist (empty list)

## Run

Requires [Scala 3](https://www.scala-lang.org/download/) (Scala CLI).

```bash
scala run Transport.scala
```

Expected output:

```
=== Summaries ===
Audi | max speed: 180 km/h | Audi drives on the road
Btwin | max speed: 25 km/h | Btwin rides on the bike lane
TGV | max speed: 320 km/h | TGV runs on the rails
Tesla | max speed: 160 km/h | Tesla drives on the road silently

=== Analysis ===
Fastest:          TGV
Faster than 100:  Audi, TGV, Tesla
Average speed:    171.25 km/h

=== Electric vehicles ===
Tesla: charging (range: 400 km)

=== Empty list ===
Fastest:          none
```
