package com.github.johndorsey.reversi3.html;

import com.google.gwt.core.client.EntryPoint;
import playn.html.HtmlPlatform;
import com.github.johndorsey.reversi3.core.Reversi;

public class ReversiHtml implements EntryPoint {

  @Override public void onModuleLoad () {
    HtmlPlatform.Config config = new HtmlPlatform.Config();
    // use config to customize the HTML platform, if needed
    HtmlPlatform plat = new HtmlPlatform(config);
    plat.assets().setPathPrefix("Reversi3/");
    new Reversi(plat);
    plat.start();
  }
}
