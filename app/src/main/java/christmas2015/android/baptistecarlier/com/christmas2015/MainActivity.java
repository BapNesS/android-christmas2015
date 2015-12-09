package christmas2015.android.baptistecarlier.com.christmas2015;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    public final static String TAG = "Christmas2015";

    private Circle myCircle;

    private Button button;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launch();
            }
        });

        tv = (TextView) findViewById(R.id.textView);
    }

    private void log(String message) {
        Log.d(TAG, message);
    }

    private void launch() {
        log("launch()");
        myCircle = new Circle();
        log("gen");
        myCircle.generate(50);
        log("gen finish");
        if ( myCircle.testFinalLoop() ) {
            log("launch()");
            tv.setText("Generation OK.");
            send();
        } else {
            tv.setText("Generation failed.");
        }
    }

    private void send() {
        SmsManager smsManager = SmsManager.getDefault();
        for ( People p : this.myCircle.getEverybody() ) {
            smsManager.sendTextMessage(p.getPhoneNumber(), null, "Cette année "+p.getName()+", tu dois offrir un cadeau à... "+p.getOfferTo().getName()+"!", null, null);
        }
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
