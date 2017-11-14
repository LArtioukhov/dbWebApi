package lart.routeParts

import akka.http.scaladsl.model._
import lart.webService.WebServicePart
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Route
import lart.dbPrimitives.CollectionConnector
import lart.helpers.AppSettings._

import scala.concurrent.Future
import scala.util.{Failure, Success}

class DocCollection(collectionConnector: CollectionConnector) extends WebServicePart {

  private def dbResponseProcessing(response: Future[String]): Route =
    onComplete(response) {
      case Success(str) ⇒
        complete(OK, HttpEntity(ContentTypes.`application/json`, str))
      case Failure(ex) ⇒ complete(InternalServerError, ex.getMessage)
    }

  private def methodNotAllowed = {
    complete(MethodNotAllowed, appName + " API: Unsupported request")
  }

  override def RouteGenerator: Route =
    path(collectionConnector.collectionName) {
      parameters('sP.as[Int] ? 0, 'aP.as[Int] ? 40, 'fltr.as[String] ? "{}") { (sP, aP, fltr) ⇒
        get { //get list of documents
          dbResponseProcessing(collectionConnector.docList(sP, aP, fltr))
        } ~ post { // create new document
          entity(as[String]) { newDocString ⇒
            dbResponseProcessing(collectionConnector.newDoc(newDocString))
          }
        } ~ (put | delete) { methodNotAllowed }
      }
    } ~ path(collectionConnector.collectionName / Segment) { id ⇒
      get { //get document by Id
        dbResponseProcessing(collectionConnector.docById(id))
      } ~ put { //update document
        entity(as[String]) { doc ⇒
          dbResponseProcessing(collectionConnector.updateDoc(id, doc))
        }
      } ~ delete { //delete document by Id
        dbResponseProcessing(collectionConnector.deleteDoc(id))
      } ~ post { methodNotAllowed }
    }

}

object DocCollection {

  def apply(collectionConnector: CollectionConnector): DocCollection =
    new DocCollection(collectionConnector)
}
