package view;

import controller.AreaOfPolygon;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import model.ConcavePolygonException;
import model.Point;
import model.Polygon;

/**
 * View class shows given messages in console.
 *
 * @author Jakub Nowak gr 5
 * @version 2.0
 */
public class View extends JPanel implements ActionListener, TableModelListener {

    private JButton bAdd, bRemove, bCalculateArea;
    private JTable table;
    private Polygon polygonModel;

    public View() {
        super(new GridLayout(1, 0));

        String[] columnNames = {"Point",
            "X",
            "Y"};

        Object[][] data = {
            {"Point1", 0.0f, 0.0f},
            {"Point2", 1.0f, 1.0f},
            {"Point3", 2.0f, 0.0f}
        };

        table = new JTable(new MyTableModel(data, columnNames));
        table.getModel().addTableModelListener(this);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);

        bAdd = new JButton("Add point");
        bAdd.setActionCommand("Add");
        bAdd.addActionListener(this);
        this.add(bAdd);

        bRemove = new JButton("Remove point");
        bRemove.setActionCommand("Remove");
        bRemove.addActionListener(this);
        this.add(bRemove);

        bCalculateArea = new JButton("Calculate area");
        bCalculateArea.setActionCommand("CalculateArea");
        bCalculateArea.addActionListener(this);
        this.add(bCalculateArea);
    }
    
    public void addPolygonModel(Polygon polygonModel)
    {
        this.polygonModel = polygonModel;
    }
    
    public void actionPerformed(ActionEvent e) {
        MyTableModel model;
        switch (e.getActionCommand())
        {
            case "Add":
                model = (MyTableModel) table.getModel();
                model.removeTableModelListener(this);
                model.addRow(new Object[]{"Point" + (model.getRowCount() + 1), 0.0f, 0.0f});
                model.addTableModelListener(this);
                polygonModel.AddPoint(new Point(0.0f, 0.0f));
                break;
            case "Remove":
                model = (MyTableModel) table.getModel();
                model.removeTableModelListener(this);
                model.removeRow(model.getRowCount() - 1);
                model.addTableModelListener(this);
                polygonModel.RemovePoint();
                break;
            case "CalculateArea":
                try {
                   float area = polygonModel.area();
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
    
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        MyTableModel model = (MyTableModel)e.getSource();
        Object data = model.getValueAt(row, column);

        if (column == 1)
        {
            polygonModel.setPointX(row, (float)data);
        }
        else
        {
            polygonModel.setPointY(row, (float)data);
        }
    }

    public void createAndShowGUI() {
        //ustawienie ladnego wygladu okien
        JFrame.setDefaultLookAndFeelDecorated(true);

        //utworzenie i przygotowanie okna
        JFrame frame = new JFrame("Area Of Polygon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //utworzenie i przygotowanie panelu
        View newContentPane = new View();
        //panel widoczny
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        //wyswietlenie okna
        frame.pack();
        frame.setVisible(true);
    }

}
