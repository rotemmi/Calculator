package Logic;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Calculator;
import com.example.myapplication.R;

public class CalculatorLogic
{
    private double number1,number2,result=0;
    private int sign1=1;
    private String op;
    private int [] btnIdArray={R.id.plus_btn,R.id.div_btn,R.id.minus_btn,R.id.id_mul,R.id.id_mod};
    private TextView textView;
    private View view;
    private Calculator activity;

    public  CalculatorLogic(View view,Calculator activity)
    {
        this.view =view;
        this.activity=activity;
        textView = view.findViewById(R.id.id_disp);
        op="";

    }
    public void funcOperator(int id)
    {
        Button bView = (Button) view.findViewById(id);
        Animation animi = AnimationUtils.loadAnimation(activity, R.anim.fade_in);
        bView.startAnimation(animi);
        for(int i:btnIdArray)
            view.findViewById(i).setEnabled(false);
        op = (String) bView.getText();
        textView.append(bView.getText());
    }

    public void funcNumber(int id)
    {
        Button bView = (Button) view.findViewById(id);
        Animation animi = AnimationUtils.loadAnimation(activity,R.anim.fade_in);
        bView.startAnimation(animi);
        textView.append(bView.getText());
        for(int i:btnIdArray)
            view.findViewById(i).setEnabled(true);
    }

    public void resClick()
    {
        if (op!="")
        {
            String txt = textView.getText().toString();
            if (txt.toCharArray()[0] == '-') {
                txt = txt.substring(1);
                sign1 = -1;
            }
            String[] values = txt.split("\\+|\\-|\\*|\\/|\\%|\\^|\\√");

            number1 = Double.parseDouble(values[0])*sign1;
            number2 = Double.parseDouble(values[1]);

            switch (op) {
                case "+":
                    ;
                    result = number1 + number2;
                    break;
                case "-":
                    result = number1 - number2;
                    break;
                case "*":
                    result = number1 * number2;
                    break;
                case "^":
                    result = Math.pow(number1, number2);
                    break;
                case "√":
                    result = Math.pow(number2, 1 / number1);
                    break;
                case "/":
                    if (number2 != 0) result = number1 / number2;
                    break;
                case "%":
                    if (number2 != 0) result = number1 % number2;
                    break;
                default:
                    break;
            }
            sign1=1;
            textView.setText(String.valueOf(result));
            for (int i : btnIdArray)
                view.findViewById(i).setEnabled(true);
        }

    }

    public void funcClear()
    {
        for(int i:btnIdArray)
            view.findViewById(i).setEnabled(true);
        op="";
        sign1 = 1;
        number1=0;
        number2=0;
        result=0;
        textView.setText("");
    }
}
