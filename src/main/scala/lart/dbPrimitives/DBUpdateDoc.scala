package lart.dbPrimitives

import scala.concurrent.Future

trait DBUpdateDoc extends DBRequest {

  val id: String
  val doc: String

  def updateDoc: Future[Long]

  override def requestResult: Future[String] = {
    import lart.webService.WebService.executionContext
    val doc = updateDoc
    for {
      r1 ← doc
    } yield """{"updated":""" + r1 + "}"
  }
}
