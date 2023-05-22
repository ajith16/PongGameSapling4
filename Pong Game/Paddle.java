import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle{
    int id;
    int yVelocity;
    int speed=10;


    Paddle(int x,int y,int Paddle_width, int Paddle_height,int id)
    {
        super(x,y,Paddle_width,Paddle_height);
        this.id=id;
    }
    public void KeyPressed(KeyEvent e){
        switch(id){
            case 1:
                if (e.getKeyCode()==KeyEvent.VK_W){
                    setYdirection(-speed);
                }
                if (e.getKeyCode()==KeyEvent.VK_S){
                    setYdirection(speed);
                }
                break;
            case 2:
                if (e.getKeyCode()==KeyEvent.VK_UP){
                    setYdirection(-speed);
                }
                if (e.getKeyCode()==KeyEvent.VK_DOWN){
                    setYdirection(speed);
                }
                break;
        }
    }

    private void setYdirection(int direction) {
        yVelocity=direction;
    }

    public void KeyReleased(KeyEvent e){
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYdirection(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYdirection(0);
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYdirection(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYdirection(0);
                }
                break;
        }
    }

    public void move()
    {
        y=y+yVelocity;
    }
    public void draw(Graphics g){
        if(id==1){
            g.setColor(Color.yellow);
        }else{
            g.setColor(Color.green);
        }
        g.fillRect(x,y,width,height);
    }
}
