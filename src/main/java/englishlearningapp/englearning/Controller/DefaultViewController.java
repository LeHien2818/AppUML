package englishlearningapp.englearning.Controller;


import animatefx.animation.BounceIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class DefaultViewController {
    @FXML
    public Button searchBtn;
    public Button gameBtn;
    public Button translateBtn;

    public DefaultViewController() throws IOException {
    }
    @FXML
    public void clickSearch (ActionEvent event) throws IOException {
        new BounceIn(searchBtn).play();
        SceneController.switchScene(event, SceneController.searchRoot);
    }
    public void clickGame (ActionEvent event) throws IOException{
        new BounceIn(gameBtn).play();
        SceneController.switchScene(event, SceneController.gameRoot);
    }
    public void clickTranslate (ActionEvent event) throws IOException {
        new BounceIn(translateBtn).play();
        SceneController.switchScene(event, SceneController.translateRoot);
    }


}
