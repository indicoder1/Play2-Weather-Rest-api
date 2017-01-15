package controllers

import play.api.mvc.{Action, Controller}

/**
  * Created by Sarthak on 14-01-2017.
  */
class HelloWorld extends Controller {
  def helloWorld(name: String) = Action{
    Ok("Hello "+name)
  }
}
