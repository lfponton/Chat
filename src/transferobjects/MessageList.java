package transferobjects;

import java.util.ArrayList;

public class MessageList
{
  private ArrayList<Message> messages;
  public MessageList()
  {
    messages = new ArrayList<>();
  }

  public void addMessage(Message message)
  {
    messages.add(message);
  }

  public String toString()
  {
    String str = "";

    for (Message m : messages) {
      str += m.toString() + "\n";
    }
    return str;
  }

  public int size() {
    return messages.size();
  }
}
