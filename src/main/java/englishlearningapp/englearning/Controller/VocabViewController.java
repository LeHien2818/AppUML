//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package englishlearningapp.englearning.Controller;

import englishlearningapp.englearning.App;
import englishlearningapp.englearning.Game.VocabGame;
import englishlearningapp.englearning.questionGame.Question_answer_vocab;
import java.io.IOException;
import java.sql.SQLException;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class VocabViewController {

    @FXML
    private MediaView leftMedia, rightMedia;
    private Media media;
    private MediaPlayer mediaPlayer;
    @FXML
    private TextArea timerbox;
    @FXML
    private TextArea questionVocab;
    @FXML
    private Button answerA;
    @FXML
    private Button answerB;
   // private int score = 0;
    @FXML
    private TextArea Scoregame = new TextArea();
    private VocabGame vocabGame = new VocabGame();

    public VocabViewController() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
    }

    public void setTextScore(String s) {
        this.Scoregame.setText(s);
    }

    public int getScore() {
        return vocabGame.getScore();
    }
    public void initialize() throws SQLException {
        Thread th = new Thread(new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                vocabGame.loadRandomQuestion(questionVocab, answerA, answerB);
                return null;
            }
        });
        th.setDaemon(true);
        th.start();

    }
    public void clickAnswer(ActionEvent event) throws IOException, UnsupportedAudioFileException, LineUnavailableException, SQLException {
        vocabGame.playTimer(event, timerbox);
        this.setTextScore(String.valueOf(vocabGame.getScore()));
        int questionnumber = vocabGame.getQuesNumber();
        int scoretmp = vocabGame.getScore();
        if (questionnumber <= 10) {
            Question_answer_vocab questionAnswer = new Question_answer_vocab();
            vocabGame.setRandom(10);
            int index = vocabGame.getRandom();
            this.questionVocab.setText(questionAnswer.getQuestion(index));
            vocabGame.setRandom(100);
            int countAnswer = vocabGame.getRandom();
            String correctAnswer = questionAnswer.getAnswer(index);
            String selectedAnswer = "";
            if (countAnswer %2 == 1) {
                this.answerA.setText(correctAnswer);
                this.answerB.setText(questionAnswer.getrandomAnswer());
            } else {
                this.answerA.setText(questionAnswer.getrandomAnswer());
                this.answerB.setText(correctAnswer);
            }

            if (event.getSource() == this.answerA) {
                selectedAnswer = this.answerA.getText();
            } else if (event.getSource() == this.answerB) {
                selectedAnswer = this.answerB.getText();
            }

            if (selectedAnswer.equals(correctAnswer)) {
                init(App.class.getResource("src/media/congratulate.mp4").toString());
                playMedia();
                ++scoretmp;
                vocabGame.setScore(scoretmp);
            } else {
                init(App.class.getResource("src/media/wrong.mp4").toString());
                playMedia();
            }

            SceneController.switchSceneNormal(event, SceneController.vocabRoot);
            this.setTextScore(String.valueOf(scoretmp));
            ++questionnumber;
            vocabGame.setQuesNumber(questionnumber);
        } else {
            String s = "vocab";
            vocabGame.endGame(event, s, Scoregame);
            setTextScore("");
        }

    }

    public void onExit(ActionEvent event) throws IOException {
        String s = "vocab";
        vocabGame.resetGame(event);
        Scoregame.clear();
        vocabGame.setScore(0);
        setTextScore("");
        timerbox.clear();
    }

    public void init(String file) {
        media = new Media(file);
        mediaPlayer = new MediaPlayer(media);
        leftMedia.setMediaPlayer(mediaPlayer);
    }
    public void playMedia() {
        mediaPlayer.play();
    }
}
