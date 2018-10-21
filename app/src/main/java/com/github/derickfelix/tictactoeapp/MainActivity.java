package com.github.derickfelix.tictactoeapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private DatabaseReference mDatabase;
    private DatabaseReference mBoard;

    private TextView status;
    private List<Button> buttons;
    private List<Integer> board;

    private boolean circle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mBoard = mDatabase.child("board");

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

        //Board board = new Board();
        board = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            board.add(Board.EMPTY);
        }

        mBoard.setValue(board);

        mBoard.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                board = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    board.add(ds.getValue(Integer.class));
                }

                updateUI();
                circle = !circle;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                Log.e(TAG, databaseError.getMessage());
            }
        });
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
                for (int i = 0; i < board.size(); i++) {
                    board.set(i, Board.EMPTY);
                }

                mBoard.setValue(board);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonOne(View view)
    {
        updateBoard(0);
    }

    public void buttonTwo(View view)
    {
        updateBoard(1);
    }

    public void buttonThree(View view)
    {
        updateBoard(2);
    }

    public void buttonFour(View view)
    {
        updateBoard(3);
    }

    public void buttonFive(View view)
    {
        updateBoard(4);
    }

    public void buttonSix(View view)
    {
        updateBoard(5);
    }

    public void buttonSeven(View view)
    {
        updateBoard(6);
    }

    public void buttonEight(View view)
    {
        updateBoard(7);
    }

    public void buttonNine(View view)
    {
        updateBoard(8);
    }

    private void updateBoard(int position)
    {
        if (board.get(position) == Board.EMPTY) {
            if (circle) {
                board.set(position, Board.P1);
            } else {
                board.set(position, Board.P2);
            }
        }

        mBoard.setValue(board);
        //status.setTextColor(button.getCurrentTextColor());
        //status.setText(button.getText());
    }

    private void updateUI()
    {
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            int value = board.get(i);

            switch (value) {
                case Board.P1:
                    button.setTextColor(getResources().getColor(R.color.colorO));
                    button.setText("O");
                    break;
                case Board.P2:
                    button.setTextColor(getResources().getColor(R.color.colorX));
                    button.setText("X");
                    break;
                case Board.EMPTY:
                    button.setText("");
                    break;

            }
        }
    }
}
