package englishlearningapp.englearning.Controller;
import englishlearningapp.englearning.Game.VocabGame;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import englishlearningapp.englearning.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class AlertController {

    public static void alertSubmit(ActionEvent event, String text, double Point) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(text);
        ButtonType buttonTypeYes = new ButtonType("YES", ButtonBar.ButtonData.YES);

        // Tạo nút NO
        ButtonType buttonTypeNo = new ButtonType("NO", ButtonBar.ButtonData.NO);


        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeYes) {
            AlertPoint(event, Point);
        } else {
            alert.close();
        }

    }

    public static void AlertPoint(ActionEvent event, double point) throws IOException {
        Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
        newAlert.setTitle("Thông báo");
        newAlert.setHeaderText(null);
        newAlert.setContentText("Your Score: " + point);
        ButtonType buttonTypeYes = new ButtonType("OKE", ButtonBar.ButtonData.YES);

        newAlert.getButtonTypes().setAll(buttonTypeYes);
        Optional<ButtonType> result = newAlert.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeYes) {
            SceneController.switchScene(event, SceneController.gameRoot);
        }
    }

    public static void CustomAlert(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Từ bạn thêm vào đã tồn tại");
        ButtonType buttonTypeYes = new ButtonType("Thử lại", ButtonBar.ButtonData.YES);

    }

    public static void CustomAlert () throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Từ bạn thêm vào đã tồn tại");
        ButtonType buttonTypeYes = new ButtonType("Thử lại", ButtonBar.ButtonData.YES);

    }

    public static void alertExit(ActionEvent event, Button answerA, Button answerB, TextArea questionVocab, TextArea scoregame, TextArea timerbox, Button handleGame) throws IOException, UnsupportedAudioFileException, SQLException, LineUnavailableException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Do you want exit");
        ButtonType buttonTypeYes = new ButtonType("YES", ButtonBar.ButtonData.YES);

        alert.getButtonTypes().setAll(buttonTypeYes);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeYes) {

            VocabGame vocabGame = new VocabGame();
            vocabGame.resetGame(event,answerA,answerB,questionVocab,scoregame,timerbox,handleGame);
            SceneController.switchScene(event, SceneController.gameRoot);
        } else {
            alert.close();
        }

    }

    public static void alertWrong(ActionEvent event, String text) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(text);
        ButtonType buttonTypeYes = new ButtonType("OKE", ButtonBar.ButtonData.YES);

        alert.getButtonTypes().setAll(buttonTypeYes);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeYes) {
            alert.close();
        }
    }


    public static void alertEndGame(KeyEvent eventkey, String text, String point) throws IOException {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(text + point);
        ButtonType buttonTypeYes = new ButtonType("OKE", ButtonBar.ButtonData.YES);
        alert.getButtonTypes().setAll(buttonTypeYes);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeYes) {
            alert.close();
            SceneController.switchScene(eventkey, SceneController.gameRoot);
        }
    }
    public static void alertEndGame(ActionEvent eventkey, String text,String point) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(text + point);
        ButtonType buttonTypeYes = new ButtonType("OKE", ButtonBar.ButtonData.YES);
        alert.getButtonTypes().setAll(buttonTypeYes);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeYes) {
            SceneController.switchScene(eventkey, SceneController.gameRoot);
        }

    }

    public static void showCustomPopUp(String fxmlFile, String styleClass) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("Views/" + fxmlFile));
        DialogPane customDialog = loader.load();
        customDialog.getStylesheets().add(App.class.getResource("src/Style.css").toString());
        customDialog.getStyleClass().add(styleClass);
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(customDialog);
        Optional<ButtonType> clickedButton = dialog.showAndWait();
    }

    public static void alertExit(ActionEvent event, TextField playerAnswerTextField, Button answerTextArea, TextArea timerNumber, TextField score, Circle c1) throws IOException, UnsupportedAudioFileException, SQLException, LineUnavailableException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Do you want exit");
        ButtonType buttonTypeYes = new ButtonType("YES", ButtonBar.ButtonData.YES);
        alert.getButtonTypes().setAll(buttonTypeYes);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeYes) {

            SceneController.switchScene(event, SceneController.gameRoot);
        }
    }
}