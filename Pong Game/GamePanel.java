import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends Panel implements Runnable{
    int width = 1000;
    int height = (int)(width*(0.555));
    Dimension screen = new Dimension(width,height);

    int Paddle_width = 25;
    int Paddle_height=100;
    int ball_diameter=20;
    Image image;
    Graphics graphics;
    Paddle p1,p2;
    Ball ball;
    Score score=new Score(width,height);
    Thread Gamethread;


    GamePanel()
    {
        setPreferredSize(screen);
        Gamethread=new Thread(this);
        Gamethread.start();
        addKeyListener(new AL());
        setFocusable(true);
        newPaddle();
        newBall();

    }

    private void newBall() {
        Random random=new Random();
        ball=new Ball(width/2,random.nextInt(height-ball_diameter),ball_diameter,ball_diameter);
    }

    private void newPaddle() {
        p1=new Paddle(0,(height-Paddle_height)/2,Paddle_width,Paddle_height,1);
        p2=new Paddle(width-Paddle_width, (height-Paddle_height)/2,Paddle_width,Paddle_height,2);
    }

    public void update(Graphics g){
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
//        super.paint(g);
        image=createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }

    private void draw(Graphics g) {
    p1.draw(g);
    p2.draw(g);
    ball.draw(g);
    score.draw(g);
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountofticks = 60;
        double ns= 1000000000/amountofticks;
        double delta=0;
        while(true){
            long Now = System.nanoTime();
            delta+=(Now-lastTime)/ns;
            lastTime=Now;
            if(delta >=1){
                move();
                repaint();
                Checkcollision();
                delta--;
            }
        }
    }

    private void Checkcollision() {
        if (ball.y<=0){
            ball.setYdirection(-ball.yVelocity);
        }if (ball.y>=height-ball_diameter){
            ball.setYdirection(-ball.yVelocity);
        }if (ball.intersects(p1)){
            ball.xVelocity=-ball.xVelocity;
            ball.xVelocity++;
            if (ball.yVelocity>0){
                ball.yVelocity++;
            }else {
                ball.yVelocity--;
            }
            ball.setYdirection(ball.yVelocity);
            ball.setXdirection(ball.xVelocity);
        }
        if (ball.intersects(p2)){
            ball.xVelocity=-ball.xVelocity;
            ball.xVelocity--;
            if (ball.yVelocity>0){
                ball.yVelocity++;
            }else {
                ball.yVelocity--;
            }
            ball.setYdirection(ball.yVelocity);
            ball.setXdirection(ball.xVelocity);
        }
        if (p1.y<=0){
            p1.y=0;
        }
        if (p1.y>=height-Paddle_height){
            p1.y=height-Paddle_height;
        }
        if (p2.y<=0){
            p2.y=0;
        }
        if (p2.y>=height-Paddle_height){
            p2.y=height-Paddle_height;
        }
        if (ball.x>=width-ball_diameter){
            newBall();
            newPaddle();
            score.player1++;
        }
        if (ball.x<=0){
            newPaddle();
            newBall();
            score.player2++;
        }
    }

    private void move() {
        p1.move();
        p2.move();
        ball.move();
    }

    public class AL extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            p1.KeyPressed(e);
            p2.KeyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            p1.KeyReleased(e);
            p2.KeyReleased(e);
        }
    }
}
