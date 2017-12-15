package adrianbeukes.question2;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SearchItems extends AppCompatActivity {

    //declare
    DbAdapter dbAdapter;
    EditText idEdt;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_items);

        //calls adapter
        dbAdapter = new DbAdapter(this);
        //assign value from activity
        idEdt = (EditText) findViewById(R.id.edtSearch);
    }

    //**********************************************************************************
    public void SearchItems (View view)
    {
        try {
            id = Long.parseLong(idEdt.getText().toString());

            Toast.makeText(this, idEdt.getText().toString(), Toast.LENGTH_SHORT).show();
            dbAdapter.open();

            Cursor getItemCursor = dbAdapter.getItem(id);

            //check if item id exists, if then return results
            if (getItemCursor.getCount() > 0) {
                displayItem(getItemCursor);
                //clear textView
                idEdt.setText("");
            } else {
                Toast.makeText(this, "This item doesn't exist", Toast.LENGTH_SHORT).show();
            }
            dbAdapter.close();
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void displayItem(Cursor curItem)
    {
        //toast out results of search
        Toast.makeText(this, "Results - " + curItem, Toast.LENGTH_SHORT).show();
    }

    //**********************************************************************************
}
