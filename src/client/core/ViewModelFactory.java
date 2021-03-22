package client.core;

import client.views.chat.ChatViewModel;

public class ViewModelFactory
{
  private ChatViewModel chatViewModel;

  public ViewModelFactory(ModelFactory modelFactory)
  {
    chatViewModel = new ChatViewModel(modelFactory.getDataModel());
  }

  public ChatViewModel getChatViewModel() {
    return chatViewModel;
  }
}
