package server.network;

import server.model.MessageSender;
import transferobjects.Request;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketHandler implements Runnable
{
  private Socket socket;
  private MessageSender messageSender;

  private ObjectOutputStream outToClient;
  private ObjectInputStream inFromClient;

  public SocketHandler(Socket socket, MessageSender messageSender)
  {
    this.socket = socket;
    this.messageSender = messageSender;

    try
    {
      outToClient = new ObjectOutputStream(socket.getOutputStream());
      inFromClient = new ObjectInputStream(socket.getInputStream());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void run()
  {
    try
    {
      System.out.println("Server handler accepting requests.");
      Request request = (Request) inFromClient.readObject();
      System.out.println(request.getType());
      System.out.println(request.getArgument());
      if("Listener".equals(request.getType())) {
        messageSender.addPropertyChangeListener("NewMessage", this::onNewMessage);
      }
      else if("SendMessage".equals(request.getType())) {
        String result = messageSender.sendMessage((String) request.getArgument());
        outToClient.writeObject(new Request(("NewMessage"), result));
      }
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  private void onNewMessage(PropertyChangeEvent evt)
  {
    try
    {
      outToClient.writeObject(new Request(evt.getPropertyName(), evt.getNewValue()));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
