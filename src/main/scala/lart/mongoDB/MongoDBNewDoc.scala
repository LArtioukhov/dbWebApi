package lart.mongoDB

import lart.dbPrimitives.DBNewDoc
import org.mongodb.scala.{Document, MongoCollection}

import scala.concurrent.Future

class MongoDBNewDoc(val collection: MongoCollection[Document], override val doc: String)
    extends DBNewDoc {

  override def insertDoc: Future[String] = {
    collection.insertOne(Document(doc)).map("""{result:"""" + _.toString() + """"}""").head()
  }
}

object MongoDBNewDoc {

  def apply(collection: MongoCollection[Document], doc: String): MongoDBNewDoc =
    new MongoDBNewDoc(collection, doc)
}
