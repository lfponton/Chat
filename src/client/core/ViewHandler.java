package client.core;

import client.views.chat.ChatViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
  private Stage stage;
  private Scene chatScene;
  private ViewModelFactory viewModelFactory;

  public ViewHandler(Stage stage, ViewModelFactory viewModelFactory)
  {
    this.stage = stage;
    this.viewModelFactory = viewModelFactory;
  }

  public void start() throws IOException
  {
    openView("Chat");
  }

  private void openView(String viewToOpen) throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    Parent root = null;

    loader.setLocation(getClass().getResource("../views/chat/" + viewToOpen + "View.fxml"));
    root = loader.load();
    if ("Chat".equals(viewToOpen)) {
      ChatViewController controller = loader.getController();
      controller.init(viewModelFactory.getChatViewModel());
      stage.setTitle("Chat");
    }
    chatScene = new Scene(root);
    stage.setScene(chatScene);
    stage.show();

  }
}
