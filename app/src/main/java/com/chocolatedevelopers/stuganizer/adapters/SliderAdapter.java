package com.chocolatedevelopers.stuganizer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.chocolatedevelopers.stuganizer.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public int[] slide_images = {
        R.drawable.app_onboarding_icon,
        R.drawable.calculator_onboarding_icon,
        R.drawable.note_onboarding_icon
    };

    public String[] slide_titles = {
            "Organize better",
            "Calculate GPA",
            "Pen it down"
    };

    public String[] slide_description = {
            "Organize your daily academic activities. keep track of your lectures.",
            "Calculate, save and protect your GPA for all years. Do good with the 'Help a friend' feature.",
            "You just might not remember it. So, pen it down."
    };


    @Override
    public int getCount() {
        return slide_description.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide_layout, container, false);

        CircleImageView imageView = view.findViewById(R.id.image);
        TextView title = view.findViewById(R.id.title_text);
        TextView description = view.findViewById(R.id.description_text);

        imageView.setImageResource(slide_images[position]);
        title.setText(slide_titles[position]);
        description.setText(slide_description[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
