package teampao.findyoursister;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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



public class gameActivity extends Fragment {


    int score = 0;
    int secondsRemaining = 60;

    //each celebrity is represented with a number between 0 - 18
    //findValue is the current target celebrity to find
    //current0 - current11 are the 12 celebrities displayed on the screen
    int findValue = 0;
    int current0 = 0;
    int current1 = 1;
    int current2 = 2;
    int current3 = 3;
    int current4 = 4;
    int current5 = 5;
    int current6 = 6;
    int current7 = 7;
    int current8 = 8;
    int current9 = 9;
    int current10 = 10;
    int current11 = 11;

    boolean exist = true;
    boolean countdown = false;

    private View view;
    OnFinishListener onFinishListener;
    MediaPlayer rightChoice;
    MediaPlayer wrongChocie;
    MediaPlayer tickingClock;
    CounterClass timer;

    //the names of the image files in drawable folder
    final String[] nameArray = {"Professor Densmore", "Angelina Jolie", "Brad Pitt", "Johnny Depp", "Nicolas Cage", "Scarlett Johansson", "Taylor Swift", "Tom Cruise", "Will Smith", "Bill Gates", "Mark Zuckerberg", "Steve Jobs", "\"The Rock\"", "Justin Bieber", "Paul Walker", "Kobe Bryant", "Lionel Messi", "David Beckham", "Bruce Lee"};
    final String[] Densmore = {"densmore1", "densmore2", "densmore3", "densmore4", "densmore5", "densmore6", "densmore7", "densmore8", "densmore9", "densmore10", "densmore11", "densmore12",};
    final String[] Jolie = {"jolie1", "jolie2", "jolie3", "jolie4"};
    final String[] Pitt = {"pitt1", "pitt2", "pitt3", "pitt4", "pitt5"};
    final String[] Depp = {"depp1", "depp2", "depp3", "depp4"};
    final String[] Cage = {"cage1", "cage2", "cage3", "cage4", "cage5"};
    final String[] Johansson = {"johansson1", "johansson2", "johansson3", "johansson4", "johansson5"};
    final String[] Swift = {"swift1", "swift2", "swift3", "swift4"};
    final String[] Cruise = {"cruise1", "cruise2", "cruise3", "cruise4", "cruise5"};
    final String[] Smith = {"smith1", "smith2", "smith3", "smith4", "smith5"};
    final String[] Gates = {"gate1", "gate2", "gate3", "gate4", "gate5", "gate6", "gate7"};
    final String[] Zuckerberg = {"zuckerberg1", "zuckerberg2", "zuckerberg3", "zuckerberg4", "zuckerberg5", "zuckerberg6", "zuckerberg7"};
    final String[] Jobs = {"jobs1", "jobs2", "jobs3", "jobs4", "jobs5", "jobs6", "jobs7"};
    final String[] Johnson = {"johnson1", "johnson2", "johnson3", "johnson4", "johnson5"};
    final String[] Bieber = {"bieber1", "bieber2", "bieber3", "bieber4", "bieber5"};
    final String[] Walker = {"walker1", "walker2", "walker3", "walker4", "walker5"};
    final String[] Bryant = {"bryant1", "bryant2", "bryant3", "bryant4"};
    final String[] Messi = {"messi1", "messi2", "messi3", "messi4"};
    final String[] Beckham = {"beckham1", "beckham2", "beckham3"};
    final String[] Lee = {"lee1", "lee2", "lee3", "lee4"};

    final String[][] imageArray = {Densmore, Jolie, Pitt, Depp, Cage, Johansson, Swift, Cruise, Smith, Gates, Zuckerberg, Jobs, Johnson, Bieber, Walker, Bryant, Messi, Beckham, Lee};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.fragment_game, container, false);

        //only API 23 or higher is targeted. getContext function cannot be used in the previous versions
        rightChoice = MediaPlayer.create(this.getContext(), R.raw.rightchoice);
        wrongChocie = MediaPlayer.create(this.getContext(), R.raw.wrongchoice);
        tickingClock = MediaPlayer.create(this.getContext(), R.raw.tickingclock);

        //cat1 - cat12 are the 12 ImageButtons displayed on the screen
        final ImageButton cat1 = (ImageButton) view.findViewById(R.id.cat1);
        final ImageButton cat2 = (ImageButton) view.findViewById(R.id.cat2);
        final ImageButton cat3 = (ImageButton) view.findViewById(R.id.cat3);
        final ImageButton cat4 = (ImageButton) view.findViewById(R.id.cat4);
        final ImageButton cat5 = (ImageButton) view.findViewById(R.id.cat5);
        final ImageButton cat6 = (ImageButton) view.findViewById(R.id.cat6);
        final ImageButton cat7 = (ImageButton) view.findViewById(R.id.cat7);
        final ImageButton cat8 = (ImageButton) view.findViewById(R.id.cat8);
        final ImageButton cat9 = (ImageButton) view.findViewById(R.id.cat9);
        final ImageButton cat10 = (ImageButton) view.findViewById(R.id.cat10);
        final ImageButton cat11 = (ImageButton) view.findViewById(R.id.cat11);
        final ImageButton cat12 = (ImageButton) view.findViewById(R.id.cat12);

        final TextView textViewTime = (TextView) view.findViewById(R.id.time);

        textViewTime.setText("01:00");

        //initializes a timer with 60 seconds and ticks every 1 second
        timer = new CounterClass(60000, 1000);


        //every time a button is pressed, catAction is performed and the corresponding current0 - current11 is changed along with the change of image
        //detailed explanations can be found at the catAction() and display() functions
        cat1.setOnClickListener(
                new ImageButton.OnClickListener() {

                    public void onClick(View v) {

                        current0 = catAction(cat1, current0, timer);
                        display();


                    }

                }
        );

        cat2.setOnClickListener(
                new ImageButton.OnClickListener() {


                    public void onClick(View v) {

                        current1 = catAction(cat2, current1, timer);
                        display();

                    }

                }
        );

        cat3.setOnClickListener(


                new ImageButton.OnClickListener() {


                    public void onClick(View v) {

                        current2 = catAction(cat3, current2, timer);
                        display();


                    }

                }
        );

        cat4.setOnClickListener(
                new ImageButton.OnClickListener() {

                    public void onClick(View v) {

                        current3 = catAction(cat4, current3, timer);
                        display();

                    }

                }
        );

        cat5.setOnClickListener(
                new ImageButton.OnClickListener() {

                    public void onClick(View v) {

                        current4 = catAction(cat5, current4, timer);
                        display();

                    }

                }
        );

        cat6.setOnClickListener(
                new ImageButton.OnClickListener() {

                    public void onClick(View v) {

                        current5 = catAction(cat6, current5, timer);
                        display();

                    }

                }
        );

        cat7.setOnClickListener(
                new ImageButton.OnClickListener() {

                    public void onClick(View v) {

                        current6 = catAction(cat7, current6, timer);
                        display();

                    }

                }
        );

        cat8.setOnClickListener(
                new ImageButton.OnClickListener() {

                    public void onClick(View v) {

                        current7 = catAction(cat8, current7, timer);
                        display();

                    }

                }
        );

        cat9.setOnClickListener(
                new ImageButton.OnClickListener() {

                    public void onClick(View v) {

                        current8 = catAction(cat9, current8, timer);
                        display();
                    }

                }
        );

        cat10.setOnClickListener(
                new ImageButton.OnClickListener() {

                    public void onClick(View v) {

                        current9 = catAction(cat10, current9, timer);
                        display();

                    }

                }
        );


        cat11.setOnClickListener(
                new ImageButton.OnClickListener() {

                    public void onClick(View v) {

                        current10 = catAction(cat11, current10, timer);
                        display();

                    }

                }
        );


        cat12.setOnClickListener(
                new ImageButton.OnClickListener() {

                    public void onClick(View v) {

                        current11 = catAction(cat12, current11, timer);
                        display();

                    }

                }
        );

        return view;
    }

    /*
        First time a button is pressed, it starts the timer.
        Every timer a button is pressed, it checks if the user pressed the right button
        by comparing findValue and the corresponding current0 - current11 value
        and tells the scoreCounter if the user made the right choice or not.
        Then, it randomly generates the next celebrity to find(not duplicate with the previous celebrity in the same button)
        and changes the background of the ImageButton to the new celebrity's picture
        then returns the value of the new celebrity
     */
    public int catAction(ImageButton cat, int current, CounterClass timer) {

        if (!countdown) {
            timer.start();
            countdown = true;
        }

        if (findValue == current) {
            scoreCounter(true);
            rightChoice.start();

        } else {
            scoreCounter(false);
            wrongChocie.start();
        }

        Random rand = new Random();

        int rndInt = rand.nextInt(imageArray.length);
        while (rndInt == current) {
            rndInt = rand.nextInt(imageArray.length);
        }
        int rndInt2 = rand.nextInt(imageArray[rndInt].length);
        int resID = getResources().getIdentifier(imageArray[rndInt][rndInt2], "drawable", "teampao.findyoursister");

        //OutOfMemory error often occurs in the emulator and older versions of android phones
        //this try-catch block prevents the app from quiting when encounters the OutOfMemory error
        //However, the background of the ImageButton remains the same
        //Therefore, when the picture does not change after the button is pressed, it must be the OutOfMemory error
        try{
            cat.setBackgroundResource(resID);
        }catch (OutOfMemoryError e) {
            e.printStackTrace();
            System.out.print("Out of Memory Error, please check phone settings.\n");
            return current;
        }
        return rndInt;
    }

    //countdown timer
    public class CounterClass extends CountDownTimer {

        TextView textViewTime = (TextView) view.findViewById(R.id.time);

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);

        }

        //for every tick, the displayed timer changes to the current remaining time
        //when there's only 5 seconds remaining, the ticking clock sound effect starts playing
        @Override
        public void onTick(long millisUntilFinished) {

            long millis = millisUntilFinished;
            String ms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millis), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(ms);
            textViewTime.setText(ms);
            if(secondsRemaining==5){
                tickingClock.start();
            }
            secondsRemaining--;
        }

        //when the time runs out, the timer calls the finishScreen function which passes the score to MainActivity and replaces the gameActivity fragment with endActivity fragment
        @Override
        public void onFinish() {

            textViewTime.setText("Time's up");
            onFinishListener.finishScreen(score);

        }

    }

    //to be implemented in the MainActivity in order to switch the fragment
    public interface OnFinishListener{

        public void finishScreen(int finalScore);

    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            onFinishListener = (OnFinishListener)activity;
        }catch(Exception e){}



    }

    //counts and displays the current score
    public void scoreCounter(boolean Correct) {

        TextView scorevalue = (TextView) view.findViewById(R.id.scorevalue);


        if (Correct) {
            score++;
            scorevalue.setText(score + "");


        } else {
            if (score > 0) {
                score--;
                scorevalue.setText(score + "");
            }
        }

    }

    //finds the next target for the user to find and displays the name on the screen
    public void display() {

        TextView findname = (TextView) view.findViewById(R.id.findname);

        Random rand = new Random();

        while (exist) {

            findValue = rand.nextInt(imageArray.length);

            if (findValue == current0) {
                exist = false;
            } else if (findValue == current1) {
                exist = false;
            } else if (findValue == current2) {
                exist = false;
            } else if (findValue == current3) {
                exist = false;
            } else if (findValue == current4) {
                exist = false;
            } else if (findValue == current5) {
                exist = false;
            } else if (findValue == current6) {
                exist = false;
            } else if (findValue == current7) {
                exist = false;
            } else if (findValue == current8) {
                exist = false;
            } else if (findValue == current9) {
                exist = false;
            } else if (findValue == current10) {
                exist = false;
            } else if (findValue == current11) {
                exist = false;
            } else {

            }


        }
        exist = true;

        findname.setText(nameArray[findValue]);


        System.out.print("current0: ");
        System.out.println(current0);
        System.out.print("current1: ");
        System.out.println(current1);
        System.out.print("current2: ");
        System.out.println(current2);
        System.out.print("current3: ");
        System.out.println(current3);
        System.out.print("current4: ");
        System.out.println(current4);
        System.out.print("current5: ");
        System.out.println(current5);
        System.out.print("current6: ");
        System.out.println(current6);
        System.out.print("current7: ");
        System.out.println(current7);
        System.out.print("current8: ");
        System.out.println(current8);
        System.out.print("current9: ");
        System.out.println(current9);
        System.out.print("current10: ");
        System.out.println(current10);
        System.out.print("current11: ");
        System.out.println(current11);
        System.out.print("findValue: ");
        System.out.println(findValue);
    }
}