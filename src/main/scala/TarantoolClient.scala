import java.io.IOException
import java.net.InetSocketAddress
import java.nio.channels.SocketChannel

import org.tarantool.{SocketChannelProvider, TarantoolClientConfig, TarantoolClientImpl}

class TarantoolClient(host: String, port: Int) extends SocketChannelProvider {

  override def get(retryNumber: Int, lastError: Throwable): SocketChannel = {
    if (lastError != null) lastError.printStackTrace(System.out)
    try
      SocketChannel.open(new InetSocketAddress(host, port))
    catch {
      case e: IOException => throw new IllegalStateException(e)
    }
  }

}
