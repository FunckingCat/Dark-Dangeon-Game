package future.code.dark.dungeon;

import future.code.dark.dungeon.config.Configuration;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame(Configuration.GAME_NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new GameFrame(frame));
        frame.setVisible(true);
    }

}
