package banners;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.math.BigDecimal;

public class Banner {

	public static final int ALIGN_LEFT = 1;
	public static final int ALIGN_RIGHT = 2;
	public static final int ALIGN_TOP = 4;
	public static final int ALIGN_BOTTOM = 8;
	public static final int ALIGN_VCENTER = 16;
	public static final int ALIGN_HCENTER = 32;

	protected Dimension size;
	protected String text;
	protected Color textColor = Color.BLACK;
	protected Color bgColor = null;
	protected Point textPoint;
	protected Font textFont;
	protected double rotation;

	protected int alignment;
	protected boolean isAligned = false;
	protected Dimension offset;

	public Banner(String text, Dimension size) {
		this.text = text;
		this.size = size;
		textPoint = new Point(0, size.height);
		textFont = new Font(Font.SERIF, Font.BOLD, 20);
		setAlignment(ALIGN_VCENTER | ALIGN_HCENTER);
		setOffset(0, 0);
	}

	public Banner(String text, Dimension size, Font textFont, int alignment) {
		this.text = text;
		this.size = size;
		this.textPoint = new Point(0, size.height);
		this.alignment = alignment;
		this.textFont = textFont;
	}

	public void alignText(Graphics g) {

		if ((alignment & ALIGN_LEFT) == ALIGN_LEFT) {
			textPoint.x = offset.width;
		} else if ((alignment & ALIGN_HCENTER) == ALIGN_HCENTER) {
			textPoint.x = size.width / 2;
			FontMetrics fm = g.getFontMetrics(textFont);
			textPoint.x = textPoint.x - (fm.stringWidth(text) / 2) + offset.width;
		} else if ((alignment & ALIGN_RIGHT) == ALIGN_RIGHT) {
			textPoint.x = size.width;
			FontMetrics fm = g.getFontMetrics(textFont);
			textPoint.x = textPoint.x - (fm.stringWidth(text)) + offset.width;
		}

		if ((alignment & ALIGN_BOTTOM) == ALIGN_BOTTOM) {
			textPoint.y = size.height + offset.height;
		} else if ((alignment & ALIGN_VCENTER) == ALIGN_VCENTER) {
			textPoint.y = size.height / 2;
			FontMetrics fm = g.getFontMetrics(textFont);
			textPoint.y = textPoint.y + (fm.getMaxAscent() / 2) + offset.height;
		} else if ((alignment & ALIGN_TOP) == ALIGN_TOP) {
			FontMetrics fm = g.getFontMetrics(textFont);
			textPoint.y = (fm.getMaxAscent()) + offset.height;
		}
		isAligned = true;
	}

	public int getAlignment() {
		return alignment;
	}

	/**
	 * @return the bgColor
	 */
	public Color getBgColor() {
		return bgColor;
	}

	public Dimension getOffset() {
		return offset;
	}

	public double getRotation() {
		return rotation;
	}

	public Dimension getSize() {
		return size;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return the textColor
	 */
	public Color getTextColor() {
		return textColor;
	}

	/**
	 * @return the textFont
	 */
	public Font getTextFont() {
		return textFont;
	}

	/**
	 * @return the textPoint
	 */
	public Point getTextPoint() {
		return textPoint;
	}

	public void paint(Graphics g) {

		if (!isAligned) {
			alignText(g);
		}

		Graphics2D g2D = (Graphics2D) g;

		if (bgColor != null) {
			g2D.setColor(bgColor);
			g2D.fillRect(0, 0, size.width, size.height);
		}

		g2D.setColor(textColor);
		g2D.setFont(textFont);
		AffineTransform old = g2D.getTransform();
		AffineTransform rotate = (AffineTransform) old.clone();
		FontMetrics fm = g.getFontMetrics(textFont);
		rotate.rotate(rotation, textPoint.x + (fm.stringWidth(text) / 2),
				textPoint.y + fm.getDescent() - (fm.getHeight() / 2));
		g2D.transform(rotate);
		g2D.drawString(text, textPoint.x, textPoint.y);
		g2D.setTransform(old);
	}

	public void setAlignment(int alignment) {
		this.alignment = alignment;
		isAligned = false;
	}

	/**
	 * @param bgColor
	 *            the bgColor to set
	 */
	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}

	public void setOffset(int xOffset, int yOffset) {
		offset = new Dimension(xOffset, yOffset);
		isAligned = false;
	}

	public void setRotation(double rotation) {
		BigDecimal bd = new BigDecimal(rotation);
		BigDecimal r = bd.remainder(new BigDecimal(2 * Math.PI));

		this.rotation = r.doubleValue();
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
		isAligned = false;
	}

	/**
	 * @param textColor
	 *            the textColor to set
	 */
	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	/**
	 * @param textFont
	 *            the textFont to set
	 */
	public void setTextFont(Font textFont) {
		this.textFont = textFont;
		isAligned = false;
	}

	/**
	 * @param textPoint
	 *            the textPoint to set
	 */
	public void setTextPoint(Point textPoint) {
		this.textPoint = textPoint;
		isAligned = true;
	}

	public void setyOffset(Dimension offset) {
		this.offset = offset;
		isAligned = false;
	}
}
