package lart.dbPrimitives

import scala.concurrent.Future

import lart.appSettings._

trait DBRequest {
  def requestResult: Future[String]
}

trait DBNewDoc extends DBRequest {

  val doc: String

  def insertDoc(): Future[String]

  override def requestResult: Future[String] = {
    val newDoc = insertDoc()
    for {
      r1 ← newDoc
    } yield """{ "result":"""" + r1 + """" }"""
  }
}

trait DBUpdateDoc extends DBRequest {

  val id: String
  val doc: String

  def updateDoc(): Future[Long]

  override def requestResult: Future[String] = {
    val doc = updateDoc()
    for {
      r1 ← doc
    } yield """{"updated":""" + r1 + "}"
  }
}

trait DBDeleteDoc extends DBRequest {

  val id: String

  def deleteResult(): Future[Long]

  override def requestResult: Future[String] = {
    val count = deleteResult()
    for {
      r1 ← count
    } yield """{"deleted":""" + r1 + "}"
  }
}

trait DBDocById extends DBRequest {

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

trait DBDocList extends DBRequest {

  val startPosition: Int
  val documentsAmount: Int
  val filter: String
  val projection: Option[String]

  def getTotalCount: Future[Long]

  def getList: Future[Seq[String]]

  override def requestResult: Future[String] = {
    val count     = getTotalCount
    val documents = getList
    for {
      r1 ← count
      r2 ← documents
    } yield """{ "count":""" + r1 + """, "documents":""" + r2.mkString("[", ",", "]}")
  }
}
