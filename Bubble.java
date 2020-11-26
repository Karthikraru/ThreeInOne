
import java.awt.Color;
import java.util.Random;
public class Bubble {

    private int x;
    private int y;
    private int yVelocity;
    private int size;
    private int xVelocity;
    private Color color;



    public Bubble(){
        x = (int)(Math.random()*100);
        y = (int)(Math.random()*100);
        yVelocity = (int)(Math.random()*100);
        xVelocity = (int)(Math.random()*100);
        Random r = new Random();
        size = r.nextInt(20)+5;
        color = Color.getHSBColor(56, 156, 232);
    }
    public Bubble(int x){
        x = (int)(Math.random()*100);
        y = (int)(Math.random()*100);
        yVelocity = (int)(Math.random()*100);
        xVelocity = (int)(Math.random()*100);
        size = (int)(Math.random()*50);
        color = Color.getHSBColor(0, 0, 0);
    }
    public Bubble(int x, int y){
        x = (int)(Math.random()*100);
        y = (int)(Math.random()*100);
        yVelocity = (int)(Math.random()*100);
        xVelocity = (int)(Math.random()*100);
        size = (int)(Math.random()*50);
        color = Color.getHSBColor(5,26,77);
    }

    public int getxVelocity() {
        return xVelocity;
    }
    public Color getColor(){
        return color;
    }
    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public void makeBig(int randSize) {
        size += randSize;
    }
    public void makeSmall(int randSize) {
        size -=randSize;
    }

    public void addyVel(int Velocity) {
        y += Velocity;
    }
    public void addxVel(int Velocity) {
        x += Velocity;
    }

    public void setColor(Color x) {
        color = x;
    }
}


