package com.example.pfa_tourismo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialiser FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Récupérer les références des champs email et mot de passe
        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
    }

    // Méthode pour gérer le clic sur le bouton "Login"
    public void login(View view) {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        // Authentifier l'utilisateur avec Firebase
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Connexion réussie, rediriger l'utilisateur vers MapsActivity
                        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Connexion échouée, afficher un message d'erreur
                        Toast.makeText(MainActivity.this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Méthode pour gérer le clic sur le texte "Sign Up"
    public void Regsiter(View view) {
        // Redirection vers l'activité d'inscription (RegisterActivity)
        Intent intent = new Intent(MainActivity.this, Register.class);
        startActivity(intent);
    }

    public void Forgot_Password(View view) {
        // Redirection vers l'activité Forgot_Password
        Intent intent = new Intent(MainActivity.this, Forgot_Password.class);
        startActivity(intent);
    }
}
