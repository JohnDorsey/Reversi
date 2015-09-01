package com.github.johndorsey.reversi3.android;

import playn.android.GameActivity;

import com.github.johndorsey.reversi3.core.Reversi;

public class ReversiActivity extends GameActivity {

  @Override public void main () {
    new Reversi(platform());
  }
}
