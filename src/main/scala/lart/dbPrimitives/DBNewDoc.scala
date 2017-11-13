package lart.dbPrimitives

import scala.concurrent.Future

trait DBNewDoc extends DBRequest {

  val doc: String

  def insertDoc: Future[String]

  override def requestResult: Future[String] = {
    import lart.webService.WebService.executionContext
    val newDoc = insertDoc
    for {
      r1 ← newDoc
    } yield r1
  }
}
