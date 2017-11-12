package lart.dbPrimitives

import scala.concurrent.Future

trait DBDocByIdRequest extends DBRequest {

  val id: String

  /**
    * Must return only one string
    *
    * @return
    */
  def getDocById: Future[Seq[String]]

  override def requestResult: Future[String] = {
    import lart.webService.WebService.executionContext
    for {
      doc <- getDocById
    } yield doc.mkString("")
  }
}
