#ScalaCheck Tags

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

##Usage

Currently, the following tags are supported for `Numeric` instances:

  * `Absolute`
  * `Positive`
  * `Negative`

Tuples containing a pair of `Numeric` values can also be tagged with `Ordered`.

Types are tagged with the `!!` operator.

## Examples

  * `Double !! Absolute`: A `Double` greater than or equal to zero
  * `Float !! Negative`: A negative `Float`
  * `(Int, Int)`: A pair of `Int`s, with `_1` <= `_2`

Distributed under the MIT licence.
