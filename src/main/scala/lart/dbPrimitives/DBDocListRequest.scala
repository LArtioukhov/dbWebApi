package lart.dbPrimitives

import scala.concurrent.Future

trait DBDocListRequest extends DBRequest {

  val startPosition: Int
  val documentsAmount: Int
  val filter: String

  def getTotalCount: Future[Long]

  def getList: Future[Seq[String]]

  override def requestResult: Future[String] = {
    import lart.webService.WebService.executionContext
    val count = getTotalCount
    val documents = getList
    for {
      r1 ← count
      r2 ← documents
    } yield "{count:" + r1 + ", documents:" + r2.mkString("[{", "},{", "}]")
  }
}