package cse489.ewubd.galleryapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageGridAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String[]> urls;

    public ImageGridAdapter(Context context, ArrayList<String[]> urls) {
        this.context = context;
        this.urls = urls;
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(this.context);
        Picasso.get().load(urls.get(i)[0]).into(imageView);
        imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 420));
        imageView.setPadding(5, 5, 5, 5);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        imageView.setOnClickListener(image -> {
            Intent intent = new Intent(this.context, ImageActivity.class);
            intent.putExtra("url", urls.get(i)[0]);
            intent.putExtra("desc", urls.get(i)[1]);
            this.context.startActivity(intent);
        });

        return imageView;
    }
}
