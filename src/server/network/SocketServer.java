package server.network;

import server.model.MessageSender;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer
{
  private MessageSender messageSender;

  public SocketServer(MessageSender messageSender)
  {
    this.messageSender = messageSender;
  }

  public void startServer() {
    try
    {
      ServerSocket server = new ServerSocket(1234);
      System.out.println("Server started.");
      while (true) {
        Socket socket = server.accept();
        System.out.println("Client connected.");
        new Thread(new SocketHandler(socket, messageSender)).start();
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
