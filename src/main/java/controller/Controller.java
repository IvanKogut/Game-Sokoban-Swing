package controller;

import model.Model;
import view.View;

/**
 * Created by Admin on 05.02.2017.
 */
public class Controller {

    private View view;
    private Model model;

    public Controller() {
        this.view = new View(this);
        this.model = new Model();
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
    }
}
