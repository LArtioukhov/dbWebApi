package lart.dbPrimitives

import scala.concurrent.Future

trait DBDeleteDoc extends DBRequest {
  val id: String

  def deleteResult: Future[Long]

  override def requestResult: Future[String] = {
    import lart.webService.WebService.executionContext
    val count = deleteResult
    for {
      r1 ← count
    } yield "{deleted:" + r1 + "}"
  }
}
