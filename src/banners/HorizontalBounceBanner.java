package banners;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

public class HorizontalBounceBanner extends Banner {

	private int dx;

	public HorizontalBounceBanner(String text, Dimension size) {
		super(text, size);
		dx = 0;
	}

	public HorizontalBounceBanner(String text, Dimension size, Font textFont, int alignment) {
		super(text, size, textFont, alignment);
		dx = 0;
	}

	public HorizontalBounceBanner(String text, Dimension size, Font textFont, int alignment, int dx) {
		super(text, size, textFont, alignment);
		this.dx = dx;
	}

	public int getDx() {
		return dx;
	}

	public void paint(Graphics g) {

		super.paint(g);

		int width = g.getFontMetrics(textFont).stringWidth(text);

		if (dx < 0) {
			if (textPoint.x <= 0)
				dx = -dx;
		}

		if (dx > 0) {
			if (textPoint.x + width >= size.width)
				dx = -dx;
		}

		textPoint.x += dx;

	}

	public void setDx(int dx) {
		this.dx = dx;
	}
}
