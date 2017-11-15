package lart.mongoDB

import scala.concurrent.Future

import org.bson.types.ObjectId
import org.mongodb.scala.model.Filters
import org.mongodb.scala.{Document, MongoCollection}

import lart.dbPrimitives.DBUpdateDoc
import lart.appSettings._

class MongoDBUpdateDoc(val collection: MongoCollection[Document],
                       override val id: String,
                       override val doc: String)
    extends DBUpdateDoc {

  override def updateDoc: Future[Long] = {
    collection
      .replaceOne(Filters.equal("_id", new ObjectId(id)), Document(doc))
      .head()
      .map(_.getModifiedCount)
  }

}

object MongoDBUpdateDoc {

  def apply(collection: MongoCollection[Document], id: String, doc: String): MongoDBUpdateDoc =
    new MongoDBUpdateDoc(collection, id, doc)
}
