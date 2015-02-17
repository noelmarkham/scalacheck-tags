package com.noelmarkham.scalacheck.tags

import com.noelmarkham.scalacheck.tags.Tags._
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object TagTests extends Properties("Tags"){

  property("Absolute") = forAll { (x: Int !! Absolute) => x >= 0 }

  property("Positive") = forAll { (x: Int !! Positive) => x > 0 }

  property("Negative") = forAll { (x: Int !! Negative) => x < 0}

  property("Ordered tuple") = forAll { (x: (Int, Int) !! Ordered) => x._1 <= x._2 }
}
