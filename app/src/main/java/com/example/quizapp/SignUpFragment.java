package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;


public class SignUpFragment extends Fragment {

    View objectSignUpFragment;
    private EditText userEmail, userPassword;
    private FirebaseAuth mAuth;
    private Button signUpButton;

    private void SignUpButton() {
        mAuth = FirebaseAuth.getInstance();
        signUpButton.setOnClickListener(v -> createUser());
    }

    public void createUser() {
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            userEmail.setError("Email cannot be empty");
            userEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            userPassword.setError("Password cannot be empty");
            userPassword.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    SignUpButton();
                    Toast.makeText(getActivity(), "User SignUp Successfully", Toast.LENGTH_SHORT).show();
                    sendUsereToNextActivity();
                } else {
                    Toast.makeText(getContext(), "Sign up Error" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
    //  if (!userEmail.getText().toString().isEmpty() && !userPassword.getText().toString().isEmpty()) {
//
//                SignUpButton.setEnabled(false);
//
//                mAuth.createUserWithEmailAndPassword(userEmail.getText().toString(),
//                                userPassword.getText().toString())
//                        .addOnSuccessListener(authResult -> {
//                            Toast.makeText(getContext(), "User created...", Toast.LENGTH_SHORT).show();
//
//                            progressbar.setVisibility(View.INVISIBLE);
//
//                            SignUpButton.setEnabled(true);
//                            userEmail.setText("");
//                            userPassword.setText("");
//                            if (mAuth.getCurrentUser() != null) {
//                                mAuth.signOut();
//                            }
//                        })
//                        .addOnFailureListener(e -> Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show());
//
//                progressbar.setVisibility(View.INVISIBLE);
//                SignUpButton.setEnabled(true);
//
//
//            } else {
//                Toast.makeText(getContext(), "Please fill the fields first", Toast.LENGTH_SHORT).show();
//            }
//
//    }


//    private void checkUserExists() {
//
//        mAuth.fetchSignInMethodsForEmail(userEmail.getText().toString())
//                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
//
//                        boolean check = !task.getResult().getSignInMethods().isEmpty();
//                        if (!check){
//                            Toast.makeText(getActivity(), "Email not found", Toast.LENGTH_SHORT).show();
//                        }else {
//                            Toast.makeText(getActivity(), "Email already exists", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
//    }
////        try {
//
//            if (mAuth != null && !userEmail.getText().toString().isEmpty()) {
//                progressbar.setVisibility(View.VISIBLE);
//                signUpButton.setEnabled(false);
//                mAuth.fetchSignInMethodsForEmail(userEmail.getText().toString())
//                        .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
//
//                                boolean checkResult = !Objects.requireNonNull(task.getResult().getSignInMethods()).isEmpty();
//
//                                if (!checkResult) {
//                                    createUser();
//                                } else {
//                                    Toast.makeText(getContext(), "User already been created", Toast.LENGTH_SHORT).show();
//                                    progressbar.setVisibility(View.INVISIBLE);
//                                    signUpButton.setEnabled(true);
//                                }
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//
//                                progressbar.setVisibility(View.INVISIBLE);
//                                signUpButton.setEnabled(true);
//                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//            }
//        } catch (Exception e) {
//            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }


    private void attachToXML() {

        try {
            userEmail = objectSignUpFragment.findViewById(R.id.signUp_email);
            userPassword = objectSignUpFragment.findViewById(R.id.SignUpPassword);
            mAuth = FirebaseAuth.getInstance();
            signUpButton = objectSignUpFragment.findViewById(R.id.SignUpnbutton);

        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        SignUpButton();
    }

    private void sendUsereToNextActivity() {
        Intent intent = new Intent(getContext(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        objectSignUpFragment = inflater.inflate(R.layout.fragment_signupfragment, container, false);
        attachToXML();
        return objectSignUpFragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        
    }
}









/*


    }


    private void attachToXML() {

        userEmail = objectSignUpFragment.findViewById(R.id.signUp_email);
        userPassword = objectSignUpFragment.findViewById(R.id.SignUpNewPassword);
        userComfirmPassword = objectSignUpFragment.findViewById(R.id.SignUpNewPassword);
        SignUpButton = objectSignUpFragment.findViewById(R.id.SignUpnbutton);
        progressbar = objectSignUpFragment.findViewById(R.id.progressbar_signup);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerforAuth();
            }
        });

    }

    private void PerforAuth() {
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();
        String comfirmPassword = userComfirmPassword.getText().toString();

        if (!email.matches(emailPattern)) {
            userEmail.setError("Enter Email");
        } else if (password.isEmpty() || password.length() < 6) {
            userPassword.setError("Enter Proper Password");
        } else if (!password.equals(comfirmPassword)) {
            userComfirmPassword.setError("Password Not match Both field");
        } else {

            // why they not show this methods;
            // progressbar.setMessage("");
            // progressbar.setTitle("");
            // progressbar.setCancelOnTouchOutside();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        sendUserTonextActivity();
                        Toast.makeText(getContext(), "SignUp Successsful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }



    }




    private void sendUserTonextActivity() {
        Intent intent = new Intent(getContext(), Home_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}
    public Signupfragment() {

    }

    public void createUser() {

        try {

            if (!userEmail.getText().toString().isEmpty() && !userPassword.getText().toString().isEmpty()) {

                objectprogressbar.setVisibility(View.VISIBLE);
                SignUpButton.setEnabled(false);

                objectFirebaseAuth.createUserWithEmailAndPassword(userEmail.getText().toString(), userPassword.getText().toString())
                        .addOnSuccessListener(authResult -> {
                            Toast.makeText(getContext(), "User Login", Toast.LENGTH_SHORT).show();

                            objectprogressbar.setVisibility(View.INVISIBLE);

                            SignUpButton.setEnabled(true);
                            userEmail.setText("");
                            userPassword.setText("");
                            if (objectFirebaseAuth.getCurrentUser() != null) {
                                objectFirebaseAuth.signOut();
                            }
                        })
                        .addOnFailureListener(e -> Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show());

                objectprogressbar.setVisibility(View.INVISIBLE);
                SignUpButton.setEnabled(true);


            } else {
                Toast.makeText(getContext(), "Please fill the fields first", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    private void checkUserExists() {
        try {

            if (objectFirebaseAuth != null && !userEmail.getText().toString().isEmpty()) {
                objectprogressbar.setVisibility(View.VISIBLE);
                SignUpButton.setEnabled(false);
                objectFirebaseAuth.fetchSignInMethodsForEmail(userEmail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                            @Override
                            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                                boolean checkResult = !Objects.requireNonNull(task.getResult().getSignInMethods()).isEmpty();

                                if (!checkResult) {
                                    createUser();
                                } else {
                                    Toast.makeText(getContext(), "User already been created", Toast.LENGTH_SHORT).show();
                                    objectprogressbar.setVisibility(View.INVISIBLE);
                                    SignUpButton.setEnabled(true);
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                objectprogressbar.setVisibility(View.INVISIBLE);
                                SignUpButton.setEnabled(true);
                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void attachToXML() {

        try {
            userEmail = objectSignUpFragment.findViewById(R.id.signUp_email);
            userPassword = objectSignUpFragment.findViewById(R.id.SignUpNewPassword);


            objectFirebaseAuth = FirebaseAuth.getInstance();
            SignUpButton = objectSignUpFragment.findViewById(R.id.SignUpnbutton);


            objectprogressbar = objectprogressbar.findViewById(R.id.progressbar_signup);

            SignUpButton.setOnClickListener(v -> checkUserExists());

        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        objectSignUpFragment = inflater.inflate(R.layout.fragment_signupfragment, container, false);
        attachToXML();
        return objectSignUpFragment;
    }
}
*/


