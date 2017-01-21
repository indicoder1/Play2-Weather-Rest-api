package controllers

import javax.inject.Inject

import play.api.mvc.{Action, Controller}
import service.WeatherService
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Sarthak on 14-01-2017.
  */
class HelloWorld @Inject()(weatherService: WeatherService)extends Controller {
  def helloWorld(name: String) = Action{
    Ok("Hello "+name)
  }

  def getWeatherData(cityName:String) = Action.async {
    weatherService.getDataForCity(cityName).map{
      result=>Ok(result)
    }
  }
}
