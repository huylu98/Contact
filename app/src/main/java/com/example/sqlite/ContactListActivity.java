package com.example.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ContactListActivity extends AppCompatActivity {
    final String[] from = new String[]{DatabaseHelper._ID, DatabaseHelper.NAME, DatabaseHelper.PHONE};

    final int[] to = new int[]{R.id.id, R.id.name, R.id.phone};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_emp_list);

        DBManager dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.Fetch();

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView nameTextView = (TextView) view.findViewById(R.id.name);
                TextView phoneTextView = (TextView) view.findViewById(R.id.phone);

                String id = idTextView.getText().toString();
                String name = nameTextView.getText().toString();
                String phone = phoneTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModifyContactActivity.class);
                modify_intent.putExtra("name", name);
                modify_intent.putExtra("phone", phone);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
            }

        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {

            Intent add_mem = new Intent(this, AddContactActivity.class);
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }
}
