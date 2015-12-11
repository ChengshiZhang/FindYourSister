/*
* Game Music "Fun in the Jungle" copyright belongs to Aurie from www.greatgamemusic.com
* It is authorized to be used in commercial and non-commercial games.
*/

package teampao.findyoursister;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.media.MediaPlayer;


/* names
0. Professor Densmore
1. Angelina Jolie
2. Brad Pitt
3. Johnny Depp
4. Nicolas Cage
5. Scarlett Johansson
6. Taylor Swift
7. Tom Cruise
8. Will Smith
9. Bill Gates
10. Mark Zuckerberg
11. Steve Jobs
12. The Rock
13. Justin Bieber
14. Paul Walker
15. Bryant
16. Messi
17. Beckham
18. Lee
*/



public class MainActivity extends AppCompatActivity implements gameActivity.OnFinishListener{


    ImageButton playAgain;
    ImageView gameTitle;
    ImageView scoreBackground;
    TextView scoreDisplay;
    TextView highScore;
    MediaPlayer applause;
    MediaPlayer funIntheJungle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playAgain = (ImageButton)findViewById(R.id.playAgain);
        gameTitle = (ImageView) findViewById(R.id.gameTitle);
        scoreBackground = (ImageView) findViewById(R.id.scoreBackground);
        scoreDisplay = (TextView) findViewById(R.id.finalScore);
        highScore = (TextView) findViewById(R.id.highScore);
        final ImageButton startButton = (ImageButton)findViewById(R.id.startButton);
        applause = MediaPlayer.create(this,R.raw.applause);

        //start and loop the background music
        funIntheJungle = MediaPlayer.create(this,R.raw.funinthejungle);
        funIntheJungle.start();
        funIntheJungle.setLooping(true);



        //makes the playAgain button and the endActivity fragment invisible in the start screen
        playAgain.setVisibility(View.GONE);
        scoreBackground.setVisibility(View.GONE);
        scoreDisplay.setVisibility(View.GONE);
        highScore.setVisibility(View.GONE);

        //endActivity fragment is replaced with gameActivity when the start button is pressed
        startButton.setOnClickListener(

                new ImageButton.OnClickListener() {

                    public void onClick(View v) {


                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        gameActivity game = new gameActivity();
                        fragmentTransaction.replace(R.id.fragment_container, game);
                        fragmentTransaction.commit();
                        startButton.setVisibility(View.GONE);
                        gameTitle.setVisibility(View.GONE);
                    }

                }


        );

        //endActivity fragment is replaced with gameActivity when the playAgain button is pressed
        playAgain.setOnClickListener(

                new ImageButton.OnClickListener() {

                    public void onClick(View v) {


                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        gameActivity game = new gameActivity();
                        fragmentTransaction.replace(R.id.fragment_container, game);
                        fragmentTransaction.commit();
                        playAgain.setVisibility(View.GONE);
                        gameTitle.setVisibility(View.GONE);
                    }

                }


        );

        //This will set the high score to 0 every time the app is opened
        /*try {
            String filename = "find_your_sister_high_score";
            FileOutputStream fileOutputStream = openFileOutput(filename, MODE_PRIVATE);
            fileOutputStream.write(("" + 0).getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    //gameActivity fragment will be replaced with endActivity when the timer runs out
    //passes the current score from gameActivity to endActivity
    @Override
    public void finishScreen(int finalScore){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        endActivity endScreen = new endActivity();
        fragmentTransaction.replace(R.id.fragment_container, endScreen);
        fragmentTransaction.commit();
        endScreen.setScore(finalScore);
        endScreen.invisible(false);
        playAgain.setVisibility(View.VISIBLE);
        gameTitle.setVisibility(View.VISIBLE);
        applause.start();
    }

    //stops the music when user exits the app
    @Override
    protected void onPause(){
        super.onPause();
        applause.release();
        funIntheJungle.release();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
