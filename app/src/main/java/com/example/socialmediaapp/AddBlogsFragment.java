package com.example.socialmediaapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AddBlogsFragment extends Fragment {

    FirebaseAuth firebaseAuth;
    EditText title, des, imageUrlInput;
    ProgressDialog pd;
    String name, email, uid, dp;
    DatabaseReference databaseReference;
    Button upload;
    ImageView imagePreview;

    public AddBlogsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.fragment_add_blogs, container, false);

        title = view.findViewById(R.id.ptitle);
        des = view.findViewById(R.id.pdes);
        imageUrlInput = view.findViewById(R.id.imageUrlInput);
        upload = view.findViewById(R.id.pupload);
        imagePreview = view.findViewById(R.id.imagePreview);
        pd = new ProgressDialog(getContext());
        pd.setCanceledOnTouchOutside(false);

        // Get current user's email and uid before using them in the query
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            email = user.getEmail();
            uid = user.getUid();
        } else {
            Toast.makeText(getContext(), "User not logged in", Toast.LENGTH_SHORT).show();
            return view;
        }

        // Retrieve user data like name, email, and profile pic using query
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        Query query = databaseReference.orderByChild("email").equalTo(email);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    name = dataSnapshot1.child("name").getValue().toString();
                    email = "" + dataSnapshot1.child("email").getValue();
                    dp = "" + dataSnapshot1.child("image").getValue().toString();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

        // Preview the image from URL as soon as user finishes input
        imageUrlInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                String imageUrl = imageUrlInput.getText().toString().trim();
                if (!TextUtils.isEmpty(imageUrl)) {
                    Glide.with(getContext())
                            .load(imageUrl)
                            .placeholder(R.drawable.placeholder_image)
                            .error(R.drawable.error_image)
                            .into(imagePreview);
                } else {
                    imagePreview.setImageResource(R.drawable.placeholder_image);
                }
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titl = title.getText().toString().trim();
                String description = des.getText().toString().trim();
                String imageUrl = imageUrlInput.getText().toString().trim();

                if (TextUtils.isEmpty(titl)) {
                    title.setError("Title can't be empty");
                    Toast.makeText(getContext(), "Title can't be left empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(description)) {
                    des.setError("Description can't be empty");
                    Toast.makeText(getContext(), "Description can't be left empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(imageUrl)) {
                    imageUrlInput.setError("Image URL can't be empty");
                    Toast.makeText(getContext(), "Image URL can't be left empty", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    uploadData(titl, description, imageUrl);
                }
            }
        });

        return view;
    }

    private void uploadData(final String titl, final String description, final String imageUrl) {
        pd.setMessage("Publishing Post");
        pd.show();
        final String timestamp = String.valueOf(System.currentTimeMillis());

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("uid", uid);
        hashMap.put("uname", name);
        hashMap.put("uemail", email);
        hashMap.put("udp", dp);
        hashMap.put("title", titl);
        hashMap.put("description", description);
        hashMap.put("uimage", imageUrl);  // Save URL directly
        hashMap.put("ptime", timestamp);
        hashMap.put("plike", "0");
        hashMap.put("pcomments", "0");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Posts");
        databaseReference.child(timestamp).setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        pd.dismiss();
                        Toast.makeText(getContext(), "Published", Toast.LENGTH_LONG).show();
                        title.setText("");
                        des.setText("");
                        imageUrlInput.setText("");
                        imagePreview.setImageResource(R.drawable.placeholder_image);
                        startActivity(new Intent(getContext(), DashboardActivity.class));
                        getActivity().finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
