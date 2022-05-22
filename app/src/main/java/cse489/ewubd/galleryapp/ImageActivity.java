package cse489.ewubd.galleryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ImageView imageView = findViewById(R.id.image);
        TextView textView = findViewById(R.id.description);
        Button backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(view -> {
            finish();
        });

        Intent intent = getIntent();
        String url = intent.getStringExtra("url").toString();
        String description = intent.getStringExtra("desc").toString();

        if (url == null || description == null) {
            Toast.makeText(this, "Image URL or Description not found", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        Picasso.get().load(url).into(imageView);
        textView.setText(description);
    }
}