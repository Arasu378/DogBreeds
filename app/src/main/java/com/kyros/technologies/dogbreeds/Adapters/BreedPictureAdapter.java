package com.kyros.technologies.dogbreeds.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Vibrator;
import android.sax.RootElement;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.kyros.technologies.dogbreeds.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by kyros on 23-08-2017.
 */

public class BreedPictureAdapter extends RecyclerView.Adapter<BreedPictureAdapter.BreedViewHolder> {
    private Context mContext;
    private List<String>picturelist;
    private  AlertDialog.Builder builder1;
    public BreedPictureAdapter(Context mContext, List<String> picturelist){
        this.mContext=mContext;
        this.picturelist=picturelist;
    }

    @Override
    public BreedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_breed_picture,parent,false);
        return new BreedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BreedViewHolder holder, int position) {
        final String url=picturelist.get(position);
        if(url!=null){
            try{
                Picasso.with(mContext.getApplicationContext()).load(url).into(holder.breed_picture);
            }catch (Exception e){
                e.getMessage();
            }
        }
        holder.card_breed_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext.getApplicationContext(),"Picture Clicked!",Toast.LENGTH_SHORT).show();
            }
        });
        holder.card_breed_picture.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Vibrator vibe = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(100);

                Picasso.with(mContext).load(url).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        showDialog(url,bitmap);

                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
                return false;
            }
        });

    }
private void showDialog(final String url,final Bitmap thumbnail){
    if(builder1==null){
        builder1 = new AlertDialog.Builder(mContext);
        builder1.setMessage("Download this picture?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        try {
                            WriteFileToStorage(url,thumbnail);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

    }
    AlertDialog alert11 = builder1.create();
    alert11.show();
}

    private void WriteFileToStorage(String url,Bitmap thumbnail) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        String title="DogBreedPictures";

                String state= Environment.getExternalStorageState();
        if(!Environment.MEDIA_MOUNTED.equals(state)){
            Toast.makeText(mContext, "SD Card not mounted!", Toast.LENGTH_LONG).show();
        }
        File imagestoragedir=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),title);
        if(!imagestoragedir.exists()&&!imagestoragedir.mkdirs()){
            Toast.makeText(mContext, "Failed to create directory in SD Card", Toast.LENGTH_LONG).show();
        }
        File destination=new File(imagestoragedir.getPath(),url);
        FileOutputStream fo;
        try{
            imagestoragedir.mkdirs();
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if(picturelist==null){
            return 0;
        }
        return picturelist.size();
    }

    public class BreedViewHolder extends RecyclerView.ViewHolder{
    public ImageView breed_picture;
        public CardView card_breed_picture;
        public BreedViewHolder(View itemView) {
            super(itemView);
            card_breed_picture=(CardView)itemView.findViewById(R.id.card_breed_picture);
            breed_picture=(ImageView)itemView.findViewById(R.id.breed_picture);
        }
    }
}
