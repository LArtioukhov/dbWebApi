package lart.dbPrimitives

import scala.concurrent.Future

/**
  * Коннектор к конкретной коллекции
  *
  * Должны генерироваться запросы для получения
  *   - создание документа
  *   - списка документов  [[DBDocListRequest]]
  *   - документа по его id [[DBDocByIdRequest]]
  *   - обновление/изменение документа
  *   - удаление документа
  */
trait CollectionConnector {

  val collectionName: String

  def newDoc(newDocString: String): Future[String]
  def docList(sP: Int, aP: Int, fltr: String): Future[String]
  def docById(id: String): Future[String]
  def updateDoc(id: String, doc4Update: String): Future[String]
  def deleteDoc(id: String): Future[String]
}
