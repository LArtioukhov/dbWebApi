package lart.dbPrimitives

import scala.concurrent.Future

/**
  * Коннектор к конкретной коллекции документов
  *
  * Должны генерироваться запросы для получения
  *   - создание документа             [[DBNewDoc]]
  *   - списка документов              [[DBDocList]]
  *   - документа по его id            [[DBDocById]]
  *   - обновление/изменение документа [[DBUpdateDoc]]
  *   - удаление документа             [[DBDeleteDoc]]
  */
trait CollectionConnector {

  val collectionName: String

  def newDoc(newDocString: String): Future[String]
  def docList(sP: Int, aP: Int, fltr: String, projection: Option[String]): Future[String]
  def docById(id: String): Future[String]
  def updateDoc(id: String, doc4Update: String): Future[String]
  def deleteDoc(id: String): Future[String]
}
