import javax.imageio.ImageIO;
import javax.sound.midi.Soundbank;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainGame {
    private static PhysicsAlt p;
    private static GraphicsWindow gw;

    private static int time = 0;

    private static JFrame game;

    private static int buttonMode;



    public static void startTitle() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        JButton startGame = new JButton("Start");
        JButton settings = new JButton("Settings");
        JButton quit = new JButton("Quit Game");

        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                buttonMode = 1;
            }
        });
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonMode = 2;
            }
        });
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonMode = 3;
            }
        });

        game = new JFrame();
        game.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 1;
        g.weighty = 10;
        game.add(startGame, g);
        game.add(settings, g);
        game.add(quit, g);

        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setUndecorated(true);
        //game.pack();
        game.setSize(640,480);
        game.setResizable(false);

        game.setVisible(true);
        GraphicsDevice d = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        d.setFullScreenWindow(game); //This part is for cool boi fullscreen
        d.setDisplayMode(new DisplayMode(640, 480, 32, 60));

        while (true){
            if(buttonMode == 1){
                startGame();
            }
            if(buttonMode == 2){

            }
            if(buttonMode == 3){
                System.exit(1);
            }
        }


    }


    public static void hey(){

    }


    public static void startGame() throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
        game.getContentPane().removeAll();

        game.setLayout(new FlowLayout());

        p = new PhysicsAlt(480, 480);

        gw = new GraphicsWindow(p);
        gw.setPreferredSize(new Dimension(470,470));

        JPanel info = new JPanel();
        info.setPreferredSize(new Dimension(160,470));
        info.setLayout(null);

        game.add(gw);
        game.add(info); //will add scoring n stuff here
        game.pack();

        Player plr = new Player(gw);


        p.addSprite(plr);
        Projectile ball = new Projectile( 200, 200,  0.03, 0.05);
        p.addSprite(ball);



        JLabel fps = new JLabel();
        fps.setBounds(120, 450, 40, 20);
        info.add(fps);





        time = 0;
        //Clip c1 = playMusic("res/Zun1.wav");
        long lastTime = System.currentTimeMillis();
        while (time/60.0/60<=1){
            spawnEnemies(0.01, 10);
            p.updatePhysics();
            gw.repaint();
            time++;
            Thread.sleep((long) 16.66666);
            double difference = System.currentTimeMillis()-lastTime;
            difference = difference*0.001;
            fps.setText("fps  " + (int)(1/difference));
            lastTime = System.currentTimeMillis();
        }
        //c1.stop();

    }
    public static Clip playMusic(String fileName) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        Clip bgm = AudioSystem.getClip();
        AudioInputStream stream = AudioSystem.getAudioInputStream(new File(fileName));
        bgm.open(stream);
        bgm.start();
        return bgm;
    }

    public static PhysicsAlt returnGamePhysics(){
        return p;
    }

    public static void spawnEnemies(double frequency, int bulletsPerEnemy) {
        if (Math.random() < frequency) {
            p.addSprite(new Enemy(Resource.DEFAULT_ENEMY, (int)(Math.random()*480), -40, 40, 40, Math.random()*6-3, Math.random()*3, bulletsPerEnemy, 1));
        }
    }
    public static int getTime(){
        return time;
    }

}

