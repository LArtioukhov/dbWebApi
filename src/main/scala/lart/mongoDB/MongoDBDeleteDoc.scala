package lart.mongoDB

import scala.concurrent.Future

import org.bson.types.ObjectId
import org.mongodb.scala.model.Filters.equal
import org.mongodb.scala.{Document, MongoCollection}

import lart.appSettings._
import lart.dbPrimitives.DBDeleteDoc

class MongoDBDeleteDoc(val collection: MongoCollection[Document], override val id: String)
    extends DBDeleteDoc {

  override def deleteResult: Future[Long] = {
    collection.deleteOne(equal("_id", new ObjectId(id))).head().map(_.getDeletedCount)
  }
}

object MongoDBDeleteDoc {

  def apply(collection: MongoCollection[Document], id: String): MongoDBDeleteDoc =
    new MongoDBDeleteDoc(collection, id)
}
