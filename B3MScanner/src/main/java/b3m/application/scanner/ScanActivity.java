package b3m.application.scanner;

/**
 * Created by florent on 11/10/13.
 */

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import b3m.application.scanner.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class ScanActivity extends Activity {

    private View succesView;
    private View dejaRetireView;
    private View nonPayeView;
    private View invalideView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scan);
        succesView = findViewById(R.id.succes_view);
        dejaRetireView = findViewById(R.id.deja_scanne);
        nonPayeView = findViewById(R.id.non_paye);
        invalideView = findViewById(R.id.invalide);

        findViewById(R.id.scan_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askScan();
            }
        });
    }

    protected void askScan() {
        try {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_FORMATS", "CODE_128");
            intent.putExtra("RESULT_DISPLAY_DURATION_MS", 0L);
            startActivityForResult(intent, 1);
        } catch (ActivityNotFoundException anfe) {
            Log.e("onCreate", "Scanner Not Found", anfe);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");

                if (contents.startsWith("1110")) {
                    succesView.setVisibility(View.VISIBLE);
                }
            }
            // else continue with any other code you need in the method
        }
    }

}
