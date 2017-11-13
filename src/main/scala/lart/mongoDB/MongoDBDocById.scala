package lart.mongoDB

import lart.dbPrimitives.DBDocByIdRequest
import org.bson.types.ObjectId
import org.mongodb.scala.model.Filters.equal
import org.mongodb.scala.{Document, MongoCollection}

class MongoDBDocById(val collection: MongoCollection[Document],
                     override val id: String)
    extends DBDocByIdRequest {

  /**
    * Must return only one string
    *
    * @return
    */
  override def getDocById = {
    collection
      .find(equal("_id", new ObjectId(id)))
      .limit(1)
      .map(_.toJson())
      .toFuture()
  }
}
