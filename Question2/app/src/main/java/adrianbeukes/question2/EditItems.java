package adrianbeukes.question2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditItems extends AppCompatActivity {

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
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_items);

        dbAdapter = new DbAdapter(this);
        nameOfProductEdt = (EditText) findViewById(R.id.edtName);
        descriptionEdt = (EditText) findViewById(R.id.edtDescription);
        priceEdt = (EditText) findViewById(R.id.edtPrice);
        quantityEdt = (EditText) findViewById(R.id.edtQuantity);
        idEdt = (EditText) findViewById(R.id.edtID);
    }

    //**************************************************************************************
    public void editItems(View view)
    {
        try {
            nameOfProduct = nameOfProductEdt.getText().toString();
            description = descriptionEdt.getText().toString();
            //note price and quantity only allows numbers, changed in xml file
            price = priceEdt.getText().toString();
            quantity = quantityEdt.getText().toString();
            id = Long.parseLong(idEdt.getText().toString());

            dbAdapter.open();
            if (dbAdapter.updateItems(id, nameOfProduct, description, price, quantity)) {
                Toast.makeText(this, "Update Successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Update Unsuccessful", Toast.LENGTH_SHORT).show();
            }
            dbAdapter.close();
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //**************************************************************************************
}
