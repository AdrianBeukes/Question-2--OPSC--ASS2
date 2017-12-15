package adrianbeukes.question2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainScreen extends AppCompatActivity {

    //declare
    DbAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        dbAdapter = new DbAdapter(this);
    }

    //**********************************************************************************
    public void AddItems(View view)
    {
        Intent i = new Intent(this, AddItems.class);
        startActivity(i);
    }

    //**********************************************************************************
    public void EditItems(View view)
    {
        Intent i = new Intent(this, EditItems.class);
        startActivity(i);
    }
    //**********************************************************************************
    public void DeleteItems(View view)
    {
        Intent i = new Intent(this, DeleteItems.class);
        startActivity(i);
    }

    //**********************************************************************************
    public void SearchItems(View view)
    {
        Intent i = new Intent(this, SearchItems.class);
        startActivity(i);
    }

    //**********************************************************************************
    public void ShowItems(View view)
    {
        //opens new activity
        //sends read information to new activity to display on create
        try {
            String allItems = getItemsFromDB();
            Intent i = new Intent(this, ShowItems.class);
            i.putExtra("results", allItems);
            startActivity(i);
        }
        catch (Exception exception){
            Toast.makeText(this, "error=" + exception.getMessage(),Toast.LENGTH_LONG).show();
    }
    }

    //**********************************************************************************
    public String getItemsFromDB()
    {
        //sends all data to report activity
        dbAdapter.open();
        Cursor dbCursor = dbAdapter.getAllItems();
        String allItems = "";

        if(dbCursor.moveToFirst())
        {
            do
            {
                {
                    allItems += "_id: " + dbCursor.getInt(0) +
                            " productName : " + dbCursor.getString(1) +
                            " description : " +dbCursor.getString(2) +
                            " price : " +  dbCursor.getString(3) +
                            " quantity: " + dbCursor.getString(4) + "\n\n";
                }
            }
            while (dbCursor.moveToNext());
        }

        dbAdapter.close();
        return allItems;
    }

    //**********************************************************************************
    public void aboutClick(View view)
    {
        Intent i = new Intent(this, About.class);
        startActivity(i);
    }

    //**********************************************************************************
}
