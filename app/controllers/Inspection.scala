package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.json.JsValue
import play.api.libs.functional.syntax._

object Inspection extends Controller {

    case class req_tester(payload: String)
   
    object req_tester {
        implicit val fmt = Json.format[req_tester]
    }
    
    // case class res_dto(success: Boolean, error_code: Option[Int], error_code_desc: Option[String], token: Option[Long])    
    case class res_dto(success: Boolean, error_code: Option[Int])
    //case class res_dto(success: Boolean)

    
    // 성공
    /*
    implicit val res_dto_writes = new Writes[res_dto] {    			
    	def writes(res: res_dto) = Json.obj(    			
    		"success" -> res.success,    		
    		"error_code" -> res.error_code
    	)    	
    } 
    */
    implicit val res_dto_writes: Writes[res_dto] = (
    	(JsPath \ "success").write[Boolean] and
    	(JsPath \ "error_code").writeNullable[Int]     	
    )(unlift(res_dto.unapply)) 
    
    /*
    	def reads(json: JsValue = res_dto(
    		(json \ "success").as[Boolean]
    	)
      */  
    

	def was = Action(parse.json) {
		request =>

			var HeartBeatReq = (request.body \ "HeartBeatReq")
			var payload = (HeartBeatReq \ "payload").as[String]
		
			// 정상 동작
			// Ok(Json.obj("success" -> true, "payload" -> payload))			
			
			// 정상!
			// var res = res_dto(true, Some(2))
			// 정상!			
			//var res = res_dto(true, error_code=Option[Int](2))
			
			// 정상!
			var res = res_dto(true, Option[Int](2))
			
			// TODO : 더 간단한 방법은??
			
			
			// error!
/*			
[error] D:\PlayFramework\typesafe-activator-1.2.1\activator-1.2.1\play-scala-ws\app\controllers\Inspection.scala:53: type mismatch;
[error]  found   : Int(2)
[error]  required: Option[Int]
[error]                         var res = res_dto(true, 2)
[error]                                                 ^
[error] one error found
[error] (compile:compile) Compilation failed
			var res = res_dto(true, 2)
*/			
			Ok(Json.toJson(res))
			
			
		/*
            request.body.validate[req_tester] map {
                title => 
                    println("title=" + title)                   
            }
            Ok("test")
            */
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