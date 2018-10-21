package com.github.derickfelix.tictactoeapp;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public static final int EMPTY = 0;
    public static final int P1 = 1;
    public static final int P2 = 2;

    private List<Integer> positions;

    public Board()
    {
        positions = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            positions.add(EMPTY);
        }
    }

    public List<Integer> getPositions()
    {
        return positions;
    }

    public void setPositions(List<Integer> positions)
    {
        this.positions = positions;
    }
}
