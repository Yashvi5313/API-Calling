package com.example.apicalling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.widget.Toast;

import com.example.apicalling.Adapter.RetrofitAdapter;
import com.example.apicalling.Pojo.Contact;
import com.example.apicalling.Pojo.Root;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView superRecyclerView;
    RetrofitAdapter retrofitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        superRecyclerView = findViewById(R.id.superRecyclerView);
        getContact();
    }

    private void getContact() {
        Call<Root> call = RetrofitClient.getInstance().getMyApi().getcontact();
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Root root = new Root();

                root = response.body();

                List<Contact> contactList = new ArrayList<>();
                contactList = root.getContacts();


//                String[] namelist = new String[contactList.size()];
//
//                for (int i = 0; i < contactList.size(); i++) {
//                    namelist[i] = contactList.get(i).getName();
//                }

                retrofitAdapter = new RetrofitAdapter(MainActivity.this, contactList);
                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                superRecyclerView.setAdapter(retrofitAdapter);
                superRecyclerView.setLayoutManager(layoutManager);
                //new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, namelist));
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}