package lart.mongoDB

import lart.dbPrimitives.CollectionConnector
import org.mongodb.scala.{Document, MongoCollection}

import scala.concurrent.Future

class MongoDBCollectionConnector(val collection: MongoCollection[Document])
    extends CollectionConnector {

  override val collectionName: String = collection.namespace.getCollectionName

  override def newDoc(newDoc: String): Future[String] =
    MongoDBNewDoc(collection, newDoc).requestResult

  override def docList(sP: Int,
                       aP: Int,
                       filter: String,
                       projection: Option[String]): Future[String] =
    MongoDBDocList(collection, sP, aP, filter, projection).requestResult

  override def docById(id: String): Future[String] = MongoDBDocById(collection, id).requestResult

  override def updateDoc(id: String, doc4Update: String): Future[String] =
    MongoDBUpdateDoc(collection, id, doc4Update).requestResult

  override def deleteDoc(id: String): Future[String] =
    MongoDBDeleteDoc(collection, id).requestResult
}

object MongoDBCollectionConnector {

  def apply(collection: MongoCollection[Document]): MongoDBCollectionConnector =
    new MongoDBCollectionConnector(collection)
}
