package com.example.pfa_tourismo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.example.pfa_tourismo.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        // Récupération des références des champs d'entrée
        TextInputEditText emailEditText = findViewById(R.id.email_edit_text);
        TextInputEditText passwordEditText = findViewById(R.id.password_edit_text);

        // Bouton d'enregistrement
        Button registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupération des données saisies par l'utilisateur
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Vérification des champs vides
                if (TextUtils.isEmpty(email)) {
                    emailEditText.setError("Email is required.");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    passwordEditText.setError("Password is required.");
                    return;
                }

                // Création du compte utilisateur avec Firebase Authentication
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Enregistrement réussi, affichage d'un message de succès
                                    Toast.makeText(Register.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                                    // Redirection vers l'activité de votre choix après l'enregistrement
                                    Intent intent = new Intent(Register.this, MainActivity.class);
                                    startActivity(intent);
                                    finish(); // Ferme l'activité d'enregistrement pour empêcher le retour
                                } else {
                                    // Enregistrement échoué, affichage d'un message d'erreur
                                    Toast.makeText(Register.this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        // Lien vers la page de connexion
        TextView signInTextView = findViewById(R.id.sign_in_text_view);
        signInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirection vers l'activité de connexion
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
