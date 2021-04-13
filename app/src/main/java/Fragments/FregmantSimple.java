package Fragments;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Calculator;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import Logic.CalculatorLogic;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FregmantSimple#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FregmantSimple extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private  CalculatorLogic cLogic;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FregmantSimple() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FregmantSimple.
     */
    // TODO: Rename and change types and number of parameters
    public static FregmantSimple newInstance(String param1, String param2) {
        FregmantSimple fragment = new FregmantSimple();
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
        View view = inflater.inflate(R.layout.calculator_activity, container, false);
        Button scientificButton = (Button) view.findViewById(R.id.science_btn);
        cLogic=new CalculatorLogic(view,(Calculator)getActivity());

        Button [] numbersButtons= new Button[11];
        Button [] OperatorsButtons= new Button[5];

        int [] idNumberArray = {R.id.id_0,R.id.id_1,R.id.id_2,R.id.id_3,R.id.id_4,R.id.id_5,R.id.id_6,R.id.id_7,R.id.id_8,R.id.id_9,R.id.id_dot};
        int [] idOperatorsArray = {R.id.plus_btn,R.id.minus_btn,R.id.div_btn,R.id.id_mul,R.id.id_mod};
        for(int i = 0;i<11;i++)
        {
            numbersButtons[i]=(Button)view.findViewById(idNumberArray[i]);
            final int j=idNumberArray[i];
            numbersButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    cLogic.funcNumber(j);
                }
            });
        }

        for(int i = 0;i<5;i++)
        {
            OperatorsButtons[i]=(Button)view.findViewById(idOperatorsArray[i]);
            final int j=idOperatorsArray[i];
            OperatorsButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    cLogic.funcOperator(j);
                }
            });
        }

        ((Button) view.findViewById(R.id.res_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                cLogic.resClick();
            }
        });
        ((Button) view.findViewById(R.id.clear_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                cLogic.funcClear();
            }
        });

        ((Button) view.findViewById(R.id.science_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Calculator activity =(Calculator)getActivity();
                activity.loadScience();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onResume();
    }
}