package com.surajdevgan.recyclerviewusingvolleyandglide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.holder> {

    // variable for our array list and context.
    private ArrayList<UserModel> userModalArrayList;
    private Context context;


    // creating a constructor.
    public UserAdapter(ArrayList<UserModel> userModalArrayList, Context context) {
        this.userModalArrayList = userModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflating our layout file on below line.
        View view = LayoutInflater.from(context).inflate(R.layout.user_rv_item, parent, false);
        return new holder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.holder holder, int position) {

// getting data from our array list in our modal class.
        UserModel userModal = userModalArrayList.get(position);

        // on below line we are setting data to our text view.
        holder.firstNameTV.setText(userModal.getFirst_name());
        holder.lastNameTV.setText(userModal.getLast_name());
        holder.emailTV.setText(userModal.getEmail());

        // on below line we are loading our image
        // from url in our image view using glide.
        Glide.with(context).load(userModal.getAvatar()).into(holder.userIV);



    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return userModalArrayList.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        // creating a variable for our text view and image view.
         TextView firstNameTV, lastNameTV, emailTV;
        ImageView userIV;

        public holder(@NonNull View itemView) {
            super(itemView);

            // initializing our variables.
            firstNameTV = itemView.findViewById(R.id.idTVFirstName);
            lastNameTV = itemView.findViewById(R.id.idTVLastName);
            emailTV = itemView.findViewById(R.id.idTVEmail);
            userIV = itemView.findViewById(R.id.idIVUser);
        }

    }
    }

