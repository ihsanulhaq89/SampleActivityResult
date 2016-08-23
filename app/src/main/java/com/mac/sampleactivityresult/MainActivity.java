package com.mac.sampleactivityresult;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int MY_REQUEST_CODE = 99; // Activity Request code
    public static final String MY_RESULT_DATA = "MY_RESULT_DATA"; // Result data key
    private TextView message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        message = (TextView) findViewById(R.id.message);
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
            startSecondActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, MY_REQUEST_CODE);
        // Animating the transition
        overridePendingTransition(R.anim.abc_fade_in, R.anim.slide_up);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == MY_REQUEST_CODE) {
            // Make sure the request was successful
            if (resultCode == Activity.RESULT_OK) {
                if(data != null && data.getExtras() != null){
                    Bundle bundle = data.getExtras();
                    MyResult result = (MyResult) bundle.get(MY_RESULT_DATA);
                    message.setText(result.getMessage());
                }
            }
        }
    }
}
