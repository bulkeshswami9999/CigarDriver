package com.hav.cigar.driver.ui.help;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.hav.cigar.driver.R;
import com.hav.cigar.driver.adapter.MyExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelpandFeedback#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HelpandFeedback extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    @BindView(R.id.expandableListView)
    ExpandableListView expandableListView;
    //langs
    List<String> question;
    //topics
    Map<String, List<String>> answer;
    ExpandableListAdapter listAdapter;


    public HelpandFeedback() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HelpandFeedback.
     */
    // TODO: Rename and change types and number of parameters
    public static HelpandFeedback newInstance(String param1, String param2) {
        HelpandFeedback fragment = new HelpandFeedback();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View helpFeedbackView = inflater.inflate(R.layout.fragment_helpand_feedback, container, false);
        ButterKnife.bind(this,helpFeedbackView);
        fillData();
        listAdapter = new MyExpandableListAdapter(getContext(),question,answer);
        expandableListView.setAdapter(listAdapter);

        return helpFeedbackView;
    }
    public void fillData(){

        question = new ArrayList<>();
        answer = new HashMap<>();

        question.add(" Are there any other charges apart from those mentioned in the rate chart?");
        question.add(" What payment modes are accepted?");
        question.add(" Can I take this service on credit?");
        question.add(" Will I get an invoice?");
        question.add(" A client is being difficult to deal with. \n How to manage the situation?");


        List<String> firstquestion = new ArrayList<>();
        List<String> secondquestion = new ArrayList<>();
        List<String> thirdquestion = new ArrayList<>();
        List<String> fourthquestion = new ArrayList<>();
        List<String> fifthquestion = new ArrayList<>();

        firstquestion.add("The prices mentioned on the website are inclusive of all taxes.");
        secondquestion.add("All online payment modes are accepted.");
        thirdquestion.add("Sorry, we donâ€™t allow a credit on this service currently.");
        fourthquestion.add("No.");
        fifthquestion.add("Driver should always remain calm and professional when dealing with clients.");

        answer.put(question.get(0),firstquestion);
        answer.put(question.get(1),secondquestion);
        answer.put(question.get(2),thirdquestion);
        answer.put(question.get(3),fourthquestion);
        answer.put(question.get(4),fifthquestion);





    }

}
