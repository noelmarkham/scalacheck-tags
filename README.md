# ScalaCheck Tags

ScalaCheck Tags is a lightweight library that allows you to constrain types for use in property-based tests.

For example, if you require your ScalaCheck property to have a positive integer, you cannot simply use an `Int` without then "massaging" the arbitrarily generated value to be positive:

```scala
property("List.fill generates a list of the correct length") = forAll { i: Int =>
  (i >= 0) ==> (List.fill(i)(()).length == i)
}
```

Importing the ScalaCheck Tags library allows you to be more explicit in your type definition:

```scala
property("List.fill generates a list of the correct length") = forAll { (i: Int !! Absolute) =>
  List.fill(i)(()).length == i
}
```

Notice that although you are technically returning a different type, the generated value can still be treated as the primitive `int`.

## Motivation
Conditional properties are often used to ensure that a certain type matches some given criteria. However, if your criteria is too specific, then ScalaCheck can give up quite easily. For example, given the following property:

```scala
property("All numbers are positive") = forAll {
  (a: Int, b: Int, c: Int) =>
    (a > 0 && b > 0 && c > 0) ==> {

      a > 0 && b > 0 && c > 0
    }
}
```

Constraining just _three_ integers to be positive results in ScalaCheck giving up:

```
[info] ! Example.All numbers are positive: Gave up after only 10 passed tests. 91 tests were discarded.
```

However, using ScalaCheck Tags ensures that the generated values meet the given criteria *before* the criteria is applied:

```scala
property("All tagged numbers are positive") = forAll {
  (a: Int !! Positive,
   b: Int !! Positive,
   c: Int !! Positive) =>

  a > 0 && b > 0 && c > 0
}
```

This test will succeed:

```
[info] + Example.All tagged numbers are positive: OK, passed 100 tests.
```

## Usage

Currently, the following tags are supported for `Numeric` instances:

  * `Absolute`
  * `Positive`
  * `Negative`

Tuples containing a pair of `Numeric` values can also be tagged with `Ordered`.

Tuples can be constrained to be unique with `Unique`.

Types are tagged with the `!!` operator.

## Examples

  * `Double !! Absolute`: A `Double` greater than or equal to zero
  * `Float !! Negative`: A negative `Float`
  * `(Int, Int) !! Ordered`: A pair of `Int`s, with `_1 <= _2`

Distributed under the MIT licence.
