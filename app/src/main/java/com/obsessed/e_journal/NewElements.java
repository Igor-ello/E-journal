package com.obsessed.e_journal;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class NewElements {
    private Context myContext;

    private TextView textView;
    private EditText editText;
    private Button button;
    private LinearLayout linearLayout;
    private Spinner spinner;


    public NewElements(Context context){
        myContext = context;
    }

    public TextView newTextView(){
        textView = new TextView(myContext);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textView.setTextColor(ContextCompat.getColor(myContext, R.color.textColor));
        textView.setTextSize(myContext.getResources().getDimension(R.dimen.text_size)
                / myContext.getResources().getDisplayMetrics().scaledDensity);

        return textView;
    }

    public EditText newEditText(){
        editText = new EditText(myContext);
        editText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        editText.setTextColor(ContextCompat.getColor(myContext, R.color.textColor));
        editText.setTextSize(myContext.getResources().getDimension(R.dimen.text_size)
                / myContext.getResources().getDisplayMetrics().scaledDensity); // px -> sp

        return editText;
    }

    public LinearLayout newLinearLayout(){
        linearLayout = new LinearLayout(myContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(0, (int) myContext.getResources().getDimension(R.dimen.margin_top), 0, 0);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        return linearLayout;
    }

    public Button newButton(){
        button = new Button(myContext);
        button.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        return button;
    }

    public Spinner newSpinner(ArrayList<?> arrayList, int index) {
        spinner = new Spinner(myContext);

        ArrayAdapter<String> adapter = new ArrayAdapter(myContext,
                android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);
        spinner.setSelection(index);

        return spinner;
    }
}
