package com.dxmio.gui.linepainter;
/**
 * @author Dreadwail
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;


/**
 * @author byte
 *
 * Represents a line.
 */
@SuppressWarnings("serial")
public class MyLine implements Serializable
{

    protected Point m_ptFrom;
    protected Point m_ptTo;
    protected Color selColor;
    protected int selWidth;

    /**
     * Instantiates a new line.
     * 
     * @param from The point to draw from.
     * @param to The point to draw to.
     * @param theColor The color.
     * @param theWidth The width.
     */
    public MyLine(Point from, Point to, Color theColor, int theWidth)
    {
        m_ptFrom = from;
        m_ptTo = to;
        selColor = theColor;
        selWidth = theWidth;
    }

    /**
     * Draws the line.
     * 
     * @param myGraphics The graphics object to use.
     */
    public void draw(Graphics2D myGraphics)
    {
        myGraphics.setColor(selColor);
        myGraphics.setStroke(new BasicStroke(selWidth));
        myGraphics.drawLine(m_ptFrom.x, m_ptFrom.y, m_ptTo.x, m_ptTo.y);
    }

}
