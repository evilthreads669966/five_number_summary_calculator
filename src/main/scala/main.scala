/*
Copyright 2023 Chris Basinger

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

import scala.io.StdIn.readLine
import scala.util.control.Breaks.{break, breakable}

@main
def main(): Unit = {
  while true
  do
    breakable{
      println("Enter a set of numbers separated by commas")
      val numbers = readLine()
      if numbers == null || numbers.trim.isEmpty then break
      val parts = numbers.split(",")
      val list = parts.map(_.trim.toInt)
      list.toList.toFiveNumberSummary() match
        case Some(summary) => {
          println(s"Q1 = ${summary.quartiles.q1}\nQ2 = ${summary.quartiles.q2}\nQ3 = ${summary.quartiles.q3}")
          println(s"MIN = ${summary.min}\nMAX = ${summary.max}")
        }
        case None => println("Please enter more than one number")
    }
}

