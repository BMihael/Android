package com.example.gamedetailapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ListAvengersFragment extends Fragment {

    private View avengersView;
    private RecyclerView myAvengersList;

    String genre;
    String title;
    String link;

    String genreSalji;
    String titleSalji;
    String linkSalji;


    private Firebase myFirebase;
    private DatabaseReference AvengerReference;

    public ListAvengersFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        avengersView = inflater.inflate(R.layout.fragment_listavengers, container, false);

        myAvengersList = avengersView.findViewById(R.id.avengers_recycleview);
        myAvengersList.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseDatabase myFirebaseDatabase = FirebaseDatabase.getInstance();

        Firebase.setAndroidContext(getContext());


        AvengerReference = myFirebaseDatabase.getInstance().getReference("Avenger");


        return avengersView;
    }

    @Override
    public void onStart() {

        super.onStart();

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Avenger>()
                .setQuery(AvengerReference,Avenger.class)
                .build();


      final FirebaseRecyclerAdapter<Avenger,AvengerViewHolder> adapter = new FirebaseRecyclerAdapter<Avenger, AvengerViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final AvengerViewHolder holder,int position, @NonNull final Avenger model) {

               Picasso.get().load(model.getImageLink()).into(holder.image);

               holder.textTitle.setText(model.getTitle());
               holder.textGenre.setText(model.getGenre());

                title = model.getTitle();
                genre = model.getGenre();
                link=model.getImageLink();


               holder.itemView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {


                       Intent showDataIntent = new Intent(getContext(), ShowDataActivity.class);


                       showDataIntent.putExtra("title",model.getTitle());
                       showDataIntent.putExtra("genre",model.getGenre());
                       showDataIntent.putExtra("link",model.getImageLink());

                       getContext().startActivity(showDataIntent);
                   }
               });

            }

            @NonNull
            @Override
            public AvengerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.avengerslist_display_layout,viewGroup,false);
                AvengerViewHolder viewHolder = new AvengerViewHolder(view);
                return viewHolder;
            }
        };

        myAvengersList.setAdapter(adapter);
        adapter.startListening();
    }

    public static class AvengerViewHolder extends RecyclerView.ViewHolder{

        TextView textTitle;
        TextView textGenre;
        ImageView image;

        public AvengerViewHolder(@NonNull View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.text1);
            textGenre = itemView.findViewById(R.id.text2);
            image = itemView.findViewById(R.id.imageViewAvenger);

        }
    }




}
