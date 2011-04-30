package com.dxmio.gui.linepainter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import javax.swing.*;

/**
 * @author byte
 *
 * Represents the action for the color menu.
 */
@SuppressWarnings("serial")
public class ColorMenuAction extends AbstractAction
{
    private BasicCanvas target;
    private Color selColor;
    private ButtonGroup butgrp;

    /**
     * Instantiates a new color menu action.
     * 
     * @param theCanvas The canvas to use.
     * @param theColor The color to use.
     * @param colorName The color name.
     */
    public ColorMenuAction(BasicCanvas theCanvas, Color theColor, String colorName)
    {
        target = theCanvas;
        selColor = theColor;

        //target.setColor(theColor);
        putValue(SHORT_DESCRIPTION, "Changes the color of the line.");
        putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/images/Color.gif")));
        putValue(NAME, colorName);
        //putValue("WIDTH", new Integer(w));
    }

    /**
     * Instantiates a new color menu action.
     * 
     * @param theCanvas The canvas to use.
     * @param theColor The color to use.
     * @param colorName The color name.
     * @param bg The button group.
     */
    public ColorMenuAction(BasicCanvas theCanvas, Color theColor, String colorName, ButtonGroup bg)
    {
        this(theCanvas, theColor, colorName);
        butgrp = bg;
    }

    public void actionPerformed(ActionEvent e)
    {
        if(selColor == null)
        {
            AbstractButton tmpbut = null;

            Enumeration<AbstractButton> enu = butgrp.getElements();
            while(enu.hasMoreElements())
            {
                tmpbut = (AbstractButton)enu.nextElement();
                if(tmpbut.isSelected())
                    break;
            }
            if(enu.hasMoreElements())
            {
                tmpbut = (AbstractButton)enu.nextElement();
            }
            else
            {
                Enumeration<AbstractButton> enu2 = butgrp.getElements();
                tmpbut = (AbstractButton)enu2.nextElement();
            }
            tmpbut.setSelected(true);
        
            target.nextColor();
        }
        else
        {
            target.setColor(selColor);
        }
    }
}
