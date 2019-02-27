package com.bookt.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.ArrayList;


public class GalleryImagesAdapter extends RecyclerView.Adapter<GalleryImagesAdapter.MyViewHolder> {


    private Context context;
    private ArrayList<Integer> imagesId;


    public GalleryImagesAdapter(Context context, ArrayList<Integer> imagesId) {
        this.context = context;
        this.imagesId = imagesId;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MyViewHolder myViewHolder;
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.gallerey_images_cardview, viewGroup, false);
        myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        myViewHolder.seekBar.setEnabled(false);
        myViewHolder.seekBar.setMax(imagesId.size()-1);
        myViewHolder.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                myViewHolder.seekBar.setProgress(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ViewPager viewPager;
        SeekBar seekBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            viewPager = itemView.findViewById(R.id.viewPager);
            seekBar = itemView.findViewById(R.id.seekBar2);


            ImageAdapter adapterView = new ImageAdapter(context, imagesId);
            viewPager.setAdapter(adapterView);


        }


        public class ImageAdapter extends PagerAdapter {
            Context mContext;
            private ArrayList<Integer> sliderImageId;


            ImageAdapter(Context context, ArrayList<Integer> sliderImageId) {
                this.mContext = context;
                this.sliderImageId = sliderImageId;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == ((ImageView) object);
            }


            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(mContext);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setImageResource(sliderImageId.get(position));
                container.addView(imageView, 0);


                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((ImageView) object);
            }

            @Override
            public int getCount() {
                return sliderImageId.size();
            }
        }
    }
}
