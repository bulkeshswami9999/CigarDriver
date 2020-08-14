package com.hav.cigar.driver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.hav.cigar.driver.R;

import java.util.List;
import java.util.Map;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String> question;
    Map<String, List<String>> answer;

    public MyExpandableListAdapter(Context context, List<String> question, Map<String, List<String>> answer) {
        this.context = context;
        this.question = question;
        this.answer = answer;
    }

    @Override
    public int getGroupCount() {
        return question.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return answer.get(question.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return question.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return answer.get(question.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        String question = (String) getGroup(i);

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_parent,null);
        }

        TextView textParent = (TextView) view.findViewById(R.id.list_parent);
        textParent.setText(question);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

       String answer = (String) getChild(i, i1);

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_child,null);
        }

        TextView textChild = (TextView) view.findViewById(R.id.list_child);
        textChild.setText(answer);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
