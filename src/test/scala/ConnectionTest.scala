import org.scalatest._

class ConnectionTest extends FlatSpec with Matchers {

  private def getConnector = () => new TarantoolConnector(host, port, username, password)

  val username = "guest"
  val password = ""
  val host = "localhost"
  val port = 3301

  it should "ping connection" in {
    val connector = getConnector()
    connector.ping().apply()
  }

  it should "create space" in {
    val connector = getConnector()
    println(connector.createFirstScheme().apply("testmefully"))
  }

  it should "create tuple" in {
    val connector = getConnector()
    println(connector.addNew().apply(1, "key"))
  }

  it should "select tuple" in {
    val connector = getConnector()
    println(connector.select().apply(1, 0, "key", 0, 1000, 0))
  }
}
