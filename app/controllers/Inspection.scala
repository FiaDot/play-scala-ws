package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.json.JsValue

object Inspection extends Controller {

    case class req_tester(title: String, payload: String)

    object req_tester {
        implicit val fmt = Json.format[req_tester]
    }

    def was = Action(parse.json) {
        request =>
            request.body.validate[req_tester] map {
                title => 
                    println("title=" + title)                   
            }
            Ok("test")     
    }

    // Object 기반 req 파싱, res 생성
    def object_to_json_and_reverse = Action(parse.json) {
        request =>
            Ok("Todo...")
    }

    
    // 일반 json body 에 대해서 파싱 후 응답!
    // 	http://localhost:9000/inspection/was
    // 	{"HeartBeatReq":{"payload":"test"}}
    def parse_json_reqeust = Action(parse.json) {
        request =>

            var HeartBeatReq = (request.body \ "HeartBeatReq")
            var paylaod = (HeartBeatReq \ "payload").as[String]

            Ok(paylaod)
    }


    // 일반 요청
    def general_get_request = Action {
        Ok("ok")
    }

    // POST에 JSON 출력
    def print_post_request = Action(parse.json) {
        request =>
            Ok("Got request [" + request + "] =" + request.body + " " + parse.json.toString())
    }

    // JSON 응답
    def json_response = Action(parse.json) {
        request =>
            Ok(Json.obj("res_code" -> 0, "res_desc" -> "Success"))
    }

    def db = Action(parse.json) {
        request =>
            Ok(Json.obj("res_code" -> 0, "res_desc" -> "Success"))
    }

}