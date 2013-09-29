package org.bigbinary

package bigb

import org.bigbinary.parsers.Ap7amParser
import org.bigbinary.parsers.VideoMastiParser



object Main extends Application {
  var allLetters = List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')
 
  allLetters.foreach(Ap7amParser.parse)
  allLetters.foreach(VideoMastiParser.parse)
}