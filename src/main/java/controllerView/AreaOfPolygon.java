package controllerView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import javax.swing.Box;
import javax.swing.BoxLayout;
import model.Point;
import model.Polygon;
import model.ConcavePolygonException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import model.CircularLinkedList;

/**
 * AreaOfPolygon class responds to the user actions and shows GUI.
 *
 * @author Jakub Nowak gr 5
 * @version 3.0
 */
public class AreaOfPolygon extends JPanel implements ActionListener, TableModelListener, ChangeListener {

    /**
     * Button adding point at the center of the coordinate system to the polygon.
     */
    private JButton bAdd;
    /**
     * Button removing last point of the polygon.
     */
    private JButton bRemove;
    /**
     * Button showing area of the polygon in pop-up window.
     */
    private JButton bCalculateArea;
     /**
     * Button increasing scale of the coordinate system.
     */
    private JButton bZoomIn;
      /**
     * Button decreasing scale of the coordinate system.
     */
    private JButton bZoomOut;
    /**
     * Table representing points of the polygon.
     */
    private JTable table;
    /**
     * Polygon which will be drew on the coordinate system and which area will be calculated.
     */
    private static Polygon polygon;
    /**
     * Scale by which all points of coordinate system are multiplied when user zooms in or zooms out.
     */
    private float scale = 1.0f;
    /**
     * Top left corner of the painted coordinate system.
     */
    private final Point coordinateSystemTopLeftCorner;
    /**
     * Width of the painted coordinate system.
     */
    private final float coordinateSystemWidth;
    /**
     * Height of the painted coordinate system.
     */
    private final float coordinateSystemHeight;

    /**
     * Default constructor.
     */
    public AreaOfPolygon() {
        super(new BorderLayout());
        
        coordinateSystemTopLeftCorner = new Point(300, 10);
        coordinateSystemWidth = 400;
        coordinateSystemHeight = 400;
        
        String[] columnNames = {"Point",
            "X",
            "Y"};

        Object[][] data = new Object[polygon.getNumberOfPoints()][3];
        
        for (int i = 0; i < polygon.getNumberOfPoints(); i++)
        {
            data[i][0] = "Point " + (i + 1);
            data[i][1] = polygon.getPoint(i).getX();
            data[i][2] = polygon.getPoint(i).getY();
        }  
        
        table = new JTable(new MyTableModel(data, columnNames));
        table.getModel().addTableModelListener(this);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().addChangeListener(this);
        add(scrollPane);
        
        bAdd = new JButton("Add point");
        bAdd.setActionCommand("Add");
        bAdd.addActionListener(this);

        bRemove = new JButton("Remove point");
        bRemove.setActionCommand("Remove");
        bRemove.addActionListener(this);

        bCalculateArea = new JButton("Calculate area");
        bCalculateArea.setActionCommand("CalculateArea");
        bCalculateArea.addActionListener(this);
        
        bZoomIn = new JButton("Zoom In");
        bZoomIn.setMnemonic(KeyEvent.VK_I);
        
        bZoomIn.setActionCommand("ZoomIn");
        bZoomIn.addActionListener(this);
        
        bZoomOut = new JButton("Zoom Out");
        bZoomOut.setMnemonic(KeyEvent.VK_O);
        bZoomOut.setActionCommand("ZoomOut");
        bZoomOut.addActionListener(this);
        
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                                           BoxLayout.LINE_AXIS));
        
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(bAdd);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(bRemove);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(bCalculateArea);
        buttonPane.add(Box.createHorizontalStrut(270));
        buttonPane.add(bZoomIn);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(bZoomOut);
        buttonPane.add(Box.createHorizontalStrut(5));
        add(buttonPane, BorderLayout.PAGE_END);
    }
    
    /**
     * Method paints coordinate system and polygon on the window.
     * 
     * @param g object encapsulates state information needed for the basic rendering operations that Java supports
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        float x =  coordinateSystemTopLeftCorner.getX();
        float y =  coordinateSystemTopLeftCorner.getY();
        
        float width = coordinateSystemWidth;
        float height =  coordinateSystemHeight;
        
        Graphics2D g2 = (Graphics2D) g;
        
        //drawing X axis arrow
        g2.draw(new Line2D.Float(x + width / 2, y, x + width / 2, y + height));
        g2.draw(new Line2D.Float((x + width / 2) - 5, y + 10, x + width / 2, y));        
        g2.draw(new Line2D.Float(x + width / 2, y, (x + width / 2) + 5, y + 10));
        
        //drawing Y axis arrow
        g2.draw(new Line2D.Float(x, y + height / 2, x + width, y + height / 2));
        g2.draw(new Line2D.Float(x + width - 10, (y + height / 2) - 5, x + width , y + height / 2));
        g2.draw(new Line2D.Float(x + width, y + height / 2, x + width - 10, (y + height / 2) + 5));
        
        //drawing frame around coordinate system
        g2.draw(new Line2D.Float(x, y, x + width, y));
        g2.draw(new Line2D.Float(x + width, y, x + width, y + height));
        g2.draw(new Line2D.Float(x + width, y + height, x, y + height));
        g2.draw(new Line2D.Float(x, y + height, x, y));
                
        //drawing scale
        for (int i = 1; i < 9; i++)
        {
            float value = i * (width / 9);
            g2.draw(new Line2D.Float((x + width / 2) - 5, value + y, (x + width / 2) + 5, value + y));
            g2.drawString(Double.toString(Math.round(((height/2 - value) * 1/scale) * 100.0) / 100.0), (x + width / 2) + 5, value + y);
            
            g2.draw(new Line2D.Float(value + x, (y + height / 2) - 5, value + x, (y + height / 2) + 5));
            g2.drawString(Double.toString(Math.round((-(width/2 - value) * 1/scale) * 100.0) / 100.0), value + x - 15, (y + height / 2) - 5);
        }
        
        //drawing polygon
        for (int i = 0; i < polygon.getNumberOfPoints(); i++)
        {
            Point point = polygon.getPoint(i);
            CircularLinkedList<Point> points = polygon.getPoints();
            
            float x1 = (point.getX() * scale) + width/2 + x;
            float x2 = (points.getNext(point).getX() * scale) + width/2 + x;
            
            Line2D lin = new Line2D.Float(x1, -(point.getY() * scale) + height/2 + y, x2, -(points.getNext(point).getY() * scale) + height/2 + y);
            g2.setColor(Color.red);
            g2.draw(lin);
        }
      }
    
    /**
     * Method responts to pressing buttons by user.
     * 
     * @param e incoming event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        MyTableModel model;
        switch (e.getActionCommand())
        {
             case "ZoomOut":
                 if (scale > 0.1)
                 {
                    scale -= 0.1f;
                    repaint((int)coordinateSystemTopLeftCorner.getX(), (int)coordinateSystemTopLeftCorner.getY(), (int)coordinateSystemWidth, (int)coordinateSystemHeight);
                 }
                break;
            case "ZoomIn":
                scale += 0.1f;
                repaint((int)coordinateSystemTopLeftCorner.getX(), (int)coordinateSystemTopLeftCorner.getY(), (int)coordinateSystemWidth, (int)coordinateSystemHeight);
                break;
            case "Add":
                model = (MyTableModel) table.getModel();
                model.removeTableModelListener(this);
                model.addRow(new Object[]{"Point" + (model.getRowCount() + 1), 0.0f, 0.0f});
                model.addTableModelListener(this);
                polygon.AddPoint(new Point(0.0f, 0.0f));
                repaint((int)coordinateSystemTopLeftCorner.getX(), (int)coordinateSystemTopLeftCorner.getY(), (int)coordinateSystemWidth, (int)coordinateSystemHeight);
                break;
            case "Remove":
                if (polygon.getPoints().size() > 3)
                {
                    model = (MyTableModel) table.getModel();
                    model.removeTableModelListener(this);
                    model.removeRow(model.getRowCount() - 1);
                    model.addTableModelListener(this);
                    polygon.RemovePoint();
                    repaint((int)coordinateSystemTopLeftCorner.getX(), (int)coordinateSystemTopLeftCorner.getY(), (int)coordinateSystemWidth, (int)coordinateSystemHeight);
                }
                break;
            case "CalculateArea":
                try {
                   float area = polygon.area();
                   JOptionPane.showMessageDialog(null, "Area of given polygon is equal: " + area);
                }
                catch(ConcavePolygonException exception)
                {
                    JOptionPane.showMessageDialog(null, "Polygon is concave! Area cannot be calculated.");
                }
                break;
            default:
                break;
        }
    }
    
    /**
     * Method responts to changing table values by user.
     * 
     * @param e incoming event
     */
    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        MyTableModel model = (MyTableModel)e.getSource();
        Object data = model.getValueAt(row, column);

        if (column == 1)
        {
            polygon.setPointX(row, (float)data);
        }
        else
        {
            polygon.setPointY(row, (float)data);
        }
        repaint((int)coordinateSystemTopLeftCorner.getX(), (int)coordinateSystemTopLeftCorner.getY(), (int)coordinateSystemWidth, (int)coordinateSystemHeight);
    }
    
    /**
     * Method responts to changing table position by user.
     * 
     * @param e incoming event
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        repaint((int)coordinateSystemTopLeftCorner.getX(), (int)coordinateSystemTopLeftCorner.getY(), (int)coordinateSystemWidth, (int)coordinateSystemHeight);
    }
    
    /**
     * Method creates and shows GUI.
     * 
     * For safety reasons it should be called from the thread that sends out the event.
     */
    public static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Area Of Polygon");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AreaOfPolygon newContentPane = new AreaOfPolygon();

        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setBounds(0, 0, 800, 500);
    }

    /**
     * Program calculates area of polygon by the points given by user and draws it on the coordinate system. When the points are given incorrectly, program asks for new points of the polygon.
     * @param args The user should enter the coordinations of the points one by one starting by first point position on the x axis. The points should be given using the right hand rule.
     */
    public static void main(String[] args) {
        
        while (args.length % 2 != 0 || args.length < 6)
        {
            String arguments = (String)JOptionPane.showInputDialog(null, "Enter new arguments:", "Incorrect arguments", JOptionPane.ERROR_MESSAGE);
            args = arguments.split(" ");
        }
        
        Point[] points = new Point[args.length / 2];
            for (int i = 0; i < args.length; i += 2)
            {
                try
                {
                    float x = Float.parseFloat(args[i]);
                    float y = Float.parseFloat(args[i + 1]);
                    points[i / 2] = new Point(x, y);
                }
                catch (NumberFormatException e)
                {
                    JOptionPane.showMessageDialog(null, "Some arguments are not numbers!", "Incorrect arguments", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            polygon = new Polygon(points);
            
         javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
