package sample;

import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GSpeechDuplex;
import com.darkprograms.speech.recognizer.GSpeechResponseListener;
import com.darkprograms.speech.recognizer.GoogleResponse;
import com.darkprograms.speech.translator.GoogleTranslate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import net.sourceforge.javaflacencoder.FLACFileWriter;

import java.io.IOException;

public class Controller {
    @FXML
    TextArea  textCapturedArea ;
    @FXML
    Button record  ;

    @FXML
    Button stop;



    Microphone mic= new Microphone(FLACFileWriter.FLAC);
    GSpeechDuplex duplex = new GSpeechDuplex("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");

    public void recognition(){

        duplex.setLanguage("en");
        textCapturedArea.setWrapText(true);
        textCapturedArea.setEditable(false);
        textCapturedArea.setPromptText("Bonjour .. Comment je peux vous aidez ? ");
       new Thread(() -> {
            try {
                duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }).start();

        stop.setDisable(false);
        record.setDisable(true);


        duplex.addResponseListener(new GSpeechResponseListener() {
            String old_text = "";
            public void onResponse(GoogleResponse gr) {
                String output = "";
                output = gr.getResponse();

                textCapturedArea.setText("");
                textCapturedArea.appendText(this.old_text);
                textCapturedArea.appendText("Le text en anglais :"+output +"\n \n");
                try {
                    textCapturedArea.appendText("Le text en francais :"+GoogleTranslate.translate("fr", output));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void stop(){
        mic.close();
        duplex.stopSpeechRecognition();
        record.setDisable(false);
        stop.setDisable(true);

    }

}
