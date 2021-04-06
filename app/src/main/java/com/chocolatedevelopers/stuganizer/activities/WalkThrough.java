package com.chocolatedevelopers.stuganizer.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chocolatedevelopers.stuganizer.R;
import com.chocolatedevelopers.stuganizer.adapters.SliderAdapter;

public class WalkThrough extends AppCompatActivity {

    ViewPager pager;
    LinearLayout layout;
    ImageView[] mDots;
    SliderAdapter sliderAdapter;
    Button nextButton, backButton;
    private int currentPage, current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);

        pager = findViewById(R.id.boarding_view_pager);
        layout = findViewById(R.id.dotsLayout);

        nextButton = findViewById(R.id.next_btn);
        backButton = findViewById(R.id.prev_btn);

        sliderAdapter = new SliderAdapter(this);
        pager.setAdapter(sliderAdapter);

        pager.addOnPageChangeListener(viewListener);

        addDotsIndicator(0);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current = currentPage + 1;
                pager.setCurrentItem(current);
                if(current > 2) {
                    startActivity(new Intent(WalkThrough.this, FirstTimeActivity.class));
                    finish();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(currentPage - 1);
            }
        });
    }

    public void addDotsIndicator(int position) {
        mDots = new ImageView[3];
        layout.removeAllViews();
        for(int i = 0; i < mDots.length; i++) {
            mDots[i] = new ImageView(this);
            if(i == position)
            mDots[i].setImageDrawable(getResources().getDrawable(R.drawable.dot_selected_item));
            else {
                mDots[i].setImageDrawable(getResources().getDrawable(R.drawable.dot_unselected_item));
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(4, 0, 4, 0);
            layout.addView(mDots[i], params);
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);

            currentPage = position;
            if(position == 0) {
                nextButton.setEnabled(true);
                backButton.setEnabled(false);
                backButton.setVisibility(View.INVISIBLE);
                nextButton.setText("Next");
                backButton.setText("");
            } else if(position ==mDots.length - 1) {
                nextButton.setEnabled(true);
                backButton.setEnabled(true);
                backButton.setVisibility(View.VISIBLE);
                nextButton.setText("Finish");
                backButton.setText("Back");
            } else {
                nextButton.setEnabled(true);
                backButton.setEnabled(true);
                backButton.setVisibility(View.VISIBLE);
                nextButton.setText("Next");
                backButton.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
