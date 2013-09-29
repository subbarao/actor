package org.bigbinary.parsers

import java.net.URL

import org.apache.commons.lang3.StringEscapeUtils
import org.bigbinary.domain.Movie
import org.htmlcleaner.CleanerProperties
import org.htmlcleaner.HtmlCleaner

object Ap7amParser {
  def parse(input: Char) = {
    var props = new CleanerProperties()
    var cleaner = new HtmlCleaner(props)

    var rootNode = cleaner.clean(new URL("http://www.ap7am.com/Movies-watch-1-" + input + "-movie-online.html"))
    var urls = List[String]()
    urls ::= "Movies-watch-1-" + input + "-movie-online.html"
    for (elem <- rootNode.getElementsByName("a", true)) {
      val classType = elem.getAttributeByName("class")
      if (classType != null && classType.equalsIgnoreCase("paging")) {
        urls ::= elem.getAttributeByName("href")
      }
    }
      
    for (url <- urls) {
      rootNode = cleaner.clean(new URL("http://www.ap7am.com/" +url))
      for (elem <- rootNode.getElementsByName("a", true)) {
        val classType = elem.getAttributeByName("style")
        if (classType != null && classType.equalsIgnoreCase("color:#333333;font-weight:bold; font-size:15px;")) {
          val name = StringEscapeUtils.unescapeHtml4(elem.getText.toString)
          new Movie(name, elem.getAttributeByName("href"), "Ap7am").insert
        }
      }
    }
  }
}
