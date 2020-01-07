import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.util.Map;

public class Spammer extends KeyAdapter {

    private boolean run = true;
    private boolean isSpamming = true;
    private int startKey;
    private int spamKey;


    public Spammer(int startKey, int spamKey) {
        this.startKey = startKey;
        this.spamKey = spamKey;
        start();
    }

    public void start() {

        GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(true);

        System.out.println("Global keyboard hook successfully started, press [escape] key to shutdown. Connected keyboards:");

        for (Map.Entry<Long, String> keyboard : GlobalKeyboardHook.listKeyboards().entrySet()) {
            System.out.format("%d: %s\n", keyboard.getKey(), keyboard.getValue());
        }

        keyboardHook.addKeyListener(new GlobalKeyAdapter() {

            @Override
            public void keyPressed(GlobalKeyEvent event) {
                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_ESCAPE) {
                    run = !run;
                }
                if (event.getVirtualKeyCode() == startKey) {
                    isSpamming = !isSpamming;
                }
            }
        });

        try {
            while (run) {
                Thread.sleep(128);
                if (isSpamming) {
                    spam();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            keyboardHook.shutdownHook();
        }
    }

    public void spam() {
        Robot robot;
        try {
            robot = new Robot();
            robot.keyPress(spamKey);
            robot.keyRelease(spamKey);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}

