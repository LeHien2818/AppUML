package englishlearningapp.englearning.Controller;

import englishlearningapp.englearning.Game.Game;
import englishlearningapp.englearning.questionGame.BotAnswerGenerator;
import englishlearningapp.englearning.questionGame.GameTimer;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.scene.shape.Circle;
import javafx.util.Duration;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;


public class ConnectController extends Game {

    private ConnectGame connectGame = new ConnectGame();

    @FXML
    private Circle c1;
    @FXML
    private TextArea timerNumber;
    @FXML
    private TextArea answerTextArea;

    private final GameTimer gmt;
    {
        try {
            gmt = new GameTimer(8);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    private TimerTask currentTask;

    @FXML
    private TextField playerAnswerTextField;
    private static final List<String> EnteredWord = new ArrayList<>();
    private static boolean checkEnterWord(String word) {
        for (int i = 0; i < EnteredWord.size() - 1; i++) {
            if (EnteredWord.get(i).equals(word)) return false;
        }
        return true;
    }

    @Override
    public  void startGame()  {

        playerAnswerTextField.setText("");
        String starWord = BotAnswerGenerator.generateRandomBotAnswers();
        answerTextArea.setText(starWord + "\n");
        EnteredWord.add(starWord);

        playerAnswerTextField.setOnKeyPressed(event -> {
            try {

                handleGame(event);
                if (event.getCode() == KeyCode.ENTER) {
                    playTimer(event);
                }
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {

                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void handleGame(KeyEvent event) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        if (event.getCode() == KeyCode.ENTER) {
            String playerAnswer = playerAnswerTextField.getText();
            if (checkEnterWord(playerAnswer) && BotAnswerGenerator.checkPlayerWord(playerAnswer)) {
                EnteredWord.add(playerAnswer);
                System.out.println(EnteredWord);
                char keyword = playerAnswer.charAt(playerAnswer.length() - 1);
                String botAnswer = BotAnswerGenerator.getWordStartingWith(keyword);
                while (!checkEnterWord(botAnswer)) {
                    botAnswer = BotAnswerGenerator.getWordStartingWith(keyword);

                    if (botAnswer == null || botAnswer.isEmpty()) {
                        break;
                    }
                }
                if (botAnswer != null && !botAnswer.isEmpty()) {
                    answerTextArea.setText(botAnswer);
                    //playerAnswerTextField.setText(botAnswer.charAt(0) + "");
                    EnteredWord.add(botAnswer);
                    playTimer(event);

                } else if (botAnswer == null) {
                    resetGame();
                    AlertController.alertEndGame(event, "You Win");
                }
                playerAnswerTextField.clear();
            } else {
                ActionEvent eventAlert = new ActionEvent();
                AlertController.alertWrong(eventAlert, "The word was entered previously");
                playerAnswerTextField.clear();
            }
        }
    }



    public void setTimerNumber(String timerNumber) {
        this.timerNumber.setText(timerNumber);
    }
    @Override
    public void playTimer(KeyEvent eventkey) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        gmt.stopAudio();
        final int[] counter = {8};
        gmt.playAudio();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (counter[0] >= 0) {
                    timerNumber.setText(String.valueOf(counter[0]));
                    counter[0]--;

                } else {
                    Platform.runLater(() -> {
                        try {
                            resetGame();
                            AlertController.alertEndGame(eventkey,"YOU LOSE");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    gmt.getTimer().cancel();
                }

            }

        };

        if (currentTask != null) {
            currentTask.cancel();
        }
        currentTask = timerTask;
        gmt.excuteTask(currentTask);
        setRotate(c1, false, 360, 8);

    }

    @Override
    public void playTimer(ActionEvent eventkey) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

    }

    @Override
    public void playTimer(ActionEvent event, TextArea textArea) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

    }

    public static void setRotate(Circle c, boolean reverse, int angle, int duration) {
        RotateTransition rt = new RotateTransition(Duration.seconds(duration), c);
        rt.setAutoReverse(reverse);
        rt.setByAngle(angle);
        rt.setDelay(Duration.millis(0));
        rt.setRate(duration);
        rt.setCycleCount(duration + 1);
        rt.play();
    }
    @Override
    public void resetGame() {
        answerTextArea.clear();
        playerAnswerTextField.clear();
        EnteredWord.clear();
        timerNumber.clear();
        gmt.stopAudio();
    }

    @Override
    public void resetGame(Event event) throws IOException {

    }

    @Override
    public void handleGame() {

    }

    public void clickExitConnect(ActionEvent event) throws IOException {
        resetGame();
        AlertController.alertExit(event);
    }

//    public void alertStartGame() throws IOException {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Thông báo");
//        alert.setHeaderText(null);
//        alert.setContentText("Introduction");
//        ButtonType buttonTypeYes = new ButtonType("OKE", ButtonBar.ButtonData.YES);
//        alert.getButtonTypes().setAll(buttonTypeYes);
//        Optional<ButtonType> result = alert.showAndWait();
//
//        if (result.isPresent() && result.get() == buttonTypeYes) {
//            alert.close(); startGame();
//        }
//
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startGame();
    }
}