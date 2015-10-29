package com.github.nobita4176.boardgame_pc

import scraper._
import xmlhelper.XmlFilter._

import scala.xml.{NodeSeq, Elem, Node}
import dispatch._, Defaults._
import net.liftweb._
import net.liftweb.common._
import util._
import scalax.io._
import scala.io.Source

object BoardgamePackageCrawler {
  def main(args: Array[String]) : Unit = {
    read_list("list.txt").foreach{
      name => {
        println(name)
        crawl(name)
      }
    }
  }

  def crawl(name: String) : Unit = {
    val http = Http.configure(_ setFollowRedirects true)
    var req = http(url("http://www.amazon.co.jp/gp/search?field-keywords=" + xml.Utility.escape(name)) OK as.String)

    val e: NodeSeq = Html5.parse(req.apply()) openOr NodeSeq.Empty
    val targets = (e \\@ ("li", "id", "result_0") \\ "img" \\ "@src")
    targets
      .map {node => node.toString()}
      .foreach {src => save(src, name)}
  }

  def read_list(filename: String) : List[String] = {
    val s = Source.fromFile(filename, "UTF-8")
    val list =
      try
        s.getLines.toList
      finally
        s.close

    list.map(line => line.mkString)
  }
}
