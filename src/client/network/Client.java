package client.network;

import transferobjects.Message;
import transferobjects.MessageList;
import util.PropertyChangeSubject;

import java.util.List;

public interface Client extends PropertyChangeSubject
{
  String sendMessage(String str);
  List<String> getMessages();
  void startClient();

}
