package com.dxmio.gui.linepainter;

import java.awt.event.ActionEvent;
import java.util.Enumeration;
import javax.swing.*;

/**
 * @author byte
 * 
 * Represents the width menu action.
 */
@SuppressWarnings("serial")
public class WidthMenuAction extends AbstractAction
{
    private BasicCanvas target;
    private int selWidth;
    private ButtonGroup butgrp;

    /**
     * Instantiates a new width menu action.
     * 
     * @param theCanvas The canvas to use.
     * @param w The width.
     */
    public WidthMenuAction(BasicCanvas theCanvas, int w)
    {
        selWidth = w;
        target = theCanvas;
        //target.setWidth(w);
        String label = w+" Pixel";
        
        putValue(SHORT_DESCRIPTION, "Changes the width of the line.");
        putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/images/Width.gif")));
        putValue(NAME, label);
        putValue("WIDTH", new Integer(w));
    }

    /**
     * Instantiates a new width menu action.
     * 
     * @param theCanvas The canvas to use.
     * @param w The width.
     * @param bg The button group.
     */
    public WidthMenuAction(BasicCanvas theCanvas, int w, ButtonGroup bg)
    {
        this(theCanvas, w);
        butgrp = bg;
    }

    public void actionPerformed(ActionEvent e)
    {
        if(selWidth == -1)
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
 
        
            target.nextWidth();
        }
        else
        {
            target.setWidth(selWidth);
        }

    }
}
