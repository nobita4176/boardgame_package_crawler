// refer and thanks to http://tototoshi.hatenablog.com/entry/20111230/1325239770
package com.github.nobita4176.boardgame_pc

import scala.xml.{ NodeSeq, Elem }
import dispatch._
import net.liftweb._
import common._
import util._
import scalax.io._

package object scraper {
  object Filename {
    def unapply(url: String): Option[String] = {
      url.split("/").reverse.toList.headOption
    }
  }

  def save(url: String, name: String): Unit = {
    val data = Resource.fromURL(url).byteArray
    url match {
      case Filename(file) => {
        val ext = """\.\w+$""".r.findFirstIn(file).getOrElse(".jpg")
        Resource.fromFile(new java.io.File("data", name + ext)).write(data)
        println(name + " done.")
      }
      case _ => sys.error("Oops!")
    }
  }
}
