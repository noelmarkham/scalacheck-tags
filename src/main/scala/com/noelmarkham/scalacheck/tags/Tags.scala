package com.noelmarkham.scalacheck.tags

import org.scalacheck.{Gen, Arbitrary}

object Tags {

  type Tagged[U] = { type Tag = U }
  type !![T, U] = T with Tagged[U]

  private def tag[A, T](a: A): A !! T = a.asInstanceOf[A !! T]

  sealed trait Absolute
  sealed trait Positive
  sealed trait Negative

  sealed trait Ordered

  implicit def absoluteNumeric[T](implicit numeric: Numeric[T], arb: Arbitrary[T]): Arbitrary[T !! Absolute] = {

    def genAbsNumeric: Gen[T !! Absolute] = {
      Arbitrary.arbitrary[T].flatMap { value =>
        val absolute = numeric.abs(value)
        if (numeric.lt(absolute, numeric.zero)) genAbsNumeric
        else Gen.const(tag[T, Absolute](absolute))
      }
    }

    Arbitrary(genAbsNumeric)
  }

  implicit def positiveNumeric[T](implicit numeric: Numeric[T], arb: Arbitrary[T]): Arbitrary[T !! Positive] = {

    def genPosNumeric: Gen[T !! Positive] = {
      Arbitrary.arbitrary[T].flatMap { value =>
        val absolute = numeric.abs(value)
        if (numeric.lteq(absolute, numeric.zero)) genPosNumeric
        else Gen.const(tag[T, Positive](absolute))
      }
    }

    Arbitrary(genPosNumeric)
  }

  implicit def negativeNumeric[T](implicit numeric: Numeric[T], arb: Arbitrary[T]): Arbitrary[T !! Negative] = {

    def genNegNumeric: Gen[T !! Negative] = {
      Arbitrary.arbitrary[T].flatMap { value =>
        val absolute = numeric.abs(value)
        if (numeric.gteq(absolute, numeric.zero)) genNegNumeric
        else Gen.const(tag[T, Negative](absolute))
      }
    }

    Arbitrary(genNegNumeric)
  }

  implicit def orderedTuple[T](implicit numeric: Numeric[T], arb: Arbitrary[T]): Arbitrary[(T, T) !! Ordered] = Arbitrary {
    for {
      a <- Arbitrary.arbitrary[T]
      b <- Arbitrary.arbitrary[T]
    } yield tag[(T, T), Ordered](numeric.min(a, b), numeric.max(a, b))
  }
}
