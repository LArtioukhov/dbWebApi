package lart.mongoDB

import org.mongodb.scala._

import lart.appSettings._

class MongoDBConnector(host: String, port: Int) {

  private val client: MongoDatabase =
    MongoClient(connString).getDatabase(mongoDBSettings.dataBaseName)

  private def connString: String = s"mongodb://${mongoDBSettings.host}:${mongoDBSettings.port}"

  def getCollection(collName: String): MongoCollection[Document] = client.getCollection(collName)

}

object MongoDBConnector {

  def apply(host: String, port: Int): MongoDBConnector = new MongoDBConnector(host, port)
}
