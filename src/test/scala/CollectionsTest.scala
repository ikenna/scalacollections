import java.io.File
import java.util
import java.util.Scanner
import org.scalatest.FunSuite
import scala.collection.immutable.BitSet
import scala.collection.mutable.ArrayBuffer
import scala.collection.{mutable, SortedSet, SortedMap}
import scala.io.Source

class CollectionsTest extends FunSuite {

  test("Iterable") {
    val iterable: Iterable[(Int, Int)] = SortedMap(3 -> 4, 1 -> 2)
    val iterator: Iterator[(Int, Int)] = iterable.iterator
    while (iterator.hasNext) {
      println(iterator.next())
    }
  }

  test("Is a Seq mutable?") {
    val aSeq = Seq(3, 4, 1, 2)
    println(aSeq ++ (Seq(2)))
    println(aSeq)
  }

  test("Immutable List") {
    val aList: List[Int] = List(1, 2, 3)
    val result = aList :+ 4
    println(result)
    println(aList)
    println(6 :: 7 :: aList)
    println(7 :: 4 :: 9 :: Nil)

    println(aList :+ 5)
    println(5 +: aList)


  }

  test("Range") {
    val range = new Range(0, 9, 2)
    println(range.toList)
  }

  test("Priority Queue") {
    val queue = new mutable.PriorityQueue[Int]()
    queue += 4
    queue += 2
    queue += 5
    println(queue)
  }

  test("List recursion") {
    val aList: List[Int] = List(1, 2, 3)
    println(aList.last)
    def sum(list: List[Int]): Int = if (list == Nil) 0 else list.head + sum(list.tail)
    def sum2(list: List[Int]): Int = list match {
      case Nil => 0
      case head :: tail => head + sum(tail)
    }
    println(sum(aList))
    println(sum2(aList))
    println(aList.sum)
  }

  test("Mutable List") {
    val list = mutable.LinkedList(1, 2, 3)
    println(list.next)
    println(list.next.next)
    println(list.next.elem = 5)
    println(list.next)
    println(list.next.next.next)
    println(list.next.next.next.next)


  }

  test("Sets") {
    val aSet = Set(1, 2, 3, 1)
    println(aSet(4))

    val set = mutable.LinkedHashSet(1, 2, 3, 4, 5, 6)
    //    set.foreach(x => {print(x); print(" "); println(x.hashCode())})

    println(set &~ aSet)
    println()
    val lhset = mutable.LinkedHashSet("ha", "ba", "you")
    println(lhset)
    println(lhset + "sho")
    println(lhset +("sho", "bo"))


    val bitSet = BitSet(4, 5, 7)
    println(bitSet)
  }

  test("Maps") {
    val myMap = Map(1 -> 2, 3 -> 4)
    println(myMap)
    println(myMap - 1)
    println(myMap - 3)
  }


  test("ArrayBuffer") {
    val buffer = ArrayBuffer(1, 2, 5, 3, 9, 3)
    println(buffer(4))
    buffer += 10
    println(buffer)
  }

  test("Adding ArrayBuffers") {
    val buffer = ArrayBuffer(1, 2, 5, 3, 9, 3)
    val buffer2 = ArrayBuffer(1, 2, 5, 3, 9, 3)
    println(buffer ++= buffer2)
  }


  test("Adding lists together") {
    val list = List(1, 2, 5, 3, 9, 3)
    val list2 = List(11, 345, 35, 37, 88)
    println(list ::: list2)
  }

  test("Appending a tuple to a Buffer") {
    val aBuffer = ArrayBuffer(2, 3)
    val aBuffer2 = ArrayBuffer(2, 3, 4, 5)
    println(aBuffer2 --= aBuffer)
  }

  test("List common methods") {
    val list = List[Int](1, 2, 3, 4)
    //    println(list.map(f => f * 2))
    //    println(list.foreach(f => f * 2))
    //    println(list.flatMap(f => List(f * 2)))
    //    println(list.collect{case x if (x % 2 == 0) => x + " found"})
    println {
      "Reduce left = " + list.reduceLeft((x, y) => x + y)
    }


    println {
      "Fold left = " + list.foldLeft(0) {
        (x, y) => {
          x + y
        }
      }
    }

    println {
      "Fold left 2 = " + list./:(0) {
        (x, y) => x + y
      }
    }
    println {
      "Scan left = " + list.scanLeft(0) {
        (x, y) => {
          x + y
        }
      }
    }
    //    println(list.scanLeft(0)((x, y) => {println((x, y)); x + y}))

    //    println{ list.reduceRight((x, y) => x - y) }
    //    println{"Fold left" + list.foldLeft(5)((x, y) =>  x + y) }
    //    println{"Fold " + list.fold(5)((x, y) => { x + y}) }
    //    println{"Aggregate  " + list.aggregate(1)(
    //      (x, y) => {println((x, y));x + y},
    //      (x,y) => {println((x, y));x - y}
    //      )
    //    }
    //    println(list.count(_ % 2 == 0 ))
    //    println(list.span(_ % 1 == 0 ))
    //    println(list.takeWhile(_ % 1 == 0 ))
    //    println(list.dropWhile(_ % 1 == 0 ))
    //    println(list.take(3))
    //    println(list.drop(3))
    //    println(list.takeRight(3))
    //    println(list.dropRight(3))
    //    println(list.slice(3, 4))

  }

  test("Other common methods") {
    val list1 = List[Int](1, 2, 3, 4, 5, 6, 7, 8)
    val list2 = List[Int](5, 6, 7, 8, 9)

    //    "Ikenna".zipWithIndex.foreach(x => println(x))

    println("Sliding")
    for (x <- list1.sliding(3)) {
      println(x)
    }


    println("Grouped")
    for (x <- list1.grouped(3)) {
      println(x)
    }
  }

  /**
   * You can turn a collection into a string using mkString
   */
  test("mkString and addString") {
    val list1 = List[Int](1, 2, 3, 4, 5, 6, 7, 8)
    println("mkString: " + list1.mkString(","))
    println("addString: " + list1.addString(new StringBuilder("hello! ")))

    /**
     * Output:
     * mkString: 1,2,3,4,5,6,7,8
     * addString: hello! 12345678
     */
  }


  /**
   * Collection conversion
   */
  test("Collection conversion") {
    val list1 = List[Int](1, 2, 3, 4, 5, 6, 7, 8)
    println("toIterable: " + list1.toIterable)
    println("toSeq: " + list1.toSeq)
    println("toIndexedSeq: " + list1.toIndexedSeq)
    println("toArray: " + list1.toArray)
    println("toList: " + list1.toList)
    println("toStream: " + list1.toStream)
    println("toSet: " + list1.toSet)
    //    println("toMap: " + list1.toMap)

    /**
     * Output:
     * toIterable: 1,2,3,4,5,6,7,8
     */
  }


  /**
   * You can copy elements to an array
   */
  test("Copy to an array") {
    val list1 = List[Int](1, 2, 3, 4, 5, 6, 7, 8)
    val arr = new Array[Int](10)
    println("Array starts out empty: " + arr.mkString(","))
    list1.copyToArray(arr)
    println("List is copied to array: " + arr.mkString(","))
  }


  /**
   * You can copy elements to an array buffer
   */
  test("Copy to an array buffer") {
    val list1 = List[Int](1, 2, 3, 4, 5, 6, 7, 8)
    val arr = new ArrayBuffer[Int](10)
    println("ArrayBuffer starts out empty: " + arr.toString)
    list1.copyToBuffer(arr)
    println("List is copied to array: " + arr.toString)
  }

  /**
   *
   */
  test("Seq methods") {
    val list1: Seq[Int] = List[Int](1, 2, 3)
    for (x <- list1.combinations(2)) println(x)

  }


  test("Print list") {
    val list = List[Int](1, 2, 3, 4)
    def printme(aList: List[Int]) {
      if (aList.length == 1) println(aList.head)
      else {
        println(aList.head);
        printme(aList.tail)
      }
    }
    printme(list)
  }


  test("A foreach loop") {
    val list = List[Int](1, 2, 3, 4)
    val listAsString = new mutable.ArrayBuffer[String]()
    for (l <- list) {
      listAsString.+=(l.toString + " hi ")
    }

    val result2 = list.foldLeft(List[String]())((x: List[String], y: Int) => (y.toString + " hi ") :: x)

    println(listAsString)
    println(result2)
  }

  test("Stream") {

    def myList(a: Stream[Int]): Stream[Int] = Stream(a.head, a.head + 1)

    println(myList(Stream(1)))

  }

  test("Source") {
    println(new File(".").getAbsolutePath);
    for (l <- Source.fromFile("src/test/me.txt").getLines.toStream) println(l)

  }

  test("Lazy views") {
    import Math.pow
    val powers = (0 until Int.MaxValue).view.map(pow(2, _))
    val powers2 = powers.map(_ * 2)

    println(powers2(2))

    println(powers(2))
  }


  test("Importing Java collections") {
    import scala.collection.JavaConversions.propertiesAsScalaMap
    val props: mutable.Map[String, String] = System.getProperties
    println(props)
  }

  test("Synchronized map") {
    val map = new mutable.HashMap[String, String] with scala.collection.mutable.SynchronizedMap[String, String]
    println(map)
  }

  test("Parallel Collections") {

    def isPrime(n: Int): Boolean = {
      for (i <- 2 until n) {
        if (n % i == 0) {
          println(Thread.currentThread().getName)
          return false;
        }
      }
      return true;
    }


    val myList = (1 to 1000).toList

    val start = System.currentTimeMillis()

    println("Starting now ")

    myList.par.map(x => isPrime(x))
    //    myList.par.map(x => println(x))

    val end = System.currentTimeMillis()

    println("Ending now ")
    println("Took " + (end - start) / 1000.0 + " secs")

  }


  test("Constructing maps") {
    //    val myMap = Map("hi" -> 1, "hello" -> 2)
    //    val myMap2 = mutable.Map("hi" -> 1, "hello" -> 2)
    //    val myMap3 = mutable.Map[Int, Int]()
    //    myMap3(1)=2
    //    myMap3+=(5 -> 6)
    //    myMap2("hoo")=2
    //
    //    val newMap = myMap + ("boo" -> 6)
    //
    //    val x = newMap - "boo"
    //
    //    println(myMap, myMap2, myMap3, newMap, x)
    //
    //    val soreted = SortedMap(2 -> 1, 9 -> 1, 3 -> 4)
    //
    //
    //    println(soreted)
    //
    //    import scala.collection.JavaConversions.mapAsScalaMap
    //    val map:scala.collection.mutable.Map[Int, Int] = new util.TreeMap[Int, Int]()
    //    println(map)
    //
    //    val y = (1 , 2)
    //
    //    val (first, second) = y
    //    println(first)
    //
    //    println("Hello".partition(_.isUpper))

    val result = Map[String, Double]("iPhone" -> 100, "Galaxy" -> 200).foldLeft(Map[String, Double]()) {
      (myMap, nextTuple) => {
        val (key, value) = nextTuple
        myMap + (key -> value * 0.9)
      }
    }
    //    println(result)

    val result2 = Map("iPhone" -> 100, "Galaxy" -> 200).map {
      x => (x._1, x._2 * 0.9)
    }
    //    println(result2)

    val result3 = Map("iPhone" -> 100, "Galaxy" -> 200).collect {
      case (k, v) => (k, v * 0.9)
    }
    println(result3)

  }

  test("Read text from file") {
    //    val source = Source.fromFile("src/test/me.txt")
    val scanner: Scanner = new java.util.Scanner(new java.io.File("src/test/me.txt"))


    println(func(scanner, Map[String, Int]()))



    def func(s: Scanner, myMap: Map[String, Int]): Map[String, Int] = {
      if (!scanner.hasNext()) myMap
      else {
        val value = scanner.next()
        func(s, myMap + (value -> (myMap.getOrElse(value, 0) + 1)))
      }
    }

    val result2 = SortedMap[String, Int]() ++
      Source.fromFile("src/test/me.txt")
        .getLines()
        .toList
        .groupBy(x => x)
        .collect {
        case (key, value) => (key, value.size)
      }


    def minmax(values: Array[Int]): (Int, Int) = (values.min -> values.max)

    println(minmax(Array(1, 2, 3, 5, 8, 9)))

  }

  test("Indexes") {

    def indexes(aString: String) = {
      aString.zipWithIndex.groupBy(x => x._1).collect {
        case (key, value) => (key, value.map(y => y._2))
      }.collect {
        case (key, vector) => (key, SortedSet[Int]() ++ vector)
      }
    }

    println(indexes("Mississippi"))
  }

  test("Removes zeroes") {

    def removeZeros(list: mutable.LinkedList[Int]): mutable.LinkedList[Int] = {
      list.filter(x => x != 0)
    }

    println(removeZeros(mutable.LinkedList(1, 0, 4, 5, 0, 0, 4)))

  }

  test("Mapping") {

    def nums(array: Array[String], map: Map[String, Int]): Array[Int] = {
      map.collect {
        case (key, value) if array.contains(key) => value
      }.toArray
    }

    println(nums(Array("Tom", "Fred", "Harry"), Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5)).mkString(","))
  }


  test("List 2") {

    val list = 1 :: 2 :: 3 :: 4 :: Nil;

    val result = (list :\ List[Int]()) {
      (x, y) => y :+ x
    }

    println(result)
  }

  //////////////////////// Implicit Conversions

  test("Implicit conversionsn to Fraction") {
    implicit def int2Fraction(n: Int) = new Fraction(n, 1)

    println(3 + new Fraction(2, 3))
  }

  test("Rich conversion") {
    //    import SampleConversions._

    val contents = SampleConversions.file2RichFile(new File("src/test/me.txt")).read
    println(contents)
  }

  test("Implicit parameters") {

    def quote(thisQuote: String)(implicit thatQuote: String) = thisQuote + thatQuote
    implicit val ha: String = "haaa"
    println(quote("boo"))

    def smaller[T](a:T, b:T)(implicit order:T => Ordered[T]) = {
      if(a < b) a else b
    }

    class MyOdering(val x:Int) extends Ordered[Int]{
      override def compare(that: Int )= x + that
    }

    implicit def myFunc(x:Int) = new MyOdering(x)

    println(smaller(40, 3))

  }


}


case class Fraction(val x: Int, val y: Int) {

  def +(that: Fraction): Fraction = {
    new Fraction(x + that.x, y + that.y)
  }
}

class Pair[T:Ordering](val first:T, val second: T){
  def smaller(implicit ord: Ordering[T]) = if(ord.compare(first, second) < 0 ) first else second
}

