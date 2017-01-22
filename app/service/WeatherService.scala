package service

import javax.inject.Inject

import play.api.libs.ws.{WSClient, WSResponse}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
  * Created by Sarthak on 15-01-2017.
  */
case class WeatherData(temperature:Double,skyCondition:String,humidityInPercent:Double,cityName:String)

class WeatherService @Inject()(ws:WSClient) {

  implicit val weatherResponseReader: Reads[WeatherData] = (
    (__ \ "main" \ "temp").read[Double] ~
    ((__ \ "weather")(0) \ "description").read[String] ~
    (__ \ "main" \ "humidity").read[Double] ~
    (__ \ "name").read[String]
  ) (WeatherData.apply _)

  def getDataForCity(cityName:String): Future[WeatherData] = {
    val url = s"http://api.openweathermap.org/data/2.5/weather?q=$cityName&APPID={YOUR_API_KEY}"
    val request = ws.url(url)
    request.get().map{
      (wsResponse:WSResponse)=>Json.parse(wsResponse.json.toString()).as[WeatherData]
    }
  }
}
