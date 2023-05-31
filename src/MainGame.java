import javax.imageio.ImageIO;
import javax.sound.midi.Soundbank;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class MainGame {
    private static PhysicsAlt p;
    private static GraphicsWindow gw;

    private static int time = 0;

    private static JFrame game;

    private static int numOfLives = 3;
    private static int numPower = 0;

    private static int score;

    private static Graphics windowBackground;



    public static void start() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        game = new JFrame();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setUndecorated(true);
        game.setSize(640,480);
        game.setResizable(false);

        game.setVisible(true);

        GraphicsDevice d = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        d.setFullScreenWindow(game); //This part is for cool boi fullscreen
        d.setDisplayMode(new DisplayMode(640, 480, 32, 60));

        Resource.loadAnimations();
        startTitle();

    }
    public static void startSettings(){
        JButton decrease  = new JButton("-");
        JButton increase = new JButton("+");
        JLabel lives = new JLabel(""+numOfLives);
        JPanel lifePanel = new JPanel();
        lifePanel.add(decrease);
        lifePanel.add(lives);
        lifePanel.add(increase);

        increase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numOfLives++;
                lives.setText(""+numOfLives);
            }
        });
        decrease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numOfLives--;
                lives.setText(""+numOfLives);
            }
        });

        JButton quit = new JButton("Quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    startTitle();
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        game.getContentPane().removeAll();
        game.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.weighty = 10;
        game.add(lifePanel, c);
        game.add(quit, c);
        game.revalidate();
        game.repaint();



    }
    public static void startTitle() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        JButton startGame = new JButton("Start");
        JButton settings = new JButton("Settings");
        JButton quit = new JButton("Quit Game");
        Font menuFont = new Font(Font.MONOSPACED, Font.PLAIN, 30);
        startGame.setFont(menuFont);
        settings.setFont(menuFont);
        quit.setFont(menuFont);
        startGame.setBorderPainted(false);
        startGame.setContentAreaFilled(false);
        settings.setBorderPainted(false);
        settings.setContentAreaFilled(false);
        quit.setBorderPainted(false);
        quit.setContentAreaFilled(false);

        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                try {
                    startGame();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startSettings();
            }
        });
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        game.getContentPane().removeAll();
        game.setLayout(new GridBagLayout());

        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 1;
        g.weighty = 10;


        Graphics gr = game.getGraphics();
        gr.drawImage(Resource.DEFAULT_MENU_BACKGROUND, 0, 0, null);

        game.add(startGame, g);
        game.add(settings, g);
        game.add(quit, g);

        game.revalidate();



    }


    public static void startGame() throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
        game.getContentPane().removeAll();

        game.setLayout(new FlowLayout());

        p = new PhysicsAlt(480, 480);

        gw = new GraphicsWindow(p);
        gw.setPreferredSize(new Dimension(470,470));




        InfoPanel info = new InfoPanel();
        info.setPreferredSize(new Dimension(160,470));
        info.setLayout(null);
        //info.setBackground(new Color(0, 0, 0, 0));


        game.repaint();
        windowBackground = game.getGraphics();

        game.add(gw);
        game.add(info); //will add scoring n stuff here
        game.pack();

        Player plr = new Player(gw);

        p.addSprite(plr);




        time = 0;
        //Clip c1 = playMusic("res/Zun1.wav");

        java.util.Timer level1timer = new java.util.Timer();

        TimerTask level1 = new TimerTask() {
            @Override
            public void run() {
                try {
                    Stage1(info, level1timer);
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }

                try {
                    Stage1Boss(info, level1timer);
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
                resetStats();
                try {
                    startTitle();
                } catch (UnsupportedAudioFileException e) {
                    throw new RuntimeException(e);
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }
        };
        level1timer.schedule(level1, 1);




    }
    public static void Stage1(JPanel info, Timer t) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        Clip c = playMusic("res/Return.wav");
        double lastTime = System.currentTimeMillis();
        while(time/60.0/60<=0.5){
            spawnEnemies(0.01, 10);
            try {
                p.updatePhysics();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            gw.repaint();

            time++;
            double current = System.currentTimeMillis();
            double difference = current-lastTime;
            difference = difference*0.001;
            updateInfoPanel(difference, info);
            lastTime = current;
            try {
                Thread.sleep((long)16.666666);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(getLives()<=0){
                c.close();
                return;
            }
        }
        c.close();
    }
    public static void Stage1Boss(JPanel info, Timer t) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        if(getLives()<=0){
            t.cancel();
            t.purge();
            return;
        }
        MessageThing.setMessage(new String[]{"Hey guys, I like to eat Hamburglers.", "Good for you"}, new Sprite(Resource.DEFAULT_PLAYER, 0, 0, 120, 240, 0, 0), new Sprite(Resource.ETHAN_IDLE, 0, 0, 120, 240, 0, 0));
        MessageThing.start();
        while(MessageThing.isDisplay()){
            gw.repaint();
        }
        Clip c = playMusic("res/Oceans.wav");
        double lastTime = System.currentTimeMillis();
        Ethan ethan = new Ethan();
        p.addSprite(ethan);
        game.setBackground(Color.red);
        while(p.getSpriteArray().contains(ethan)){
            SpellCard.updateSpinny();
            try {
                p.updatePhysics();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            gw.repaint();
            time++;
            double current = System.currentTimeMillis();
            double difference = current-lastTime;
            difference = difference*0.001;
            updateInfoPanel(difference, info);
            lastTime = current;
            try {
                Thread.sleep((long)16.666666);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(getLives()<=0){
                c.close();
                ethan.remove();
                SpellCard.stop();
                return;
            }
        }
        if(getLives()>0){
            int secondsOfRejoice = 10;
            double incrementor = 0;
            while(incrementor<secondsOfRejoice){
                p.updatePhysics();
                gw.repaint();
                incrementor += 1/60.0;
                Thread.sleep((long)16.6666666666);
            }
        }
        SpellCard.stop();
        t.cancel();
        c.close();
    }
    public static void resetStats(){
        numOfLives = 3;
        score = 0;
        numPower = 0;
    }

    public static void updateInfoPanel(double difference, JPanel info){

        info.removeAll();

        /**

        JLabel power = new JLabel("Power: " + numPower);
        power.setBounds(20, 200, 300, 20);
        power.setForeground(Color.RED);
        power.setFont(new Font(Font.SANS_SERIF, 0, 20));


        JLabel lives = new JLabel("Lives: ");
        for(int i = 1; i< numOfLives; i++){
            lives.setText(lives.getText() + "*");
        }
        lives.setBounds(20, 220, 300, 20);
        lives.setForeground(Color.RED);
        lives.setFont(new Font(Font.SANS_SERIF, 0, 20));

        info.add(lives);

        info.add(power);
         **/

        JLabel fps = new JLabel("fps  " + (int)(1/difference));
        fps.setBounds(120, 450, 40, 20);
        fps.setForeground(Color.RED);
        info.add(fps);

        info.repaint();




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
    public static GraphicsWindow returnGraphicsWindow(){
        return gw;
    }
    public static void spawnEnemies(double frequency, int bulletsPerEnemy) {
        if (Math.random() < frequency) {
            p.addSprite(new Enemy(Resource.DEFAULT_ENEMY, (int)(Math.random()*480), -40, 40, 40, Math.random()*4-2, Math.random()*3, bulletsPerEnemy, 1, 0.7, 0.2, 0.5));
        }
    }
    public static int getTime(){
        return time;
    }
    public static int getLives(){
        return numOfLives;
    }
    public static void setLives(int newNum){
        numOfLives = newNum;
    }
    public static void setNumPower(int num){
        numPower = num;
    }
    public static int getNumPower(){
        return numPower;
    }
    public static int getScore(){
        return score;
    }
    public static void setScore(int num){
        score = num;
    }


}

