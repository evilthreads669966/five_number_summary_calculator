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

import scala.math.floor
import scala.util.Sorting

extension (list: Iterable[Int])
  def toFiveNumberSummary(): Option[FiveNumberSummary] = {
    if list.size == 1 then return None
    val sortedList = Sorting.stableSort(list.toSeq)
    val min = sortedList(0)
    val max = sortedList.last
    if sortedList.size % 2 == 0 then
      val left = ((sortedList.size - 1)/ 2) - 1
      val right = left + 2
      val q2 = (sortedList(left + 1) + sortedList(left + 2)) / 2.0
      var q1 = 0.0
      var q3 = 0.0
      if (sortedList.size / 2) % 2 == 0 then
        q1 = (sortedList(left / 2) + sortedList((left / 2) + 1)) / 2.0
        q3 = (sortedList((right + sortedList.size - 1) / 2) + sortedList((right + sortedList.size) / 2)) / 2.0
      else
        q3 = sortedList((right + sortedList.size - 1) / 2)
        q1 = sortedList((left + 1) / 2)
      Some(FiveNumberSummary(Quartiles(q1,q2,q3), min, max))
    else
      val left = floor((sortedList.size / 2.0) - 1).toInt
      val right = left + 2
      val middle = left + 1
      val q2 = sortedList(middle)
      var q1 = 0.0
      var q3 = 0.0
      if ((sortedList.size - 1) / 2) % 2 == 0 then
        q1 = (sortedList(left / 2) + sortedList(left / 2 + 1)) / 2.0
        q3 = (sortedList((right + sortedList.size - 1) / 2) + sortedList((right + sortedList.size - 1) / 2 + 1)) / 2.0
      else
        q1 = sortedList((left + 1) / 2)
        q3 = sortedList((right + (sortedList.size - 1)) / 2)
      Some(FiveNumberSummary(Quartiles(q1,q2,q3), min, max))
  }