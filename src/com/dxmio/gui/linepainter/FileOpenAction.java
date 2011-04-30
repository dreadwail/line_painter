package com.dxmio.gui.linepainter;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

/**
 * @author byte
 *
 * Represents the file-open action.
 */
@SuppressWarnings("serial")
public class FileOpenAction extends AbstractAction
{
    private BasicCanvas target;

    /**
     * Instantiates a new file-open action.
     * 
     * @param theCanvas The canvas to use.
     */
    public FileOpenAction(BasicCanvas theCanvas)
    {
        target = theCanvas;
        
        putValue(SHORT_DESCRIPTION, "Opens an existing painting.");
        putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/images/Open.gif")));
        putValue(NAME, "Open") ;
        putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_O)) ;
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
    }

    public void actionPerformed(ActionEvent e)
    {
      //Always prompt for a save filename in this case, but 
      //default the name to the current name in the file chooser.
      //JChooser.setSelectedFile()
        //target.save(true);
        target.open();
        String filename = target.m_file.getName();
        target.m_frame.setTitle(filename);
    }
}
