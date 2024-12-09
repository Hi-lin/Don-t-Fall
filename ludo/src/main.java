import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;

import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import javax.sound.sampled.*;
import java.net.*;
import java.util.*;

public class main
{
	static File file; 
    static AudioInputStream stream; 
    static Clip music; 
	public static void main(String...args) throws Exception
	{
/*		file = new File("song.wav");
		stream = AudioSystem.getAudioInputStream(file);
		music = AudioSystem.getClip();
		music.open(stream);
		music.start(); //Start the music
		music.loop(Clip.LOOP_CONTINUOUSLY);*/
		JFrame j = new JFrame();  //JFrame is the window; window is a depricated class
		MyPanelb m = new MyPanelb();
		j.setSize(m.getSize());
		j.add(m); //adds the panel to the frame so that the picture will be drawn
			      //use setContentPane() sometimes works better then just add b/c of greater efficiency.

		j.setVisible(true); //allows the frame to be shown.



}
}

class MyPanelb extends JPanel implements ActionListener, MouseListener, KeyListener
{
	private Timer time;
	public static int x, y;
	public grid board = new grid();
	public Color brown = new Color(161, 102, 47);
	
	public MyPanelb()
	{
		time = new Timer(15, this); //sets delay to 15 millis and calls the actionPerformed of this class.
		setSize(2000, 1500);
		setVisible(true); //it's like calling the repaint method.
		time.start();
		addKeyListener(this);
		setFocusable(true);
		addMouseListener(this);
	
	} 
	
	public void paintComponent(Graphics g)
	{
		drawOver(g);
		x = 75; y = 55;
		g.setColor(board.cols[board.turn]);
		g.fillRect(90+4*x, 20+8*y, x, y);
		g.setColor(Color.yellow);
		g.fillRect(90+x*6, 20+8*y, x, y);
		g.setColor(Color.CYAN);
		g.fillRect(90+x*8, 20+8*y, x, y);
		for(int i = 0; i<10; i++) {
			g.setColor(Color.black);
			g.drawRect(90+i*x, 20, x, y);
			if(board.curpath[i]<=0) continue;
			g.setColor(brown);
			drawCracks(board.path[i]-board.curpath[i],
					90+i*x+1, 20+1, g);
			g.fillRect(90+i*x+1, 20+1, x-2, y-2);
		}
		for(int i = 0; i<10; i++) {
			g.setColor(Color.black);
			g.drawRect(90+10*x, 20+i*y, x, y);
			if(board.curpath[i+10]<=0) continue;
			g.setColor(brown);
			drawCracks(board.path[i+10]-board.curpath[i+10],
					90+10*x+1, 20+i*y+1, g);
			g.fillRect(90+10*x+1, 20+i*y+1, x-2, y-2);
		}
		for(int i = 0; i<10; i++) {
			g.setColor(Color.black);
			g.drawRect(90+x+i*x, 20+10*y, x, y);
			if(board.curpath[29-i]<=0) continue;
			g.setColor(brown);
			drawCracks(board.path[29-i]-board.curpath[29-i],
					90+x+i*x+1, 20+10*y+1, g);
			g.fillRect(90+x+i*x+1, 20+10*y+1, x-2, y-2);
		}
		for(int i = 0; i<10; i++) {
			g.setColor(Color.black);
			g.drawRect(90, 20+y+i*y, x, y);
			if(board.curpath[39-i]<=0) continue;
			g.setColor(brown);
			drawCracks(board.path[39-i]-board.curpath[39-i],
					90+1, 20+y+i*y+1, g);
			g.fillRect(90+1, 20+y+i*y+1, x-2, y-2);
		}
		for(int i = 0; i<40; i++) {
			peice t = board.road[i];
			if(t!=null) {
				int[] cords = getLoc(i);
				g.setColor(board.cols[t.side]);
				g.fillOval(cords[0]+x/3, cords[1]+y/8, x/3, y*25/55);
				int[] xp = new int[] {cords[0]+x/4, cords[0]+x*3/4, cords[0]+x/2};
				int[] yp = new int[] {cords[1]+y-5, cords[1]+y-5, cords[1]+y/3};
				g.fillPolygon(xp, yp, 3);
				//g.fillRect(cords[0]+x/4, cords[1]+y/4, x/2, y/2);
			}
		}
		Image title;
		try
		{
			title = ImageIO.read(new File("TitleImage.png"));
			//g.drawImage(title, 480, 300, null);
		}
		catch(Exception e)	{
		}
	}
	public void drawCracks(int a, int xc, int yc, Graphics g) {
		switch(a) {
		case(1): g.setColor(Color.blue); break;
		case(2): g.setColor(Color.GREEN); break;
		case(3): g.setColor(Color.yellow); break;
		case(4): g.setColor(Color.red); break;
		}
	}
	public void drawOver(Graphics g){
		//portal
		g.setColor(Color.black);
		g.fillRect(0, 0, 2000, 1500);
		g.setColor(new Color(17*15, 16*15, 12*15));
		g.fillRect(0, 0, 60, 1500);
		g.fillRect(944, 0, 500, 1500);
		g.setColor(Color.black);
		g.drawRect(0, 0, 60, 1500);
		g.drawRect(944, 0, 500, 1500);
		for(int i = 0; i<14; i++) {
			g.setColor(new Color(17*(13-i), 15*(13-i), 12*(13-i)));
			g.fillRect(60+i*3, 0, 3, 1500);
		}
		for(int i = 0; i<=15; i++) {
			g.setColor(new Color(17*(15-i), 15*(15-i), 12*(15-i)));
			g.fillRect(1000-(60+i*3), 0, 3, 1500);
		}
		
	}
	public void drawBob(Graphics g, int x, int y)
	{
		
		
		//Background
		

	}
	
	public void actionPerformed(ActionEvent e)
	{
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		repaint();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int xc = e.getX(); int yc = e.getY();
		System.out.println(xc+" "+yc+ " "+board.state);
		if(board.state==0) {
			int a = getsq(xc, yc);
			System.out.println(a);
			if(a!=-1) board.check(a);
			else if(yc>=20+8*y&&yc<=20+9*y) {
				System.out.println(9);
				if(xc>=x*6+90&&xc<=7*x+90) {
					System.out.println(1);
					board.state = 3;
				}
				if(xc>=x*8+90&&xc<=x*9+90) {
					if(board.road[board.turn*10]==null&&board.curpath[board.turn*10]!=0) {
						board.road[board.turn*10] = new peice(board.turn, board.turn*10);
						board.endt();
					}
					System.out.println(2);
				}
			}
		}
		if(board.state==1) {
			if(yc>=20+8*y&&yc<=20+9*y) {
				if(xc>=4*x&&xc<=90+5*x) {
					board.move((int)(Math.random()*6+1));
					board.endt();
				}
			}
		}
		if(board.state==3) {
			int a = getsq(xc, yc);
			System.out.println(xc+" "+yc+" "+a);
			if(a!=-1) {
				board.curpath[a] = board.path[a];
				board.endt();
			}
		}
		repaint();
	}
	public int[] getLoc(int a){
		if(a<10){
			return new int[]{90+a*x, 20};
		}
		a-=10;
		if(a<10){
			return new int[]{90+10*x, 20+a*y};
		}
		a-=10;
		if(a<10){
			return new int[]{90+(10-a)*x, 20+10*y};
		}
		a-=10;
		return new int[]{90, 20+(10-a)*y};
	}
	public int getsq(int xc, int yc) {
		if(xc>=90&&xc<90+10*x) {
			if(yc>=20&&yc<20+y) {
				return (xc-90)/x;
			}
		}
		if(xc>=90+10*x&&xc<90+11*x) {
			if(yc>=20&&yc<20+10*y) {
				return 10+(yc-20)/y;
			}
		}
		if(xc>=90+x&&xc<90+11*x) {
			if(yc>=20+10*y&&yc<20+11*y) {
				return 29-(xc-90-x)/x;
			}
		}
		if(xc>=90&&xc<90+x) {
			if(yc>=20+y&&yc<20+11*y) {
				return 39-(yc-20-y)/y;
			}
		}
		return -1;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
class peice{
	int side, loc, dist = 0;
	public peice(int a, int l) {
		side = a; loc = l;
	}
	public boolean same(peice p) {
		return side%2==p.side%2;
	}
}
class grid{
	Color[] cols = new Color[] {Color.red, Color.green,
							Color.blue, Color.yellow};
	int curpath[] = new int[40];
	peice[] road = new peice[40];
	peice curpc;
	int turn = 0;
	int state = 0;
	int[] path = new int[40];
	int[] points = new int[4];
	public grid() {
		for(int i = 1; i<10; i++) {
			int temp = (int)(Math.random()*3+2);
			path[i] = temp;
			path[i+10] = temp;
			path[i+20] = temp;
			path[i+30] = temp;
			curpath[i] = temp;
			curpath[i+10] = temp;
			curpath[i+20] = temp;
			curpath[i+30] = temp;
		}
		for(int i = 0; i<4; i++) {
			path[i*10] = Integer.MAX_VALUE;
			curpath[i*10] = Integer.MAX_VALUE;
			road[i*10] = new peice(i, i*10);
		}
	}
	public void check(int a) {
		if(road[a]==null) return;
		if(road[a].side!=turn) return;
		curpc = road[a];
		state++;
	}
	public void endt() {
		long t = System.currentTimeMillis();
		while(System.currentTimeMillis()-t<1000);
		endt2();
	}
	public void endt2() {
		state = 0; turn++; turn%=4;
		System.out.println("Turn "+turn);
	}
	public void move(int a) {
		state = 2;
		int x = curpc.loc+a; x%=40;
		curpc.dist+=a; road[curpc.loc]=null;
		if(curpc.dist>=40) {
			points[turn]++; return;
		}
		road[x] = null;
		
		curpath[x]--;
		if(curpath[x]>0) {
			road[x] = curpc;
		}
		curpc.loc = x;
	}
}




