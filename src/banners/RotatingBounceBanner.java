package banners;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

public class RotatingBounceBanner extends Banner {

	private double dTheta;

	public RotatingBounceBanner(String text, Dimension size) {
		super(text, size);
		dTheta = 0;
	}

	public RotatingBounceBanner(String text, Dimension size, Font textFont, int alignment) {
		super(text, size, textFont, alignment);
		dTheta = 0;
	}

	public RotatingBounceBanner(String text, Dimension size, Font textFont, int alignment, int dTheta) {
		super(text, size, textFont, alignment);
		this.dTheta = dTheta;
	}

	public double getdTheta() {
		return dTheta;
	}

	public void paint(Graphics g) {

		super.paint(g);

		if (dTheta < 0) {
			if (rotation <= 0)
				dTheta = -dTheta;
		}

		if (dTheta > 0) {
			if (rotation >= Math.PI)
				dTheta = -dTheta;
		}

		rotation += dTheta;

	}

	public void setdTheta(double d) {
		this.dTheta = d;
	}
}
