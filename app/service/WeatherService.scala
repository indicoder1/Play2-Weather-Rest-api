package service

import javax.inject.Inject

import play.api.libs.json.JsValue
import play.api.libs.ws.{WSClient, WSResponse}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by Sarthak on 15-01-2017.
  */
class WeatherService @Inject()(ws:WSClient) {
  def getDataForCity(cityName:String): Future[JsValue] = {
    val url = s"http://api.openweathermap.org/data/2.5/weather?q=$cityName&APPID={YOUR_API_KEY}"
    val request = ws.url(url)
    request.get().map{
      (wsResponse:WSResponse)=>wsResponse.json
    }
  }
}
