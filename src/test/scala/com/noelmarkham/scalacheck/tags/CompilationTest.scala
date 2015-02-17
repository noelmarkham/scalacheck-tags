package com.noelmarkham.scalacheck.tags

import com.noelmarkham.scalacheck.tags.Tags._

object CompilationTest {

  def tagIsSuperfluous(x: Int !! Absolute): Unit = {
    val untagged: Int = x
    ()
  }

}
