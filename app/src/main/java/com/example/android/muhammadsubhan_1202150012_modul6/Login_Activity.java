package com.example.android.muhammadsubhan_1202150012_modul6;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.muhammadsubhan_1202150012_modul6.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login_Activity extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonRegister;
    private Button buttonLogin;
    private ProgressDialog progressDialog;


    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        firebaseAuth = FirebaseAuth.getInstance();

        /*if (firebaseAuth.getCurrentUser() != null){
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }*/

        progressDialog = new ProgressDialog(this);

        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);
        buttonRegister = (Button) findViewById(R.id.register);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void regis(View view) {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (!validateform()){
            return;
        }

        /*if (TextUtils.isEmpty(email)){
            //jika kosong
            Toast.makeText(this,"Masuukan Email !!!", Toast.LENGTH_LONG).show();

            return;
        }
        if (TextUtils.isEmpty(password)){
            //jika kosong
            Toast.makeText(this,"Masuukan Password !!!", Toast.LENGTH_LONG).show();
            return;
        }*/
        progressDialog.setMessage("Registering User Process...");
        progressDialog.show();


        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
//                            Toast.makeText( Login_Activity.this,"Register success !!!", Toast.LENGTH_LONG).show();
//                            progressDialog.dismiss();

                            //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            onAuthSuccess(task.getResult().getUser());

                        }else {
                            Toast.makeText(Login_Activity.this,"Register Failed !!!", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }
                });
    }

    private boolean validateform() {
        boolean valid = true;

        String email = editTextEmail.getText().toString();
        if (TextUtils.isEmpty(email)){
            editTextEmail.setError("Required");
            Toast.makeText(this,"Masukan Email !!!", Toast.LENGTH_LONG).show();
            valid = false;
        }
        else {
            editTextEmail.setError(null);
        }

        String password = editTextPassword.getText().toString();
        if (TextUtils.isEmpty(password)){
            editTextPassword.setError("Required");
            Toast.makeText(this,"Masukan Password !!!", Toast.LENGTH_LONG).show();
            valid = false;
        } else{
            editTextPassword.setError(null);
        }
        return valid;
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
    }

    public void login(View view) {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (!validateform()){
            return;
        }

        /*if (TextUtils.isEmpty(email)){
            //jika kosong
            Toast.makeText(this,"Masuukan Email !!!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            //jika kosong
            Toast.makeText(this,"Masuukan Password !!!", Toast.LENGTH_LONG).show();
            return;
        }*/

        progressDialog.setMessage("Login User Process...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            //start main activity

                            onAuthSuccess(task.getResult().getUser());
                        }
                        else {
                            toastshow();
                        }

                    }
                });
    }

    private void onAuthSuccess(FirebaseUser user) {
        String username = usernameFromEmail(user.getEmail());
        writeNewUser(user.getUid(),username,user.getEmail());

        startActivity(new Intent(Login_Activity.this,MainActivity.class));



        finish();
    }

    private void writeNewUser(String uid, String username, String email) {
        User user = new User(username,email);
        mDatabaseReference.child("users").child(uid).setValue(user);
    }

    private String usernameFromEmail(String email) {
        if (email.contains("@")){
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    private void toastshow() {
        Toast.makeText(this, "Username atau Password salah .. ", Toast.LENGTH_LONG).show();
    }

}

