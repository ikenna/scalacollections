import java.io.File
import scala.io.Source

object SampleConversions {
  implicit def file2RichFile(from: File) = new RichFile(from)

}


class RichFile(val from:File){
  def read = Source.fromFile(from).getLines().toList
}
