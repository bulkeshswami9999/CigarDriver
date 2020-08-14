package com.hav.cigar.driver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.hav.cigar.driver.R;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    private Context context;
    private List<String> title;
    private List<Integer> count;

    public SliderAdapter(Context context, List<String> title,List<Integer> count) {
        this.context = context;
        this.title = title;
        this.count = count;
    }

    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.home_slider, null);

        TextView titleTxt = (TextView) view.findViewById(R.id.title);
        TextView countTxt = (TextView) view.findViewById(R.id.count);

        titleTxt.setText(title.get(position));
        countTxt.setText(""+count.get(position));
        if(position == 1) {
            countTxt.setTextColor(context.getResources().getColor(R.color.orange));
        }else if(position ==2){
                countTxt.setTextColor(context.getResources().getColor(R.color.red));
            }else if(position ==3){
                countTxt.setTextColor(context.getResources().getColor(R.color.green_light));
        }
        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }

}
