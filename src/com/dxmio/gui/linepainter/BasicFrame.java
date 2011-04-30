package com.dxmio.gui.linepainter;
/**
 * @author Ben Lakey
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
class BasicFrame extends JFrame implements ActionListener
{

    protected BasicCanvas m_canvas;
    protected JLabel statusBar;

    // constructor
    public BasicFrame()
    {
        statusBar = new JLabel("");
        m_canvas = new BasicCanvas(Color.BLACK, 1, statusBar, this);
        //setBackground(Color.GRAY);

        setTitle("(noname)");

        setSize(400, 400);

        addWindowListener(
            new WindowAdapter()
            {
                public void windowClosing(WindowEvent e)
                {
                    onShutdown();
                }
            });

        JToolBar toolBar = new JToolBar();
        JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = new JMenu("File");
        JMenu menuWidth = new JMenu("Width");
        JMenu menuColor = new JMenu("Color");
        JMenu menuHelp = new JMenu("Help");

        JMenuItem menuFileExit = new JMenuItem("Exit", 'x');
        menuFile.setMnemonic('f');
        menuFileExit.setMnemonic('e');
        menuFileExit.addActionListener(this);

        JMenuItem menuHelpAbout = new JMenuItem("About", 'a');
        menuHelp.setMnemonic('h');
        menuHelpAbout.setMnemonic('a');
        menuHelpAbout.addActionListener(this);
        menuHelp.add(menuHelpAbout);

        FileNewAction fileNewAction = new FileNewAction(m_canvas);
        FileOpenAction fileOpenAction = new FileOpenAction(m_canvas);
        FileSaveAction fileSaveAction = new FileSaveAction(m_canvas);
        FileSaveAsAction fileSaveAsAction = new FileSaveAsAction(m_canvas);

        JMenuItem menuFileNew = new JMenuItem(fileNewAction);
        JMenuItem menuFileOpen = new JMenuItem(fileOpenAction);
        JMenuItem menuFileSave = new JMenuItem(fileSaveAction);
        JMenuItem menuFileSaveAs = new JMenuItem(fileSaveAsAction);

        menuFile.add(menuFileNew);
        menuFile.add(menuFileOpen);
        menuFile.add(menuFileSave);
        menuFile.add(menuFileSaveAs);
        menuFile.add(menuFileExit);

        ButtonGroup widthGroup = new ButtonGroup();

        WidthMenuAction widthMenuAction1 = new WidthMenuAction(m_canvas, 1, widthGroup);
        WidthMenuAction widthMenuAction4 = new WidthMenuAction(m_canvas, 4, widthGroup);
        WidthMenuAction widthMenuAction8 = new WidthMenuAction(m_canvas, 8, widthGroup);

        JRadioButtonMenuItem pixelCount1 = new JRadioButtonMenuItem(widthMenuAction1);
        JRadioButtonMenuItem pixelCount4 = new JRadioButtonMenuItem(widthMenuAction4);
        JRadioButtonMenuItem pixelCount8 = new JRadioButtonMenuItem(widthMenuAction8);

        pixelCount1.setIcon(null);
        pixelCount4.setIcon(null);
        pixelCount8.setIcon(null);
        menuWidth.add(pixelCount1);
        menuWidth.add(pixelCount4);
        menuWidth.add(pixelCount8);

        widthGroup.add(pixelCount1);
        widthGroup.add(pixelCount4);
        widthGroup.add(pixelCount8);

        widthGroup.setSelected(pixelCount1.getModel(), true);

        ButtonGroup colorGroup = new ButtonGroup();

        ColorMenuAction colorBlackAction = new ColorMenuAction(m_canvas, Color.BLACK, "Black", colorGroup);
        ColorMenuAction colorRedAction = new ColorMenuAction(m_canvas, Color.RED, "Red", colorGroup);
        ColorMenuAction colorGreenAction = new ColorMenuAction(m_canvas, Color.GREEN, "Green", colorGroup);
        ColorMenuAction colorBlueAction = new ColorMenuAction(m_canvas, Color.BLUE, "Blue", colorGroup);

        JRadioButtonMenuItem colorBlack = new JRadioButtonMenuItem(colorBlackAction);
        JRadioButtonMenuItem colorRed = new JRadioButtonMenuItem(colorRedAction);
        JRadioButtonMenuItem colorGreen = new JRadioButtonMenuItem(colorGreenAction);
        JRadioButtonMenuItem colorBlue = new JRadioButtonMenuItem(colorBlueAction);

        colorBlack.setIcon(null);
        colorRed.setIcon(null);
        colorGreen.setIcon(null);
        colorBlue.setIcon(null);
        menuColor.add(colorBlack);
        menuColor.add(colorRed);
        menuColor.add(colorGreen);
        menuColor.add(colorBlue);

        colorGroup.add(colorBlack);
        colorGroup.add(colorRed);
        colorGroup.add(colorGreen);
        colorGroup.add(colorBlue);

        colorGroup.setSelected(colorBlack.getModel(), true);
        
        menuBar.add(menuFile);
        menuBar.add(menuWidth);
        menuBar.add(menuColor);
        menuBar.add(menuHelp);

        setJMenuBar(menuBar);

        WidthMenuAction widthCycleAction = new WidthMenuAction(m_canvas, -1, widthGroup);
        ColorMenuAction colorCycleAction = new ColorMenuAction(m_canvas, null, null, colorGroup);

        toolBar.add(fileNewAction);
        toolBar.add(fileOpenAction);
        toolBar.add(fileSaveAction);
        toolBar.addSeparator();
        toolBar.add(widthCycleAction);
        toolBar.addSeparator();
        toolBar.add(colorCycleAction);

        JScrollPane myScrollPane = new JScrollPane(m_canvas);
        getContentPane().add(myScrollPane);
        getContentPane().add(toolBar, BorderLayout.NORTH);
        getContentPane().add(statusBar, BorderLayout.SOUTH);

    }

    public void setStatus(String sts)
    {
        statusBar.setText(sts);
    }

    public void actionPerformed(ActionEvent e)
    {
        String action = e.getActionCommand();
        if (action.compareTo("Exit") == 0)
            onShutdown();
        else if (action.compareTo("About") == 0)
            onAbout();
    }

    // Shutdown procedure
    protected void onShutdown()
    {
        System.exit(0);
    }

    // About Box
    protected void onAbout()
    {
        JOptionPane.showMessageDialog(null, "(c) Ben Lakey Lab3");
    }

}