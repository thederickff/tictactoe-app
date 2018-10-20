package com.github.derickfelix.tictactoeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private DatabaseReference mDatabase;

    private TextView status;
    private List<Button> buttons;

    private boolean circle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        buttons = new ArrayList<>();

        status = findViewById(R.id.tv_status);

        Button button1 = findViewById(R.id.bt1);
        Button button2 = findViewById(R.id.bt2);
        Button button3 = findViewById(R.id.bt3);
        Button button4 = findViewById(R.id.bt4);
        Button button5 = findViewById(R.id.bt5);
        Button button6 = findViewById(R.id.bt6);
        Button button7 = findViewById(R.id.bt7);
        Button button8 = findViewById(R.id.bt8);
        Button button9 = findViewById(R.id.bt9);

        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);
        buttons.add(button6);
        buttons.add(button7);
        buttons.add(button8);
        buttons.add(button9);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.clear_action:
                for (Button button : buttons) {
                    button.setText("");
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonOne(View view)
    {
        updateValue(buttons.get(0));
    }

    public void buttonTwo(View view)
    {
        updateValue(buttons.get(1));
    }

    public void buttonThree(View view)
    {
        updateValue(buttons.get(2));
    }

    public void buttonFour(View view)
    {
        updateValue(buttons.get(3));
    }

    public void buttonFive(View view)
    {
        updateValue(buttons.get(4));
    }

    public void buttonSix(View view)
    {
        updateValue(buttons.get(5));
    }

    public void buttonSeven(View view)
    {
        updateValue(buttons.get(6));
    }

    public void buttonEight(View view)
    {
        updateValue(buttons.get(7));
    }

    public void buttonNine(View view)
    {
        updateValue(buttons.get(8));
    }

    void updateValue(Button button)
    {
        if (TextUtils.isEmpty(button.getText().toString())) {

            if (circle) {
                button.setTextColor(getResources().getColor(R.color.colorO));
                button.setText("O");
            } else {
                button.setTextColor(getResources().getColor(R.color.colorX));
                button.setText("X");
            }

            status.setTextColor(button.getCurrentTextColor());
            status.setText(button.getText());
            circle = !circle;
        }
    }
}
