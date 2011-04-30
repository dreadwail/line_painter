package com.dxmio.gui.linepainter;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 * @author Ben Lakey
 */

@SuppressWarnings("serial")
class BasicCanvas extends JPanel implements MouseListener, MouseMotionListener
{

    protected Point m_ptFrom = new Point(0, 0);
    protected Point m_ptTo = new Point(0, 0);
    protected LinkedList<MyLine> myList;

    protected Color selColor = Color.BLACK;
    protected int selWidth = 1;
    protected JLabel statusBar;
    
    protected boolean m_bNeedSave = false;
    protected File m_file = null;
    
    protected BasicFrame m_frame;

    public BasicCanvas(Color initialColor, int initialWidth, JLabel sts, BasicFrame theFrame)
    {
        selColor = initialColor;
        selWidth = initialWidth;
        statusBar = sts;
        m_frame = theFrame;

        

        setBackground(Color.WHITE);
        setMaximumSize(new Dimension(400, 400));

        myList = new LinkedList<MyLine>();

        setLayout(new BorderLayout());

        addMouseListener(this);
        addMouseMotionListener(this);

    }
    
    protected boolean checkForSave()
    {
        int response = JOptionPane.showConfirmDialog(null, "Do you want to save this first?", "Unsaved Work", JOptionPane.YES_NO_CANCEL_OPTION);
        if(response == JOptionPane.YES_OPTION)
        {
            save(true);
        }
        else if(response == JOptionPane.CANCEL_OPTION)
        {
            return false;
        }
        
        return true;
        
    }
    
    @SuppressWarnings("unchecked")
    public void open()
    {
        boolean continueOn = true;

        if(m_bNeedSave == true)
        {
            continueOn = checkForSave();
        }
        
        if(continueOn == true)
        {
            //allow user to choose a temp filename
            JFileChooser fileChooser = new JFileChooser(".");
            int returnValue = fileChooser.showOpenDialog(null);
            if(returnValue != JFileChooser.APPROVE_OPTION)
            {
                return;
            }
            else
            {
                m_file = fileChooser.getSelectedFile();
            }
            
            try
            {
                FileInputStream fIn = new FileInputStream(m_file);
                ObjectInputStream ois = new ObjectInputStream(fIn);
                myList = (LinkedList<MyLine>)(ois.readObject());
                m_bNeedSave = false;
                repaint();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, "There was a problem opening or writing to the save file.");
                m_file = null;
                return;
            }

        }
    }
    
    public void clear()
    {
        boolean continueOn = true;

        if(m_bNeedSave == true)
        {
            continueOn = checkForSave();
        }
        
        if(continueOn == true)
        {
            m_file = null;
        }
        //repaint();
        
        
        
    }
    
    public void save(boolean forcePrompt)
    {

        if(m_file == null || forcePrompt == true)
        {
            JFileChooser fileChooser = new JFileChooser(".");
            if(m_file != null)
                fileChooser.setSelectedFile(m_file);
            int returnValue = fileChooser.showSaveDialog(null);
            if (returnValue != JFileChooser.APPROVE_OPTION)
            {
                return;
            }
            else
            {
                m_file = fileChooser.getSelectedFile();
            }
        }
        
        try
        {
            FileOutputStream fOut = new FileOutputStream(m_file);
            ObjectOutputStream oos = new ObjectOutputStream(fOut);
            oos.writeObject(myList);
            m_bNeedSave = false;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "There was a problem opening or writing to the save file.");
            m_file = null;
            return;
        }

    }

    public void nextWidth()
    {

        if(selWidth == 1)
            setWidth(4);
        else if(selWidth == 4)
            setWidth(8);
        else
            setWidth(1);

    }

    public void nextColor()
    {

        if(selColor == Color.BLACK)
            setColor(Color.RED);
        else if(selColor == Color.RED)
            setColor(Color.GREEN);
        else if(selColor == Color.GREEN)
            setColor(Color.BLUE);
        else
            setColor(Color.BLACK);

    }

    public void setWidth(int w)
    {
        selWidth = w;
    }

    public void setColor(Color c)
    {
        selColor = c;
    }

    public void emptyLineCollection()
    {
        myList.clear();
    }

    public void redrawCanvas()
    {
        repaint();
    }

    public void setSize(Dimension myDimension)
    {
        setSize(myDimension.width, myDimension.height);
    }

    public void setSize(int width, int height)
    {
        if(width > getMaximumSize().width)
            width = getMaximumSize().width;
        if(height > getMaximumSize().height)
            height = getMaximumSize().height;

        super.setSize(width, height);
    }

    public void mousePressed(MouseEvent e)
    {
        m_ptFrom = m_ptTo = e.getPoint();
        statusBar.setText("Start Line: From=("+m_ptFrom.x+","+m_ptFrom.y+") To=("+m_ptFrom.x+","+m_ptFrom.y+")");
        String newTitle = "";
        if(m_file == null)
        {
            newTitle = "(noname)*";
        }
        else
        {
            newTitle = m_file.getName() +"*";
        }
        m_frame.setTitle(newTitle);
    }

    public void mouseDragged(MouseEvent e)
    {
        if(contains(e.getPoint()))
        {
            Graphics myGraphics = getGraphics();
            myGraphics.setXORMode(this.getBackground());
            //erase line from m_ptFrom and m_ptTo
            myGraphics.drawLine(m_ptFrom.x, m_ptFrom.y, m_ptTo.x, m_ptTo.y);
            //copy mouse position to m_ptTo
            m_ptTo = e.getPoint();
            //draw line from m_ptFrom and m_ptTo using default stroke/color
            myGraphics.drawLine(m_ptFrom.x, m_ptFrom.y, m_ptTo.x, m_ptTo.y);
        }
        Point tmpPt = e.getPoint();
        statusBar.setText("In Progress: From=("+m_ptFrom.x+","+m_ptFrom.y+") To=("+tmpPt.x+","+tmpPt.y+")");
        
        String newTitle = "";
        if(m_file == null)
        {
            newTitle = "(noname)*";
        }
        else
        {
            newTitle = m_file.getName() +"*";
        }
        m_frame.setTitle(newTitle);
    }

    public void mouseReleased(MouseEvent e)
    {
        m_bNeedSave = true;
        
        Graphics2D myGraphics = (Graphics2D)getGraphics();
        myGraphics.setXORMode(this.getBackground());
        //erase the last line drawn
        myGraphics.drawLine(m_ptFrom.x, m_ptFrom.y, m_ptTo.x, m_ptTo.y);

        Point tmpPt = e.getPoint();

        if(contains(e.getPoint()))
        {
            //update the m_ptTo point
            m_ptTo = tmpPt;
            //dont want to be in XOR mode
            myGraphics.setPaintMode();

            MyLine thisLine = new MyLine(m_ptFrom, m_ptTo, selColor, selWidth);
            myList.add(thisLine);
            thisLine.draw(myGraphics);
        }
        statusBar.setText("Finish Line: From=("+m_ptFrom.x+","+m_ptFrom.y+") To=("+m_ptTo.x+","+m_ptTo.y+")");
        String newTitle = "";
        if(m_file == null)
        {
            newTitle = "(noname)*";
        }
        else
        {
            newTitle = m_file.getName() +"*";
        }
        m_frame.setTitle(newTitle);
    }

    public void mouseExited(MouseEvent e)
    {
        
    }

    public void mouseEntered(MouseEvent e)
    {

    }

    public void mouseClicked(MouseEvent e)
    {
        
    }

    public void mouseMoved(MouseEvent e)
    {
        
        
    }

    protected void paintComponent(Graphics myGraphics)
    {
        Graphics2D g = (Graphics2D)myGraphics;
        
        
        setPreferredSize(new Dimension(400, 400));

        ListIterator<MyLine> myIterator = myList.listIterator();

        while (myIterator.hasNext())
        {
            MyLine thisLine = (MyLine)myIterator.next();
            thisLine.draw(g);
        }


    }

}