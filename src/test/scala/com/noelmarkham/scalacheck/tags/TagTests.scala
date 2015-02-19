package com.noelmarkham.scalacheck.tags

import com.noelmarkham.scalacheck.tags.Tags._
import org.scalacheck._
import org.scalacheck.Prop._

object TagTests extends Properties("Tags") {

  property("Absolute") = forAll { (x: Int !! Absolute) => x >= 0 }

  property("Positive") = forAll { (x: Int !! Positive) => x > 0 }

  property("Negative") = forAll { (x: Int !! Negative) => x < 0}

  property("Ordered tuple") = forAll { (x: (Int, Int) !! Ordered) => x._1 <= x._2 }

  property("Unique tuple") = forAll {(x: (Int, Int) !! Unique) => x._1 != x._2}
}
