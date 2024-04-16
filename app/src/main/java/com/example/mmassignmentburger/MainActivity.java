package com.example.mmassignmentburger;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private burger burgerExpert;
    private Spinner burgerPreference;
    private RadioGroup radioGroup;
    private Button finalizeButton;
    private TextView responseTextView;
    private TextView recommendationTextView;
    private TextView radioResponseTextView;
    private TextView newResponseTextView; // New TextView for displaying responses

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the app's locale to the default system locale
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(Locale.getDefault());
        resources.updateConfiguration(config, resources.getDisplayMetrics());

        setContentView(R.layout.activity_main);
        burgerExpert = new burger();

        burgerPreference = findViewById(R.id.preference_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.choose_burger_options, android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        burgerPreference.setAdapter(adapter);

        radioGroup = findViewById(R.id.radio_group);
        radioResponseTextView = findViewById(R.id.response_text_view);
        responseTextView = findViewById(R.id.recommendation_text_view);
        newResponseTextView = findViewById(R.id.new_response_text_view); // Initialize the new TextView

        burgerPreference.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                onSelectRecommend(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_yes) {
                    radioResponseTextView.setText("Thank you! :)");
                } else if (checkedId == R.id.radio_no) {
                    radioResponseTextView.setText("Too Bad :(");
                }
            }

        finalizeButton = findViewById(R.id.finalize_button);
        finalizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected radio button text
                int checkedId = radioGroup.getCheckedRadioButtonId();
                String radioText = "";
                if (checkedId == R.id.radio_yes) {
                    radioText = "Free fires for you! :)";
                } else if (checkedId == R.id.radio_no) {
                    radioText = "You can always change your mind ;)";
                }

                // Get the selected spinner item
                String selectedBurger = burgerPreference.getSelectedItem().toString();

                // Combine the radio button text and selected spinner item
                String responseText =  radioText;

                // Set the text of the newResponseTextView and make it visible
                newResponseTextView.setText(responseText);
                newResponseTextView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void onSelectRecommend(int position) {
        String description = getResources().getStringArray(R.array.choose_burger_options)[position];
        List<String> expertRecommendations = burgerExpert.getRecommendations(description);
        StringBuilder recommendationsFormatted = new StringBuilder();
        for (String recommendation : expertRecommendations) {
            recommendationsFormatted.append(recommendation);
            recommendationsFormatted.append('\n');
        }
        responseTextView.setText(recommendationsFormatted.toString());
    }
}
