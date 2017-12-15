package adrianbeukes.question2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddItems extends AppCompatActivity {

    //declare
    DbAdapter dbAdapter;
    EditText nameOfProductEdt;
    EditText descriptionEdt;
    EditText priceEdt;
    EditText quantityEdt;
    EditText idEdt;

    String nameOfProduct;
    String description;
    String price;
    String quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);

        dbAdapter = new DbAdapter(this);
        nameOfProductEdt = (EditText) findViewById(R.id.edtName);
        descriptionEdt = (EditText) findViewById(R.id.edtDescription);
        priceEdt = (EditText) findViewById(R.id.edtPrice);
        quantityEdt = (EditText) findViewById(R.id.edtQuantity);
        idEdt = (EditText) findViewById(R.id.edtID);
    }

    //*****************************************************************************
    public void addItems(View v)
    {
        try
        {
            nameOfProduct = nameOfProductEdt.getText().toString();
            description = descriptionEdt.getText().toString();
            //note price and quantity only allows numbers, changed in xml file
            price = priceEdt.getText().toString();
            quantity = quantityEdt.getText().toString();

            dbAdapter.open();
            dbAdapter.insertItems(nameOfProduct, description, price, quantity);
            Toast.makeText(this, "Add Successful", Toast.LENGTH_SHORT).show();
            dbAdapter.close();

            //clear fields after words
            nameOfProductEdt.setText("");
            descriptionEdt.setText("");
            priceEdt.setText("");
            quantityEdt.setText("");
        }
        catch (Exception exception)
        {
            Toast.makeText(this, "error=" + exception.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    //*****************************************************************************
}
