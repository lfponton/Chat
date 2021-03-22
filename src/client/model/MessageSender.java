package client.model;

import transferobjects.Message;
import util.PropertyChangeSubject;

import java.util.List;

public interface MessageSender extends PropertyChangeSubject
{
  String sendMessage(String message);
  List<String> getMessages();
}
