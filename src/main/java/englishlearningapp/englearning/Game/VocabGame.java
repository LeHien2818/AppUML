package englishlearningapp.englearning.Game;

import englishlearningapp.englearning.App;
import englishlearningapp.englearning.Controller.AlertController;
import englishlearningapp.englearning.Controller.SceneController;
import englishlearningapp.englearning.questionGame.GameTimer;
import englishlearningapp.englearning.questionGame.Question_answer_vocab;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;

import javax.sound.sampled.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
import java.util.TimerTask;

public class VocabGame extends Game {
    private Clip clip;
    private GameTimer gameTimer = new GameTimer(10);
    private int score = 0;
    private int quesnumber = 0;
    private Random random = new Random();
    private int randomIndex;
    private TimerTask currentTask;

    public GameTimer getGameTimer() {
        return gameTimer;
    }

    public void setGameTimer(GameTimer gameTimer) {
        this.gameTimer = gameTimer;
    }

    public VocabGame() throws UnsupportedAudioFileException, LineUnavailableException, IOException, SQLException {
    }

    public int getRandom() {
        return this.randomIndex;
    }

    public void setRandom(int size) {
        this.randomIndex = this.random.nextInt(size - 1) + 1;
    }


    public int getQuesNumber() {
        return this.quesnumber;
    }

    public void setQuesNumber(int quesnumber) {
        this.quesnumber = quesnumber;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    Question_answer_vocab questionAnswer = new Question_answer_vocab();


    public void endGame(ActionEvent event, TextArea scoreGame) throws IOException {

        scoreGame.setText(String.valueOf(0));
        SceneController.switchScene(event, SceneController.gameRoot);
        this.setScore(0);
        this.setQuesNumber(0);
        scoreGame.clear();
    }


    @Override
    public void startGame() throws IOException {
    }

    @Override
    public void resetGame() {
    }

    @Override
    public void resetGame(Event event, Button answerA, Button answerB, TextArea questionvocab) throws IOException {
        SceneController.switchScene(event, SceneController.gameRoot);
        this.setScore(0);
        this.setQuesNumber(0);
        if (currentTask != null) {
            currentTask.cancel();
        }
    }

    @Override
    public void resetGame(TextField playerAnswerTextField, Button answerTextArea, TextArea timerNumber, TextField score) {

    }

    @Override
    public void resetGame(TextField playerAnswerTextField, Button answerTextArea, TextArea timerNumber, TextField score, Circle c1) {

    }

    public boolean checkCorrect(TextArea questionVocab, Button answerA) {
        String question = questionVocab.getText();
        String correctAnswer = questionAnswer.getAnswer(question);
        if (answerA.getText().equals(correctAnswer)) {
            return true;
        } else return false;
    }

    public void loadRandomQuestion(TextArea questionVocab, Button answerA, Button answerB, TextArea ScoreGame) throws SQLException {

        ScoreGame.setText(String.valueOf(0));
        this.setRandom(950);
        int index = this.getRandom();
        questionVocab.setText(questionAnswer.getQuestion(index));
        String correctAnswer = questionAnswer.getAnswer(index);
        String answerRandom = questionAnswer.getrandomAnswer(index);

        this.setRandom(950);
        int countAnswer = this.getRandom();

        if (countAnswer % 2 == 1) {
            answerA.setText(correctAnswer);
            answerB.setText(answerRandom);
        } else {
            answerA.setText(answerRandom);
            answerB.setText(correctAnswer);
        }
    }

    @Override
    public void playTimer(ActionEvent event, TextArea textArea, TextArea score) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        final int[] counter = {gameTimer.getCounter()};
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (counter[0] >= 0) {
                    textArea.setText(String.valueOf(counter[0]));
                    counter[0]--;
                } else {
                    Platform.runLater(() -> {
                        try {
                            String point = score.getText();
                            score.setText(String.valueOf(0));
                            setScore(0);
                            AlertController.alertEndGame(event, "YOU LOSE", point);

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    currentTask.cancel();
                    resetGame();
                }
            }
        };
        if (currentTask != null) {
            currentTask.cancel();
        }
        currentTask = timerTask;
        gameTimer.excuteTask(currentTask);
    }

    @Override
    public void playTimer(KeyEvent eventkey, TextArea timerNumber, Circle c1, TextField score, Button botAnswer, TextField playanswer) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

    }

    public void playAudio(String relativeUrl) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioInputStream
                = AudioSystem.getAudioInputStream(App.class.getResource(relativeUrl));
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

    public void stopAudio() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }

}
