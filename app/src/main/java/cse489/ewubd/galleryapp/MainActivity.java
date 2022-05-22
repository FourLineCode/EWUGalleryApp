package cse489.ewubd.galleryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private final String URL = "https://muthosoft.com/univ/photos/";

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpClient client = new OkHttpClient();

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                Request request = new Request.Builder().url(URL).build();
                try (Response response = client.newCall(request).execute()) {
                    String data = response.body().string();
                    return data;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String data) {
                super.onPostExecute(data);

                if (data != null) {
                    renderGridView(data);
                }
            }
        }.execute();

    }

    private void renderGridView(String data) {
        ArrayList<String[]> images = new ArrayList();

        String[] array = data.split(",");
        for (String s : array) {
            String[] imageData = s.split(".jpeg:");
            imageData[0] = this.URL + imageData[0] + ".jpeg";

            if (imageData.length == 2) {
                System.out.println(imageData[0]);
                images.add(imageData);
            }
        }

        ImageGridAdapter adapter = new ImageGridAdapter(this, images);
        GridView imageGrid = findViewById(R.id.image_grid);
        imageGrid.setAdapter(adapter);
    }
}