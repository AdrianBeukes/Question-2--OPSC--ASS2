package adrianbeukes.question2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ShowItems extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_items);

        //gets information from MainScreen activity to display on create
        try {
            String Results = getIntent().getStringExtra("results");
            TextView edtResults = (TextView) findViewById(R.id.txtReport);
            edtResults.setText(Results);
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
