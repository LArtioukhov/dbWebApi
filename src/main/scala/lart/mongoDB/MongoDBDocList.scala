package lart.mongoDB

import lart.dbPrimitives.DBDocList
import org.mongodb.scala.{Document, MongoCollection}

import scala.concurrent.Future

class MongoDBDocList(val collection: MongoCollection[Document],
                     override val startPosition: Int,
                     override val documentsAmount: Int,
                     override val filter: String,
                     override val projection: Option[String])
    extends DBDocList {

  override def getList: Future[Seq[String]] =
    projection match {
      case None ⇒
        collection
          .find(Document(filter))
          .skip(startPosition)
          .limit(documentsAmount)
          .map(_.toJson())
          .toFuture
      case Some(prj) ⇒
        collection
          .find(Document(filter))
          .projection(Document(prj))
          .skip(startPosition)
          .limit(documentsAmount)
          .map(_.toJson())
          .toFuture

    }

  override def getTotalCount: Future[Long] =
    collection.count(Document(filter)).toFuture
}

object MongoDBDocList {

  def apply(collection: MongoCollection[Document],
            startPosition: Int,
            documentsAmount: Int,
            filter: String,
            projection: Option[String]): MongoDBDocList =
    new MongoDBDocList(collection, startPosition, documentsAmount, filter, projection)
}
