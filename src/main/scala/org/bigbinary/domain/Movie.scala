package org.bigbinary.domain

import com.mongodb.DBObject
import com.mongodb.casbah.MongoClient
import com.mongodb.casbah.commons.MongoDBObject

case class Movie(val name: String, val href: String, val source: String) extends MongoLike

trait MongoLike {
  def name: String
  def href: String
  def source: String

  def toMongoObj: DBObject = {
    MongoDBObject("name" -> name, "source" -> source, "href" -> href)
  }
  def insert {
    MongoLike.insert(toMongoObj)
  }

}

object MongoLike {
  def insert(mongobObj: DBObject) {
    collection.insert(mongobObj)
  }
  def find(query: String) {
    collection.find(MongoDBObject("name" -> query))
  }
  lazy val mongoClient = MongoClient("localhost", 27017)
  lazy val mongoDB = mongoClient("cast-movies")
  lazy val collection = mongoDB("movies")
}