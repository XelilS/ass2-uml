package controller;

import java.lang.ModuleLayer.Controller;
import model.Time;

import javax.swing.text.View;
import model.Contract;
import model.Item;
import model.Member;
import model.MemberList;
import view.Viewer;

/**
 * Class app.
 */
public class App {

  /**
   * Main.
   */
  public static void main(String[] args) {

    Viewer viewer = new Viewer();
    MemberList memberList = new MemberList();
    Time time = new Time();

    ControlTower controller = new ControlTower(viewer, memberList, time);
    controller.start();
  }
}
