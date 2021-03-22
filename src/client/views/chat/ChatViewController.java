package client.views.chat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import transferobjects.Message;

public class ChatViewController
{
  @FXML private TextField messageField;
  @FXML private TextArea messagesArea;
  private ChatViewModel viewModel;
  public void init(ChatViewModel viewModel)
  {
    this.viewModel = viewModel;

    messageField.textProperty().bindBidirectional(viewModel.getMessage());
    messagesArea.textProperty().bind(viewModel.getMessages());

  }

  public void sendMessageButton(ActionEvent evt)
  {
    viewModel.sendMessage();
  }
}
