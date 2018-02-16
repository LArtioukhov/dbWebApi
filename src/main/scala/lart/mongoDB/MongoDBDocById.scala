package lart.mongoDB

import lart.dbPrimitives.DBDocById
import org.bson.types.ObjectId
import org.mongodb.scala.model.Filters.equal
import org.mongodb.scala.{Document, MongoCollection}

import scala.concurrent.Future

class MongoDBDocById(val collection: MongoCollection[Document], override val id: String)
    extends DBDocById {

  /**
    * Must return only one document
    *
    * @return
    */
  override def getDocById: Future[Seq[String]] = {
    collection
      .find(equal("_id", new ObjectId(id)))
      .limit(1)
      .map(_.toJson())
      .toFuture()
  }
}

object MongoDBDocById {

  def apply(collection: MongoCollection[Document], id: String): MongoDBDocById =
    new MongoDBDocById(collection, id)
}
