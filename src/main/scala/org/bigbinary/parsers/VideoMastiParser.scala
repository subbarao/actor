package org.bigbinary.parsers

import java.net.URL

import org.apache.commons.lang3.StringEscapeUtils
import org.bigbinary.domain.Movie
import org.htmlcleaner.CleanerProperties
import org.htmlcleaner.HtmlCleaner

object VideoMastiParser {
  private val baseUrl = "http://videomasti.net/telugu-movie-index-";
  def parse(input: Char) = {
    var props = new CleanerProperties()
    var cleaner = new HtmlCleaner(props)

    var rootNode = cleaner.clean(new URL(baseUrl + input + "/"))
    for (elem <- rootNode.getElementsByName("div", true)) {
      val classType = elem.getAttributeByName("class")
      if (classType != null && classType.equalsIgnoreCase("azindex")) {
        for (link <- elem.getElementsByName("a", true)) {
          val name = StringEscapeUtils.unescapeHtml4(elem.getText.toString)
          new Movie(name, elem.getAttributeByName("href"), "VideoMasti").insert
        }
      }
    }
  }
}