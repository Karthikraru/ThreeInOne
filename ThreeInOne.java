
import java.awt.*;
import java.applet.*;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;

public class ThreeInOne extends Applet implements MouseListener, ActionListener, AWTEventListener
{
    //constants to control size, speed, number of circles...
    private final int DELAY = 1;
    private final int MAX_SIZE = 5000;
    private final int gameMaxSize = 25;
    private final int opticalMaxSize = 25;
    private final int MAX_CIRCLES = 300;
    private int gameMaxVelocity = 8;
    private final int opticalMaxVelocity = 30;
    private boolean colorChanger = false;
    private Button button1;
    private Button button2;
    private Button button3;
    private boolean opticalIllusion1 = false;
    private boolean clickSpawn1 = false;
    private boolean game1 = false;
    private ArrayList<Integer> keysDown;
    private double startTime;
    private double endTime;
    private double holdTime;
    private boolean flag = false;
    private int numCircle = 0;
    private final int numCircleOnScreen = 5;
    private int numCircleCurrentlyOnScreen = 0;
    private int Points = -5;
    private int userSpeed = 5;
    private int indexNewBubble = 1;
    private boolean firstTime = true;
    private String finalScoreStr;
    private int finalScoreInt = 999;
    private Bubble[] bubbleList;

    public void init()
    {
        this.resize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());

        keysDown = new ArrayList<Integer>();

        button1 = new Button("Optical Illusion!");
        button1.setBounds(50, 100, 80, 30);
        add(button1);
        button1.addActionListener(this);

        button2 = new Button("Click to Spawn!");
        button2.setBounds(50, 100, 80, 30);
        add(button2);
        button2.addActionListener(this);

        button3 = new Button("Game!");
        button3.setBounds(50, 100, 80, 30);
        add(button3);
        button3.addActionListener(this);
        //What is the purpose of this method?  State as a comment under this line.
        //To initialize the screen/method
        //Document this...what's going on in each line?...
        //Get screen height to know
        // where to start the bubble
//		button1 = new Button("Switch Game Modes");
//		add(button1);
//		button1.setBounds(0,0,50,30);
//		button1.setBackground(Color.gray);
//		button1.setForeground(Color.blue);
//		button1.addActionListener((ActionListener) this);

        //again, change these parallel arrays to make them better.
//		x = new int[MAX_CIRCLES];
//		y = new int[MAX_CIRCLES];
//		yvelocity = new int[MAX_CIRCLES];
//		size = new int[MAX_CIRCLES];
        bubbleList = new Bubble[MAX_CIRCLES];

        for(int count = 0;count < MAX_CIRCLES; count++)
        {
            if(colorChanger){
                bubbleList[count] = new Bubble(1);
                colorChanger=false;
            }
            else{
                bubbleList[count] = new Bubble();
                colorChanger=true;
            }
            resetCircle(count);
        }

        ActionListener taskPerformer = new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                repaint();
            }
        };

        new Timer(DELAY, taskPerformer).start();
        //addKeyListener((KeyListener)this);
        addMouseListener((MouseListener) this);
        this.getToolkit().addAWTEventListener(this, AWTEvent.KEY_EVENT_MASK);
    }

    //This method is to "double buffer".  If it wasn't here,
    //the animations would flicker.  No need to modify/comment anything
    //in this method.
    public void update(Graphics g)
    {
        Graphics offgc;
        Image offscreen = null;
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        offscreen = createImage(d.width, d.height);
        offgc = offscreen.getGraphics();

        offgc.setColor(getBackground());
        offgc.fillRect(0,0,d.width,d.height);
        offgc.setColor(getForeground());

        paint(offgc);

        g.drawImage(offscreen, 0, 0, this);

    }

    private void resetCircle(int index)
    {
        //What does this method do?  Comment under this line.  Also, again - fix the parallel array issue.
        //removes the circle after it moves off the screen
//		y[index] = 0;
//		x[index] = (int)(Math.random()*this.getWidth());
//		yvelocity[index] = (int)(Math.random()*MAX_VELOCITY)+2;
//		size[index] = (int)(Math.random()*MAX_SIZE);
        bubbleList[index].setY(999999);
        bubbleList[index].setX(999999);
        bubbleList[index].setyVelocity(0);
        bubbleList[index].setxVelocity(0);
        bubbleList[index].setSize(1);
    }

    private void resetCircle(int index, int x, int y)
    {
        bubbleList[index].setY(y);
        bubbleList[index].setX(x);
        bubbleList[index].setyVelocity((int)(Math.random()*opticalMaxVelocity)-(opticalMaxVelocity/2));
        bubbleList[index].setxVelocity((int)(Math.random()*opticalMaxVelocity)-(opticalMaxVelocity/2));
        bubbleList[index].setSize((int)(Math.random()*opticalMaxSize));
    }
    private void resetCircle(int index, int x, int y, int size)
    {
        resetCircle(index,x,y);
        bubbleList[index].setSize(size);
    }
    private void resetCircle(int index, int x, int y, String a)
    {
        bubbleList[index].setY(y);
        bubbleList[index].setX(x);
        bubbleList[index].setyVelocity((int)(Math.random()*30)-15);
        bubbleList[index].setxVelocity((int)(Math.random()*30)-15);
        Random r = new Random();
        int size = r.nextInt(30)+8;
        bubbleList[index].setSize(size);
    }
    private void resetCircle(int index, int x)
    {
        bubbleList[index].setSize((int)(Math.random()*MAX_SIZE)+25);
        bubbleList[index].setY((int)(Math.random()*(this.getHeight()-200))+50);
        bubbleList[index].setX((int)(Math.random()*(this.getWidth()-100))+50);
        bubbleList[index].setyVelocity((int)(Math.random()*gameMaxVelocity)-(gameMaxVelocity/2));
        bubbleList[index].setxVelocity((int)(Math.random()*gameMaxVelocity)-(gameMaxVelocity/2));
        bubbleList[index].setSize((int)(Math.random()*gameMaxSize)+25);

    }
    private void bounceSide(int index){
        bubbleList[index].setyVelocity(bubbleList[index].getyVelocity()*-1);

    }
    private void bounceTop(int index){
        bubbleList[index].setxVelocity(bubbleList[index].getxVelocity()*-1);
    }
    public void paint(Graphics g)
    {
        Ellipse2D circle;
        Graphics2D g2 = (Graphics2D)g;
        setBackground(Color.white);
        Font a = new Font("Serif", Font.BOLD, 62);
        g.setFont(a);
        g.setColor(Color.blue);
        g.drawString("Bubble Bounce", this.getWidth()/5*2, this.getHeight()/2-100);
        if(opticalIllusion1) {
            for(int count = 0;count < MAX_CIRCLES; count++)
            {
//				y[count] += yvelocity[count];  //moves the bubble
                bubbleList[count].addyVel(bubbleList[count].getyVelocity());
                bubbleList[count].addxVel(bubbleList[count].getxVelocity());
                g2.setPaint(bubbleList[count].getColor());
                if(bubbleList[count].getY()>(this.getHeight()-5)&&(bubbleList[count].getY()<9999))  //if bubble is not on screen
                {
                    bounceSide(count);
                    g2.setColor(Color.blue);
                }
                if(bubbleList[count].getX()>(this.getWidth()-5)&&(bubbleList[count].getX()<9999))  //if bubble is not on screen
                {
                    bounceTop(count);
                    g2.setColor(Color.blue);
                }
                if(bubbleList[count].getX()<0)  //if bubble is not on screen
                {
                    bounceTop(count);
                    g2.setColor(Color.blue);
                }
                if(bubbleList[count].getY()<0)  //if bubble is not on screen
                {
                    bounceSide(count);
                    g2.setColor(Color.blue);
                }
                circle = new Ellipse2D.Double(bubbleList[count].getX(), bubbleList[count].getY(), bubbleList[count].getSize(), bubbleList[count].getSize());
                g2.fill(circle);
                Font b = new Font("Serif", Font.BOLD, 35);
                g.setFont(b);
                g.setColor(Color.black);
                g.drawString("x", this.getWidth()/2-5, this.getHeight()/2-5);
                g.drawString("Click anywhere (I reccomend the x) and watch for at least 20 seconds", this.getWidth()/4, this.getHeight()/2+this.getHeight()/4);
            }
        }
        else if(clickSpawn1) {
            for(int count = 0;count < MAX_CIRCLES; count++)
            {
//				y[count] += yvelocity[count];  //moves the bubble
                bubbleList[count].addyVel(bubbleList[count].getyVelocity());
                bubbleList[count].addxVel(bubbleList[count].getxVelocity());
                g2.setPaint(bubbleList[count].getColor());


                if(bubbleList[count].getY()>(this.getHeight()-bubbleList[count].getSize())&&(bubbleList[count].getY()<9999))  //if bubble is not on screen
                {
                    bounceSide(count);
                    g2.setColor(Color.blue);
                }
                if(bubbleList[count].getX()>(this.getWidth()-bubbleList[count].getSize())&&(bubbleList[count].getX()<9999))  //if bubble is not on screen
                {
                    bounceTop(count);
                    g2.setColor(Color.blue);
                }
                if(bubbleList[count].getX()<0)  //if bubble is not on screen
                {
                    bounceTop(count);
                    g2.setColor(Color.blue);
                }
                if(bubbleList[count].getY()<0)  //if bubble is not on screen
                {
                    bounceSide(count);
                    g2.setColor(Color.blue);
                }



                //set the bubble color
                //circle = new Ellipse2D.Double(x[count], y[count], size[count], size[count]);
                circle = new Ellipse2D.Double(bubbleList[count].getX(), bubbleList[count].getY(), bubbleList[count].getSize(), bubbleList[count].getSize());
                g2.fill(circle);
            }
            Font c = new Font("Serif", Font.BOLD, 25);
            g.setFont(c);
            g.setColor(Color.black);
            g.setFont(c);
            g.drawString("Click anywhere to spawn a bubble of a random size (unlimited times)", this.getWidth()/3-this.getWidth()/22, this.getHeight()/2+this.getHeight()/4);
            g.drawString("Or hold and change the size! (The longer you hold the bigger the circle!)", this.getWidth()/3-this.getWidth()/22, this.getHeight()/2+this.getHeight()/3);

        }
        else if (game1) {
            Font d = new Font("Serif", Font.BOLD, 35);
            if(firstTime) {
                bubbleList[0].setX(this.getWidth()/2);
                bubbleList[0].setY(this.getHeight()/2);
                bubbleList[0].setSize(25);
                firstTime = false;
                finalScoreStr = JOptionPane.showInputDialog("How many points would you like to go to?");
                finalScoreInt = Integer.parseInt(finalScoreStr);
            }
            if(numCircleCurrentlyOnScreen != numCircleOnScreen) {
                numCircleCurrentlyOnScreen++;
                Points++;
                resetCircle(indexNewBubble,1);
                indexNewBubble++;
                userSpeed +=1;
                gameMaxVelocity += 2;
            }
            if((Points == finalScoreInt)) {
                Points++;
                JOptionPane.showMessageDialog(null, "You Win!");
            }
            //Document this...what's going on in each line?... there should be a comment for each line.
            if(bubbleList[0].getY()>(this.getHeight()-bubbleList[0].getSize())&&(bubbleList[0].getY()<9999))  //if bubble is not on screen
            {
                bubbleList[0].setY(bubbleList[0].getY()-20);
                g2.setColor(Color.blue);
            }
            if(bubbleList[0].getX()>(this.getWidth()-bubbleList[0].getSize())&&(bubbleList[0].getX()<9999))  //if bubble is not on screen
            {
                bubbleList[0].setX(bubbleList[0].getX()-20);
                g2.setColor(Color.blue);
            }
            if(bubbleList[0].getX()<0)  //if bubble is not on screen
            {
                bubbleList[0].setX(bubbleList[0].getX()+20);
                g2.setColor(Color.blue);
            }
            if(bubbleList[0].getY()<0)  //if bubble is not on screen
            {
                bubbleList[0].setY(bubbleList[0].getY()+20);
                g2.setColor(Color.blue);
            }

            circle = new Ellipse2D.Double(bubbleList[0].getX(), bubbleList[0].getY(), bubbleList[0].getSize(), bubbleList[0].getSize());
            g2.setPaint(Color.ORANGE);
            g2.fill(circle);

            for(int count = 1;count < MAX_CIRCLES; count++)
            {
                int xDistance = Math.abs(bubbleList[0].getX()-bubbleList[count].getX());
                int yDistance = Math.abs(bubbleList[0].getY()-bubbleList[count].getY());
//				y[count] += yvelocity[count];  //moves the bubble
                bubbleList[count].addyVel(bubbleList[count].getyVelocity());
                bubbleList[count].addxVel(bubbleList[count].getxVelocity());
                g2.setPaint(bubbleList[count].getColor());

                if(bubbleList[count].getY()>(this.getHeight()-bubbleList[count].getSize())&&(bubbleList[count].getY()<9999))  //if bubble is not on screen
                {
                    bounceSide(count);
                    g2.setColor(Color.blue);
                }
                if(bubbleList[count].getX()>(this.getWidth()-bubbleList[count].getSize())&&(bubbleList[count].getX()<9999))  //if bubble is not on screen
                {
                    bounceTop(count);
                    g2.setColor(Color.blue);
                }
                if(bubbleList[count].getX()<0)  //if bubble is not on screen
                {
                    bounceTop(count);
                    g2.setColor(Color.blue);
                }
                if(bubbleList[count].getY()<0)  //if bubble is not on screen
                {
                    bounceSide(count);
                    g2.setColor(Color.blue);
                }
                if((xDistance<=bubbleList[count].getSize())&&(yDistance<=bubbleList[count].getSize()))  //if bubble is not on screen
                {
                    resetCircle(count);
                    numCircleCurrentlyOnScreen--;
                }


                //set the bubble color
                //circle = new Ellipse2D.Double(x[count], y[count], size[count], size[count]);
                circle = new Ellipse2D.Double(bubbleList[count].getX(), bubbleList[count].getY(), bubbleList[count].getSize(), bubbleList[count].getSize());
                g2.fill(circle);
                g.setColor(Color.BLACK);
                g.setFont(d);
                g.drawString("Points: " + Points, 50, 50);
                g.drawString("To win, get " + finalScoreInt + " points by running into the bubbles", this.getWidth()/3, this.getHeight()*2/3);
            }
        }

    }

    public void actionPerformed(ActionEvent e) {
        opticalIllusion1 = false;
        clickSpawn1 = false;
        game1 = false;
        if(e.getSource() == button1) {
            opticalIllusion1 = true;
        }
        else if (e.getSource() == button2) {
            clickSpawn1 = true;
        }
        else if (e.getSource() == button3) {
            game1 = true;
            gameMaxVelocity = 8;
            firstTime = true;
            numCircleCurrentlyOnScreen = 0;
            Points = -5;
            userSpeed = 5;
            indexNewBubble = 1;
        }
        for(int count = 0;count < MAX_CIRCLES; count++) {
            resetCircle(count);
        }
    }

    public void mouseClicked(MouseEvent e) {
        if(opticalIllusion1) {
            int x = e.getX();
            int y = e.getY();
            for(int count = 0;count < MAX_CIRCLES; count++)
                resetCircle(count,x,y);
        }
        else if(clickSpawn1) {
            int x = e.getX();
            int y = e.getY();
            if(holdTime<1) {
                resetCircle(numCircle,x,y,"a");
                numCircle++;
            }
        }

    }


    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }


    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }


    public void mousePressed(MouseEvent arg0) {
        if(clickSpawn1) {
            startTime = System.nanoTime();
            flag = true;
        }

    }


    public void mouseReleased(MouseEvent e) {
        if(clickSpawn1)   {
            if(flag) {
                endTime = System.nanoTime();
                flag = false;
            }
            holdTime = (endTime - startTime) / Math.pow(10,9);
            int x = e.getX();
            int y = e.getY();
            if(holdTime>=1) {
                if(holdTime>100) {
                    resetCircle(numCircle,x,y,(int)500);
                }
                resetCircle(numCircle,x,y,(int)holdTime*75);
                numCircle++;
            }
        }

    }

    public void moveCircle() {
        int x = bubbleList[0].getX();
        int y = bubbleList[0].getY();
        if(keysDown.contains(new Integer(KeyEvent.VK_UP)))
            y -= userSpeed;
        if(keysDown.contains(new Integer(KeyEvent.VK_DOWN)))
            y += userSpeed;
        if(keysDown.contains(new Integer(KeyEvent.VK_LEFT)))
            x -= userSpeed;
        if(keysDown.contains(new Integer(KeyEvent.VK_RIGHT)))
            x += userSpeed;
        bubbleList[0].setX(x);
        bubbleList[0].setY(y);
    }

	/*@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("Released");
		if(game1) {
			keysDown.remove(new Integer(e.getKeyCode()));
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println("Pressed");
		if(game1) {
			if(!keysDown.contains(e.getKeyCode())) {
				keysDown.add(new Integer(e.getKeyCode()));
			}
			moveCircle();
		}

	}*/

//	public void keyPressed(KeyEvent e) {
//		if(!keysDown.contains(e.getKeyCode())) {
//			keysDown.add(new Integer(e.getKeyCode()));
//		}
//		moveCircle();
//	}
//
//	public void keyReleased(KeyEvent e) {
//		keysDown.remove(new Integer(e.getKeyCode()));
//
//	}
//
//	public void keyTyped(KeyEvent e) {
//		// TODO Auto-generated method stub
//
//	}


    public void eventDispatched(AWTEvent event) {
        // TODO Auto-generated method stub
        if(game1){
            if(event instanceof KeyEvent){
                KeyEvent key = (KeyEvent)event;
                if(!keysDown.contains(key.getKeyCode())) {
                    keysDown.add(new Integer(key.getKeyCode()));
                    moveCircle();
                    keysDown.remove(new Integer(key.getKeyCode()));
                }
            }
        }
    }

}

/*FINALLY - change this to make it your own.  Make different colors (randomize it, if you want).
 *  Change the starting spot of the bubbles.  Change the size.  Do something to make it your own.
 *  Try to make some kind of video game out of it (I'm thinking pong...).  */











