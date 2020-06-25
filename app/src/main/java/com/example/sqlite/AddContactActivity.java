package com.example.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddContactActivity extends Activity implements OnClickListener {
    private EditText nameEditText;
    private EditText phoneEditText;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Add Contact");

        setContentView(R.layout.activity_add_record);

        nameEditText = (EditText) findViewById(R.id.name_edittext);
        phoneEditText = (EditText) findViewById(R.id.phone_edittext);

        Button addTodoBtn = (Button) findViewById(R.id.add_record);

        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_record) {
            final String name = nameEditText.getText().toString();
            final String desc = phoneEditText.getText().toString();

            dbManager.Insert(name, desc);

            Intent main = new Intent(AddContactActivity.this, ContactListActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(main);
        }
    }

}
