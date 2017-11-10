package lart.mongoDB

import lart.dbPrimitives.DBRequest
import org.mongodb.scala.{Document, FindObservable, MongoCollection, Observable}

import scala.concurrent.Future

class MongoDBDocList(private val collection: MongoCollection[Document]) extends DBRequest {

  private var _startPosition: Option[Int]   = None
  private var _documentsAmount: Option[Int] = None
  private var _filter: Option[String]       = None

  def startPosition = _startPosition
  private def startPosition_=(newValue: Int): Unit = {
    _startPosition = Some(newValue)
  }

  def documentsAmount = _documentsAmount
  private def documentsAmount_=(newVal: Int): Unit = {
    _documentsAmount = Some(newVal)
  }

  def filter = _filter
  private def filter_=(newVal: String): Unit = {
    _filter = Some(newVal)
  }

  def setConditions(startPosition: Int, documentsAmount: Int, filter: String): MongoDBDocList = {
    this.startPosition = startPosition
    this.documentsAmount = documentsAmount
    this.filter = filter
    this
  }

  private def getList: Future[Seq[String]] = {
    val find = filter match {
      case Some(x) ⇒ collection.find(Document(x))
      case None    ⇒ collection.find()
    }
    val skip = startPosition match {
      case Some(i) ⇒ find.skip(i)
      case None    ⇒ find
    }
    val limit = documentsAmount match {
      case Some(i) ⇒ skip.limit(i)
      case None    ⇒ skip
    }
    limit.map(_.toJson()).toFuture()
  }

  private def getTotalCount: Future[Long] = {
    val count = filter match {
      case Some(s) ⇒ collection.count(Document(s))
      case None    ⇒ collection.count()
    }
    count.toFuture()
  }

  override def requestResult: Future[String] = {
    val count     = getTotalCount
    val documents = getList
    for {
      r1 ← count
      r2 ← documents
    } yield "{count:" + r1 + ", documents:" + r2.mkString("[{", "},{", "}]")
  }
}

object MongoDBDocList {

  def apply(collection: MongoCollection[Document]): MongoDBDocList =
    new MongoDBDocList(collection)
}
