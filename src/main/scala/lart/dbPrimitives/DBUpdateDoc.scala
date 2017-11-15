package lart.dbPrimitives

import scala.concurrent.Future

import lart.appSettings._

trait DBUpdateDoc extends DBRequest {

  val id: String
  val doc: String

  def updateDoc: Future[Long]

  override def requestResult: Future[String] = {
    val doc = updateDoc
    for {
      r1 ← doc
    } yield """{"updated":""" + r1 + "}"
  }
}
