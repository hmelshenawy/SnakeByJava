package snake;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
//import javax.swing.JPanel;
import javax.swing.Timer;
//import sun.applet.AppletPanel;

import javafx.embed.swing.JFXPanel;

import java.applet.Applet;
//import java.applet.AppletContext;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gameply extends JPanel implements KeyListener, ActionListener {
	public ImageIcon titleIcon;
	int[] xpos = new int[750];
	int[] ypos = new int[750];
	int[] enemyx = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,
			375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,
			750,775,800,825,850};
	int[] enemyy= {75,100,125,150,175,200,225,250,275,300,325,350,375,400,
					425,450,475,500,525,550,575,600,625};
	
	Random r = new Random();
	int enmX = r.nextInt(34);
	int enmY = r.nextInt(23);
	int score = 0;
	
	boolean right =false;
	boolean left =false;
	boolean up =false;
	boolean down =false;
	
	Timer timer;
	int delay=100;
	int snakelenght = 3;
	int moves =0;
	
	ImageIcon rightmouth;
	ImageIcon leftmouth;
	ImageIcon upmouth;
	ImageIcon downmouth;
	ImageIcon snakebody;
	ImageIcon enemy;

	
	
	public Gameply() {
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		
	}
	
	public void paint(Graphics g) {
		
		if (moves ==0) {
			xpos[2]=50;
			xpos[1]=75;
			xpos[0]=100;
			
			ypos[2]=100;
			ypos[1]=100;
			ypos[0]=100;
			right = true;
			left=false;
			up=false;
			down=false;
					
		}
		
		//Draw icon image rectangle
		g.setColor(Color.BLACK);
		g.drawRect(24, 10, 851, 55);
		titleIcon= new ImageIcon("snaketitle.jpg");
		titleIcon.paintIcon(getParent(), g, 25, 11);
		
		//Draw score 
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.ITALIC, 14));
		g.drawString("Score:"+score, 780, 30);
		g.setColor(Color.white);
		g.drawString("Lenght:"+snakelenght, 780, 50);
		
		
		//Draw gameplay rectangle
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 5);
		g.setColor(Color.BLACK);
		g.fillRect(25, 75, 850, 575);
		
//		rightmouth= new ImageIcon("rightmouth.png");
//		rightmouth.paintIcon(this, g, xpos[0], ypos[0]);
		
		for (int i = 0; i < snakelenght; i++) {
			
			if (i ==0 && right) {
				rightmouth= new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(getParent(), g, xpos[i], ypos[i]);
			}
			if (i ==0 && left) {
				leftmouth= new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(getParent(), g, xpos[i], ypos[i]);
			}
			if (i ==0 && up) {
				upmouth= new ImageIcon("upmouth.png");
				upmouth.paintIcon(getParent(), g, xpos[i], ypos[i]);
			}
			if (i ==0 && down) {
				downmouth= new ImageIcon("downmouth.png");
				downmouth.paintIcon(getParent(), g, xpos[i], ypos[i]);
			}
			if (i != 0) {
				snakebody= new ImageIcon("snakeimage.png");
				snakebody.paintIcon(getParent(), g, xpos[i], ypos[i]);
			}
		}
		enemy = new ImageIcon("enemy.png");
		enemy.paintIcon(getParent(), g, enemyx[enmX], enemyy[enmY]);
		
		if (enemyx[enmX]==xpos[0] && enemyy[enmY]== ypos[0]) {
			snakelenght++;
			score++;
			enmX = r.nextInt(34);
			enmY= r.nextInt(23);
		}
		
		for (int i = 1; i <= snakelenght; i++) {
			if (xpos[i]== xpos[0] && ypos[i]== ypos[0]) {
				right = false;
				left = false;
				up= false;
				down= false;
				moves=0;
				timer.stop();
				
				g.setColor(Color.RED);
				g.setFont(new Font("arial", Font.HANGING_BASELINE, 50));
				g.drawString("Game Over !!", 300, 300);
			}
		}
		
		//g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(right) {
			
			for (int i = snakelenght-1; i >= 0; i--) {
				ypos[i+1]= ypos[i];
			}
			for (int i = snakelenght; i >=0; i--) {
				
				if (i==0) {
					xpos[i]=xpos[i]+25;
				}
				if (i!=0) {
					xpos[i]=xpos[i-1];
				}
				if (xpos[i]>850) {
					xpos[i]=25;
				}
			}
			repaint();
		}
if(left) {
			
			for (int i = snakelenght-1; i >= 0; i--) {
				ypos[i+1]= ypos[i];
			}
			for (int i = snakelenght; i >=0; i--) {
				
				if (i==0) {
					xpos[i]=xpos[i]-25;
				}
				if (i!=0) {
					xpos[i]=xpos[i-1];
				}
				if (xpos[i]<25) {
					xpos[i]=850;
				}
			}
			repaint();
		}
if(down) {
	
	for (int i = snakelenght-1; i >= 0; i--) {
		xpos[i+1]= xpos[i];
	}
	for (int i = snakelenght; i >=0; i--) {
		
		if (i==0) {
			ypos[i]=ypos[i]+25;
		}
		if (i!=0) {
			ypos[i]=ypos[i-1];
		}
		if (ypos[i]>625) {
			ypos[i]=75;
		}
	}
	repaint();
}
if(up) {
	
	for (int i = snakelenght-1; i >= 0; i--) {
		xpos[i+1]= xpos[i];
	}
	for (int i = snakelenght; i >=0; i--) {
		
		if (i==0) {
			ypos[i]=ypos[i]-25;
		}
		if (i!=0) {
			ypos[i]=ypos[i-1];
		}
		if (ypos[i]<75) {
			ypos[i]=625;
		}
	}
	repaint();
}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			if (!left) {
				moves++;
				right = true;
				left =false;
				up= false;
				down = false;
			}
			if(left) {}
		}
	if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			
			if (!right) {
				moves++;
				right = false;
				left =true;
				up= false;
				down = false;
			}
			if(right) {}
		}
	if (e.getKeyCode() == KeyEvent.VK_UP) {
		
		if (!down) {
			moves++;
			right = false;
			left =false;
			up= true;
			down = false;
		}
		if(down) {}
	}
	if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		
		if (!up) {
			moves++;
			right = false;
			left =false;
			up= false;
			down = true;
		}
		if(up) {}
	}
	if (e.getKeyCode()==KeyEvent.VK_SPACE) {
		if (timer.isRunning()) {
			timer.stop();
		}
		else timer.start();
	}
	if (e.getKeyCode()==KeyEvent.VK_R) {
		moves = 0;
		score =0;
		snakelenght=3;
//		right=true;
//		left=false;
//		up=false;
//		down=false;
		repaint();
		timer.restart();
		
		
	}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
