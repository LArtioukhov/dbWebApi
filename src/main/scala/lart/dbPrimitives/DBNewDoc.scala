package lart.dbPrimitives

import scala.concurrent.Future

import lart.appSettings._

trait DBNewDoc extends DBRequest {

  val doc: String

  def insertDoc: Future[String]

  override def requestResult: Future[String] = {
    val newDoc = insertDoc
    for {
      r1 ← newDoc
    } yield """{ "result":"""" + r1 + """" }"""
  }
}
