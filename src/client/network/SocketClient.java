package client.network;

import transferobjects.Request;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class SocketClient implements Client
{
  private PropertyChangeSupport support;

  public SocketClient() {
    support = new PropertyChangeSupport(this);
  }

  @Override public void startClient()
  {
    try
    {
      Socket socket = new Socket("localhost", 1234);
      ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
      ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());

      new Thread(() -> listenToServer(outToServer, inFromServer)).start();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  private void listenToServer(ObjectOutputStream outToServer, ObjectInputStream inFromServer)
  {
    try
    {
      outToServer.writeObject(new Request("Listener", null));
      while (true) {
        Request request = (Request) inFromServer.readObject();
        support.firePropertyChange(request.getType(), null, request.getArgument());
      }
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  private Request request(String arg, String type)
      throws IOException, ClassNotFoundException
  {
    Socket socket = new Socket("localhost", 1234);
    ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
    ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());
    outToServer.writeObject(new Request(type, arg));
    return (Request) inFromServer.readObject();
  }


  @Override public String sendMessage(String str)
  {
    try
    {
      Request response = request(str, "SendMessage");
      return (String) response.getArgument();
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
    return str;
  }

  @Override public List<String > getMessages()
  {
    try
    {
      Request response = request(null, "SendMessage");
      return (List<String>) response.getArgument();
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

}
