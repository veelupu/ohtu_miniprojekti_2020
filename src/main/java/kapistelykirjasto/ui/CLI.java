package kapistelykirjasto.ui;

import kapistelykirjasto.domain.Application;

public class CLI implements UserInterface {

    private Application app;
    private IO io;

    public CLI(Application app, IO io) {
        this.app = app;
        this.io = io;
    }

    @Override
    public void run() {
        io.print("***********************************");
        io.print("* Tervetuloa käpistelykirjastoon! *");
        io.print("*                                 *");
        io.print("*  Toiminnot:                     *");
        io.print("*  - 0: tulosta valikko           *");
        io.print("*  - X: poistu sovelluksesta      *");
        io.print("***********************************");
        io.print("");

        while (io.hasNextLine()) {
            String action = io.readLine("Anna toiminto: ");

            if (action.equals("X")) {
                io.print("suljetaan");
                // exit() for app closure, close db connection etc
                break;
            } else if (action.equals("0")) {
                printActions();
            } else {
                io.print("epäkelpo toiminto");
            }
        }
    }

    private void printActions() {
        io.print("- 0: tulosta valikko");
        io.print("- X: poistu sovelluksesta");
    }
}
