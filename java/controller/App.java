package controller;
import java.lang.ModuleLayer.Controller;
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
    Contract contract = new Contract(null, 0, 0, 0, null, null);

    ControlTower controller = new ControlTower();
    controller.start();
  }
}
