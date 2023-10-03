package englishlearningapp.englearning.Controller;

import englishlearningapp.englearning.DictionaryPackage.Dictionary;
import englishlearningapp.englearning.DictionaryPackage.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;

public class LookingUpController {
    @FXML
    private TextField textInput;
    @FXML
    private TextArea definitionArea;
    private ListView<String> resultListView = new ListView<>();

    private Dictionary dictionary = new Dictionary();
    public LookingUpController() throws IOException {
    }

    public void clickGame (ActionEvent event) throws IOException {
        SceneController.switchScene(event, SceneController.gameRoot);
    }
    public void clickTranslate (ActionEvent event) throws IOException {
        SceneController.switchScene(event, SceneController.translateRoot);
    }

    public void inputWordHanddle (KeyEvent e) throws SQLException {
        dictionary.getWords();
        // Render prefixes.
        ObservableList<String> wordNames = FXCollections.observableArrayList();
        for (int i = 0; i < dictionary.size(); i++){
            wordNames.add(dictionary.get(i).getName());
        }
        textInput.setOnKeyReleased((KeyEvent event) -> {
            String queryString = textInput.getText().toLowerCase().trim();
            ObservableList<String> filteredList = FXCollections.observableArrayList();

            for(String wordName : wordNames) {
                if(wordName.toLowerCase().startsWith(queryString)){
                    filteredList.add(wordName);
                }
            }
            resultListView.getStyleClass().add("wordListView");
            resultListView.setItems(filteredList);
        });
        SceneController.updateScene(e,"add",resultListView);
        // Render definition and pronunciation of items.
        resultListView.setOnMouseClicked((MouseEvent event) -> {
            if(event.getClickCount() == 1) {
                String wordSelected = resultListView.getSelectionModel().getSelectedItem();
                for(int i = 0; i < dictionary.size(); i++){
                    if(dictionary.get(i).getName().equals(wordSelected)) {
                        definitionArea.setText(dictionary.get(i).getPronunciation() + "\n" + dictionary.get(i).getDefinition() + "\n");
                        break;
                    }
                }

            }
        });
    }

}
