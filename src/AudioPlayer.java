import javazoom.jl.player.Player;

import java.net.URL;
import java.net.URLConnection;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.List;

public class AudioPlayer {
    private TextToSpeech speaker;
    private File aufile;

    public AudioPlayer() {
        try {
            speaker = new TextToSpeech("kevin16");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DownloadAudio(String word) { // Connect to an URL and get the audio
        aufile = new File("rsc/audio/" + word.toLowerCase().trim() + ".mp3");
        if (aufile.exists()) {
            return;
        }
        try {
            if (word.trim().contains(" ")) {
                String[] w = word.trim().split(" ");
                word = new String(w[0]);
                for (int i = 1; i < w.length; ++i) {
                    word += "%20" + w[i].trim();
                }
            }
            String url = "https://translate.google.com.vn/translate_tts?ie=UTF-8&q=\""
                       + word + "\"&tl=en&client=tw-ob";
            URL u = new URL(url);
            URLConnection conn = u.openConnection();
            InputStream is = conn.getInputStream();

            OutputStream outstream = new FileOutputStream(aufile);
            byte[] buffer = new byte[4096];
            int len;
            while ((len = is.read(buffer)) > 0) {
                outstream.write(buffer, 0, len);
            }
            outstream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void PlayAudio(String word) { // play audio already stored
        try {
            String filepath = "rsc/audio/" + word.toLowerCase().trim() + ".mp3";
            FileInputStream au = new FileInputStream(filepath);
            Player playMP3 = new Player(au);
            playMP3.play();
            playMP3.close();
        } catch (Exception e) {
            DownloadAudio(word);
            if (aufile.exists()) {
                PlayAudio(word);
                DeleteAudio(word);
            } else {
                try {
                    speaker.speak(word);
                } catch (Exception e2) {
       
                }
            }
        }
    }

    public void DeleteAudio(String word) {
        aufile = new File("rsc/audio/" + word.toLowerCase().trim() + ".mp3");
        if (aufile.exists()) {
            try {
                aufile.delete();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
