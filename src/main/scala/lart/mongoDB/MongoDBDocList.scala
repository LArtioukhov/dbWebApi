package lart.mongoDB

import lart.dbPrimitives.DBDocListRequest
import org.mongodb.scala.{Document, MongoCollection}

import scala.concurrent.Future

class MongoDBDocList(val collection: MongoCollection[Document],
                     override val startPosition: Int,
                     override val documentsAmount: Int,
                     override val filter: String)
    extends DBDocListRequest {

  override def getList: Future[Seq[String]] =
    collection
      .find(Document(filter))
      .skip(startPosition)
      .limit(documentsAmount)
      .map(_.toJson())
      .toFuture

  override def getTotalCount: Future[Long] =
    collection.count(Document(filter)).toFuture
}

object MongoDBDocList {

  def apply(collection: MongoCollection[Document],
            startPosition: Int,
            documentsAmount: Int,
            filter: String): MongoDBDocList =
    new MongoDBDocList(collection, startPosition, documentsAmount, filter)
}
