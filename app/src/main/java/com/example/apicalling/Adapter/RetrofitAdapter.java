package com.example.apicalling.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apicalling.MainActivity;
import com.example.apicalling.Pojo.Contact;
import com.example.apicalling.R;

import java.util.Collections;
import java.util.List;

public class RetrofitAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Contact> contactList;

    public RetrofitAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.contact_list, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MyHolder myHolder = (MyHolder) holder;
        Contact current = contactList.get(position);
        myHolder.textId.setText(current.getId());
        myHolder.textName.setText(current.getName());
        myHolder.textEmail.setText(current.getEmail());
        Glide.with(context).load(current.getUserimage()).into(myHolder.textImage);
        myHolder.textAddress.setText(current.getAddress());
        myHolder.textMobile.setText(current.getPhone().getMobile());
        myHolder.textHome.setText(current.getPhone().getHome());

        if (current.getGender().matches("female")) {
            myHolder.textFemale.setTypeface(null, Typeface.BOLD);
            myHolder.textMale.setTypeface(null, Typeface.NORMAL);
        } else {
            myHolder.textMale.setTypeface(null, Typeface.BOLD);
            myHolder.textFemale.setTypeface(null, Typeface.NORMAL);
        }

        myHolder.idRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context.getApplicationContext(), "Item Deleted", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView textId;
        TextView textName;
        TextView textEmail;
        TextView textAddress;
        TextView textFemale;
        TextView textMale;
        ImageView textImage;
        TextView textMobile;
        TextView textHome;
        Button idRemove;

        public MyHolder(View itemView){
            super(itemView);
            textId = (TextView) itemView.findViewById(R.id.textId);
            textName = (TextView) itemView.findViewById(R.id.textName);
            textEmail = (TextView) itemView.findViewById(R.id.textEmail);
            textAddress = (TextView) itemView.findViewById(R.id.textAddress);
            textFemale = (TextView) itemView.findViewById(R.id.textFemale);
            textMale = (TextView) itemView.findViewById(R.id.textMale);
            textImage = (ImageView) itemView.findViewById(R.id.textImage);
            textMobile = (TextView) itemView.findViewById(R.id.textMobile);
            textHome = (TextView) itemView.findViewById(R.id.textHome);
            idRemove = (Button) itemView.findViewById(R.id.idRemove);
        }
    }
}
