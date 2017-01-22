package controllers

import javax.inject.Inject

import com.google.gson.Gson
import play.api.mvc.{Action, Controller}
import service.WeatherService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Sarthak on 14-01-2017.
  */
class WeatherController @Inject()(weatherService: WeatherService) extends Controller {
  val gson = new Gson()
  def getWeatherData(cityName:String) = Action.async {
    weatherService.getDataForCity(cityName).map{
      result=>Ok(gson.toJson(result))
    }
  }
}
