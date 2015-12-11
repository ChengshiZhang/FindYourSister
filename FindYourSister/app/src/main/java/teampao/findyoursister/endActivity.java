package teampao.findyoursister;

import android.app.Fragment;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class endActivity extends Fragment {

    private View view;
    boolean invisible = false;
    int s;
    int high = 0;
    String filename = "find_your_sister_high_score";
    TextView scoreDisplay;
    TextView highScore;
    ImageView scoreBackground;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_end, container, false);

        scoreDisplay = (TextView) view.findViewById(R.id.finalScore);
        highScore = (TextView) view.findViewById(R.id.highScore);
        scoreBackground = (ImageView) view.findViewById(R.id.scoreBackground);
        scoreDisplay.setText("" + s);

        //reads the savefile which stores the high score
        //only API 23 or higher is targeted. getContext function cannot be used in the previous versions
        try{
            String Message;
            FileInputStream fileInputStream = getContext().openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while((Message = bufferedReader.readLine())!=null){
                stringBuffer.append(Message + "\n");
            }
            high = Integer.parseInt(stringBuffer.toString().trim());
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

        //compares the high score to the current score and store the current score if it is higher than the previous high score
        if(high < s) {
            try {
                FileOutputStream fileOutputStream = getContext().openFileOutput(filename, getContext().MODE_PRIVATE);
                fileOutputStream.write(("" + s).getBytes());
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            highScore.setText("" + s);
        }else{
            highScore.setText("" + high);
        }

        //makes the fragments invisible if needed(in the start screen)
        if(invisible) {
            scoreBackground.setVisibility(View.GONE);
            scoreDisplay.setVisibility(View.GONE);
            highScore.setVisibility(View.GONE);
        }else{
            scoreBackground.setVisibility(View.VISIBLE);
            scoreDisplay.setVisibility(View.VISIBLE);
            highScore.setVisibility(View.VISIBLE);
        }

        return view;
    }

    //receives the current score from MainActivity
    public void setScore(int finalScore){

        s = finalScore;

    }

    //makes the fragment invisible if needed
    public void invisible(boolean inv){
        if(inv) {
            invisible = true;
        }else{
            invisible = false;
        }
    }

}