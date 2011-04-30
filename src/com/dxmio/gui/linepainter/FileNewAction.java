package com.dxmio.gui.linepainter;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

/**
 * @author byte
 * 
 * Represents the file-new action.
 */
@SuppressWarnings("serial")
public class FileNewAction extends AbstractAction
{
    private BasicCanvas target;

    /**
     * Instantiates a new file-new action.
     * 
     * @param theCanvas The canvas to use.
     */
    public FileNewAction(BasicCanvas theCanvas)
    {

        target = theCanvas;
        
        putValue(SHORT_DESCRIPTION, "Creates a new painting surface.");
        putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/images/New.gif")));
        putValue(NAME, "New") ;
        putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_N)) ;
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
    }

    public void actionPerformed(ActionEvent e)
    {
        target.clear();
        target.emptyLineCollection();
        target.repaint(); //redrawCanvas();
        target.m_frame.setTitle("(noname)");
    }
}
