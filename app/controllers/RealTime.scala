package controllers


object RealTime {
  object Controller1 {
    import Actor1.MyWebSocketActor

    //#actor-accept
    import play.api.mvc._
    import play.api.Play.current

    def socket = WebSocket.acceptWithActor[String, String] { request => out =>
      MyWebSocketActor.props(out)
    }
    //#actor-accept
  }

  object Actor1 {

    //#example-actor
    import akka.actor._

    object MyWebSocketActor {
      def props(out: ActorRef) = Props(new MyWebSocketActor(out))
    }

    class MyWebSocketActor(out: ActorRef) extends Actor {
      def receive = {
        case msg: String =>
          out ! ("I received your message: " + msg)
      }
    }
    //#example-actor
  }


}