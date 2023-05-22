import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle{
    int xVelocity;
    int yVelocity;
    int initialSpeed=5;
    Random random;
    Ball(int x,int y,int width,int height){
        super(x, y, width, height);
        random=new Random();
        int RandomXdirection = random.nextInt(2);
        if (RandomXdirection==0){
            RandomXdirection--;
        }else {

        }
        setXdirection(RandomXdirection);
        int RandomYdirection = random.nextInt(2);
        if (RandomYdirection==0){
            RandomYdirection--;
        }
        setYdirection(RandomYdirection);
    }

    public void setYdirection(int randomYdirection) {
        yVelocity=randomYdirection;
    }

    public void setXdirection(int randomXdirection) {
        xVelocity=randomXdirection;
    }

    public void move(){
        x+=xVelocity;
        y+=yVelocity;
    }
    public void draw(Graphics G)
    {
        G.setColor(Color.white);
        G.fillOval(x,y,width,height);

        G.setColor(Color.white);
        G.drawLine(1000/2,0,1000/2,555);
    }
}
