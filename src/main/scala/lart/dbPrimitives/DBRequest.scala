package lart.dbPrimitives

import scala.concurrent.Future

trait DBRequest {

  def requestResult: Future[String]
}
