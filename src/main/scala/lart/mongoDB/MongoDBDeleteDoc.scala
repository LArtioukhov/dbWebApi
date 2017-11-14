package lart.mongoDB

import lart.dbPrimitives.DBDeleteDoc
import org.bson.types.ObjectId
import org.mongodb.scala.model.Filters.equal
import org.mongodb.scala.{Document, MongoCollection}

import scala.concurrent.Future

class MongoDBDeleteDoc(val collection: MongoCollection[Document], override val id: String)
    extends DBDeleteDoc {

  override def deleteResult: Future[Long] = {
    import lart.webService.WebService.executionContext
    collection.deleteOne(equal("_id", new ObjectId(id))).head().map(_.getDeletedCount)
  }
}

object MongoDBDeleteDoc {

  def apply(collection: MongoCollection[Document], id: String): MongoDBDeleteDoc =
    new MongoDBDeleteDoc(collection, id)
}
