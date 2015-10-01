package banners;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

public class VerticalBounceBanner extends Banner {

	private int dy;

	public VerticalBounceBanner(String text, Dimension size) {
		super(text, size);
		dy = 0;
	}

	public VerticalBounceBanner(String text, Dimension size, Font textFont, int alignment) {
		super(text, size, textFont, alignment);
		dy = 0;
	}

	public VerticalBounceBanner(String text, Dimension size, Font textFont, int alignment, int dy) {
		super(text, size, textFont, alignment);
		this.dy = dy;
	}

	public int getDy() {
		return dy;
	}

	public void paint(Graphics g) {

		super.paint(g);

		int ascent = g.getFontMetrics(textFont).getMaxAscent();
		int descent = g.getFontMetrics(textFont).getMaxDescent();

		if (dy < 0) {
			if (textPoint.y - ascent <= 0)
				dy = -dy;
		}

		if (dy > 0) {
			if (textPoint.y + descent >= size.height)
				dy = -dy;
		}

		textPoint.y += dy;

	}

	public void setDy(int dy) {
		this.dy = dy;
	}
}