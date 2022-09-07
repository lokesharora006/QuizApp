package com.example.quizapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignInFragment extends Fragment {


    View objectSignInFragment;
    EditText userEmail, userPassword;
    Button btnLogin;
    //    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    public SignInFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        objectSignInFragment = inflater.inflate(R.layout.fragment_sign_infragment, container, false);
        LoginAttachtoXML();
        return objectSignInFragment;
    }

    private void LoginAttachtoXML() {
        userEmail = objectSignInFragment.findViewById(R.id.userLoginEmail);
        userPassword = objectSignInFragment.findViewById(R.id.userLoginpassword);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        btnLogin = objectSignInFragment.findViewById(R.id.Loginbutton);

        btnLogin.setOnClickListener(v -> LoginUser());
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
            updateUI(currentUser);

    }

    private void updateUI(FirebaseUser currentUser) {
        if (mUser != null){
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    private void LoginUser() {
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            userEmail.setError("Email cannot be empty");
            userEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            userPassword.setError("Password can not be empty");
            userPassword.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {

                if (task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT).show();
                    sendUsereToNextActivity();

                } else {
                    Toast.makeText(getActivity(), "Login Error", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private void sendUsereToNextActivity() {
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            requireActivity().finish();
        }

    }




//    private void PerforLogin() {
//        String username = LoginEmail.getText().toString();
//        String password = userPassword.getText().toString();
//
//        if (!username.matches(emailPattern)) {
//            Toast.makeText(getContext(), "Enter username", Toast.LENGTH_SHORT).show();
//        } else if (password.length() == 0) {
//            userPassword.setError("Enter Proper Password");
//
//        } else {
//            mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//
//                    if (task.isSuccessful()) {
//                        sendUsereToNextActivity();
//                        Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(getContext(), "" + task.getException(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//    }




