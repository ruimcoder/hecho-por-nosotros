package com.folcamp.hechopornosotros.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FirebaseInitializer {

    @PostConstruct
    private void iniFirestore() throws IOException {
        FileInputStream serviceAccount =
                //El parametro es el nombre de la clave privada generada en firebase (archivo json) y almacenada en el proyecto
                new FileInputStream("key-ejemplo.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);


        if (FirebaseApp.getApps().isEmpty()){
            FirebaseApp.initializeApp(options);
        }

    }

    public Firestore getFirestore(){
        return FirestoreClient.getFirestore();
    }
}
