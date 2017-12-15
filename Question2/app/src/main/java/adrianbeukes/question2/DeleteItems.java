package adrianbeukes.question2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteItems extends AppCompatActivity {

    //declare
    DbAdapter dbAdapter;
    EditText idEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_items);

        dbAdapter = new DbAdapter(this);
        idEdt = (EditText) findViewById(R.id.edtItem);
    }

    //************************************************************************************
    public void deleteItems(View view)
    {
        //opens database, search for matching id
        try {
            dbAdapter.open();
            if (dbAdapter.deleteItem(Long.parseLong(idEdt.getText().toString()))) {
                Toast.makeText(this, "Record Deleted", Toast.LENGTH_SHORT).show();
                //clears up for next record
                idEdt.setText("");
            }
            else
            {
                Toast.makeText(this, "Record Doesn't Exist", Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //************************************************************************************
}