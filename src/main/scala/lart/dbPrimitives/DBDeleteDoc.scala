package lart.dbPrimitives

import scala.concurrent.Future

import lart.appSettings._

trait DBDeleteDoc extends DBRequest {

  val id: String

  def deleteResult: Future[Long]

  override def requestResult: Future[String] = {
    val count = deleteResult
    for {
      r1 ← count
    } yield """{"deleted":""" + r1 + "}"
  }
}
