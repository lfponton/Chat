package client.views.chat;

import client.model.MessageSender;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;

public class ChatViewModel
{
  private MessageSender messageSender;
  private StringProperty message, messages;

  public ChatViewModel(MessageSender messageSender)
  {
    this.messageSender = messageSender;
    messages = new SimpleStringProperty();
    message = new SimpleStringProperty();
    messageSender.addPropertyChangeListener("NewMessage", this::onNewMessage);
  }

  private void onNewMessage(PropertyChangeEvent evt)
  {
    messages.setValue((String) evt.getNewValue());
    System.out.println(messages);
  }

  public void sendMessage()
  {
    String input = message.get();
    System.out.println(input);
    messageSender.sendMessage(input);
  }

  public StringProperty getMessages()
  {
    return messages;
  }

  public StringProperty getMessage()
  {
    return message;
  }
}
