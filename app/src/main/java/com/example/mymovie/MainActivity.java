package com.example.mymovie;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ablanco.imageprovider.ImageProvider;
import com.ablanco.imageprovider.ImageSource;
import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {
    EditText moviename;
    Spinner moviespinner;
    Button save,takeimage;
    ImageView movieimage;
    Bitmap b;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save=findViewById(R.id.Busave);
        moviespinner=findViewById(R.id.moviespinner);
        moviename=findViewById(R.id.moviename);
        takeimage=findViewById(R.id.butakeimage);
        movieimage=findViewById(R.id.movieimage);
    }

    public void save(View view) {
        Backendless.Files.Android.upload(b, Bitmap.CompressFormat.PNG, 40, moviename.getText().toString(), "images", new AsyncCallback<BackendlessFile>() {
            @Override
            public void handleResponse(BackendlessFile response) {
                movie movie=new movie();
                movie.setName(moviename.getText().toString());
                movie.setType(moviespinner.getSelectedItem().toString());
                movie.setUrl(response.getFileURL().toString());
                Backendless.Data.save(movie, new AsyncCallback<com.example.mymovie.movie>() {
                    @Override
                    public void handleResponse(com.example.mymovie.movie response) {
                        Toast.makeText(MainActivity.this, "saved", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();

                    }
                });

            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });



    }

    public void takeimage(View view) {
        ImageProvider provider=new ImageProvider(this);
        provider.getImage(ImageSource.GALLERY, new Function1<Bitmap, Unit>() {
            @Override
            public Unit invoke(Bitmap bitmap) {
                 b =bitmap;
                movieimage.setImageBitmap(bitmap);
                return null;
            }
        });
    }
}