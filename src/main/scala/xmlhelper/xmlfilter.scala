// refer and thanks to http://www.mwsoft.jp/programming/scala/web_scraping.html
package com.github.nobita4176.boardgame_pc.xmlhelper

import scala.xml.NodeSeq

object XmlFilter {
  implicit def nodeSeqToMyXmlFilter(nodeSeq: NodeSeq): XmlFilter = 
    new XmlFilter(nodeSeq)
}

class XmlFilter(that: NodeSeq) {

  import XmlFilter._

  /** 属性用のFilter */
  def attrFilter(name: String, value: String): NodeSeq = {
    that filter (_ \ ("@" + name) exists (_.text == value))
  }

  /** attrFilter付き\\ */
  def \\@(nodeName: String, attrName: String, value: String): NodeSeq = {
    that \\ nodeName attrFilter (attrName, value)
  }

  /** attrFilter付き\ */
  def \@(nodeName: String, attrName: String, value: String): NodeSeq = {
    that \ nodeName attrFilter (attrName, value)
  }
}
