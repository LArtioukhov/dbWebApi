package lart.webService

class WebServiceDaemon extends AbstractWebServiceDaemon {

  override def webServer: WebServiceLifecycle = WebService

}
