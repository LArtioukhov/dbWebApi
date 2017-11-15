package lart.dbPrimitives

import scala.concurrent.Future

import lart.appSettings._

trait DBDocByIdRequest extends DBRequest {

  val id: String

  /**
    * Must return only one document
    *
    * @return
    */
  def getDocById: Future[Seq[String]]

  override def requestResult: Future[String] = {
    for {
      doc <- getDocById
    } yield doc.mkString
  }
}
