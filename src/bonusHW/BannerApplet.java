package bonusHW;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.Random;

import javax.swing.JApplet;

import banners.Banner;
import banners.HorizontalBounceBanner;
import banners.RotatingBounceBanner;
import banners.VerticalBounceBanner;

@SuppressWarnings("serial")
public class BannerApplet extends JApplet implements Runnable {

	protected Image image;
	protected Graphics offscreen;
	
	private Banner b;
	private Thread bannerThread;
	private int delay = 100;
	
	private long ms = 0;
	
	private boolean playAll = false;
	
	public void init() {
		
		String text;
		
		String att = getParameter("delay");
		if(att != null) 
			delay = Integer.parseInt(att);
		
		att = getParameter("text");
		if(att != null)
			text = att;
		else text = "THIS IS A BANNER";
		
		att = getParameter("type");
		
		if(att != null) {
			int rand = 0;
			
			if(att.equalsIgnoreCase("all")) {
				att = "random";
				playAll = true;
			}
		
			if(att.equalsIgnoreCase("random")) {
				Random rng = new Random();
				rand = rng.nextInt(3) + 1;
			}
			
			if(att.equalsIgnoreCase("horizontal") || rand == 1) {
				HorizontalBounceBanner hb = new HorizontalBounceBanner(text,this.getSize());
				hb.setBgColor(Color.BLACK);
				hb.setTextColor(Color.GREEN);
				hb.setDx(2);
				
				b = hb;
			}
			if(att.equalsIgnoreCase("vertical") || rand == 2) {
				VerticalBounceBanner vb = new VerticalBounceBanner(text,this.getSize());
				vb.setBgColor(Color.BLACK);
				vb.setTextColor(Color.GREEN);
				vb.setDy(2);
				
				b = vb;
			}
			if(att.equalsIgnoreCase("circular") || rand == 3) {
				RotatingBounceBanner rb = new RotatingBounceBanner(text,this.getSize());
				rb.setBgColor(Color.BLACK);
				rb.setTextColor(Color.GREEN);
				rb.setdTheta(Math.PI / 100);
				
				b = rb;
			}
		}
		else {
			b = new Banner(text,this.getSize());
			b.setBgColor(Color.BLACK);
			b.setTextColor(Color.GREEN);
		}
	}
	
	public void start() {
		bannerThread = new Thread(this);
		bannerThread.start();
	}
	
	public void stop() {
		bannerThread = null;
	}
	
	public void run() {
		while(Thread.currentThread() == bannerThread) {
			try {
				if(ms >= 10000) {
					if(b instanceof HorizontalBounceBanner) {
						VerticalBounceBanner vb = new VerticalBounceBanner(b.getText(),this.getSize());
						Point tp = b.getTextPoint();
						double rotation = b.getRotation();
						vb.setTextPoint(tp);
						vb.setRotation(rotation);
						vb.setBgColor(Color.BLACK);
						vb.setTextColor(Color.GREEN);
						vb.setDy(2);
						
						b = vb;
					}
					else if(b instanceof VerticalBounceBanner) {
						RotatingBounceBanner rb = new RotatingBounceBanner(b.getText(),this.getSize());
						Point tp = b.getTextPoint();
						double rotation = b.getRotation();
						rb.setTextPoint(tp);
						rb.setRotation(rotation);
						rb.setBgColor(Color.BLACK);
						rb.setTextColor(Color.GREEN);
						rb.setdTheta(Math.PI / 100);
						
						b = rb;
					}
					else if(b instanceof RotatingBounceBanner) {
						HorizontalBounceBanner hb = new HorizontalBounceBanner(b.getText(),this.getSize());
						Point tp = b.getTextPoint();
						double rotation = b.getRotation();
						hb.setTextPoint(tp);
						hb.setRotation(rotation);
						hb.setBgColor(Color.BLACK);
						hb.setTextColor(Color.GREEN);
						hb.setDx(2);
						
						b = hb;
					}
					ms = 0;
				}
				
				Thread.sleep(delay);
				if(playAll) ms += delay;
			} catch(InterruptedException e) {
				
			}
			repaint();
		}

	}
	
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(b.getSize().width, b.getSize().height);
			offscreen = image.getGraphics();
		}
		
		b.paint(offscreen);
		
		g.drawImage(image, 0, 0, this);		
	}
	
	public void paint(Graphics g) {
		update(g);
	}
}
