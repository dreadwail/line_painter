package com.dxmio.gui.linepainter;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * @author byte
 *
 * Represents the file-saveas action.
 */
@SuppressWarnings("serial")
public class FileSaveAsAction extends AbstractAction
{
    private BasicCanvas target;

    /**
     * Instantiates a new file-saveas action.
     * 
     * @param theCanvas The canvas to use.
     */
    public FileSaveAsAction(BasicCanvas theCanvas)
    {
        target = theCanvas;

        putValue(NAME, "Save As");
        putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/images/Save.gif")));
        //putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_S));
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
    }

    public void actionPerformed(ActionEvent e)
    {
        target.save(true);
        String filename = target.m_file.getName();
        target.m_frame.setTitle(filename);
    }
}
