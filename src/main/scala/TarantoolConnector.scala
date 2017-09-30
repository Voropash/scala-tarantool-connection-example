import java.util

import org.tarantool.{TarantoolClientConfig, TarantoolClientImpl}

import scala.collection.Iterable
import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer

class TarantoolConnector(host: String, port: Int, username: String, password: String) {

  val config = new TarantoolClientConfig
  config.username = username
  config.password = password

  val client = new TarantoolClientImpl(new TarantoolClient(host, port), config)

  private def convertToJavaList: (Any) => java.util.List[Any] = (obj: Any) => ListBuffer(List(obj): _*)


  def ping(): () => Unit = () => client.syncOps().ping()

  /** insert at base library */
  def addNew(): (Int, Any) => util.List[_] =
    (spaceId: Int, value: Any) => client.syncOps().insert(spaceId, convertToJavaList(value))

  def select(): (Int, Int, Any, Int, Int, Int) => util.List[_] =
    (spaceId: Int, indexId: Int, key: Any, offset: Int, limit: Int, iterator: Int) =>
      client.syncOps().select(spaceId, indexId, convertToJavaList(key), offset, limit, iterator)

  def createFirstScheme(): (String) => Iterable[Any] = (name: String) =>
    client.syncOps().eval(s"box.schema.space.create('$name', {id = 1})")
}
