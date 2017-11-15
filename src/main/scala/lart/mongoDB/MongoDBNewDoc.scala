package lart.mongoDB

import scala.concurrent.Future

import org.mongodb.scala.{Document, MongoCollection}

import lart.appSettings._
import lart.dbPrimitives.DBNewDoc

class MongoDBNewDoc(val collection: MongoCollection[Document], override val doc: String)
    extends DBNewDoc {

  override def insertDoc: Future[String] = {
    collection.insertOne(Document(doc)).head().map(_.toString())
  }
}

object MongoDBNewDoc {

  def apply(collection: MongoCollection[Document], doc: String): MongoDBNewDoc =
    new MongoDBNewDoc(collection, doc)
}
