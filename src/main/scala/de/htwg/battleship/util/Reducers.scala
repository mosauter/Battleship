package de.htwg.battleship.util

/**
  * Util
  *
  * @author ms
  * @since 2016-06-18
  */
object Reducers {
    def reduceBooleanAND(one: Boolean, two: Boolean): Boolean = one && two

    def reduceBooleanOR(one: Boolean, two: Boolean): Boolean = one || two
}
