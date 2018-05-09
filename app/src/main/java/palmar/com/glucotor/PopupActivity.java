package palmar.com.glucotor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Toast;


/**
 * Created by Nicolas on 1/14/2018.
 */

public class PopupActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        AlertDialog alertDialog = new AlertDialog.Builder(PopupActivity.this).create();
        alertDialog.setTitle("Alert Dialog");
        alertDialog.setMessage("Welcome to dear user.");
        alertDialog.setIcon(R.drawable.sample_0);

        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });

        alertDialog.show();

        Button dismissbutton = (Button) findViewById(R.id.closBtn);
        dismissbutton.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
            PopupActivity.this.finish();
        }
    });
    }
}
