package com.dxmio.gui.linepainter;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

/**
 * @author byte
 *
 * Represents the file-save action.
 */
@SuppressWarnings("serial")
public class FileSaveAction extends AbstractAction
{
    private BasicCanvas target;

    /**
     * Instantiates a new file-save action.
     * 
     * @param theCanvas The canvas to use.
     */
    public FileSaveAction(BasicCanvas theCanvas)
    {
        target = theCanvas;
        
        putValue(SHORT_DESCRIPTION, "Saves this painting surface.");
        putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/images/Save.gif")));
        putValue(NAME, "Save") ;
        putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_S)) ;
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
    }

    public void actionPerformed(ActionEvent e)
    {
        target.save(false);
        String filename = target.m_file.getName();
        target.m_frame.setTitle(filename);
    }
}
