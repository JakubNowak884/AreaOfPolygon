package view;

import controller.AreaOfPolygon;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 * View class shows given messages in console.
 *
 * @author Jakub Nowak gr 5
 * @version 2.0
 */
public class View extends JPanel {

    private JButton bAdd, bRemove, bCalculateArea;
    private JTable table;
    private AreaOfPolygon listener;

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

        listener = new AreaOfPolygon();
        table = new JTable(new MyTableModel(data, columnNames));
        table.getModel().addTableModelListener(listener);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);

        bAdd = new JButton("Add point");
        bAdd.setActionCommand("Add");
        bAdd.addActionListener(listener);
        this.add(bAdd);

        bRemove = new JButton("Remove point");
        bRemove.setActionCommand("Remove");
        bRemove.addActionListener(listener);
        this.add(bRemove);

        bCalculateArea = new JButton("Calculate area");
        bCalculateArea.setActionCommand("CalculateArea");
        bCalculateArea.addActionListener(listener);
        this.add(bCalculateArea);
    }
    
     public void addRowToTable() {
        MyTableModel model = (MyTableModel) table.getModel();
        model.removeTableModelListener(listener);
        model.addRow(new Object[]{"Point" + (model.getRowCount() + 1), 0.0f, 0.0f});
        model.addTableModelListener(listener);
    }

    public void removeRowFromTable() {
        MyTableModel model = (MyTableModel) table.getModel();
        model.removeTableModelListener(listener);
        model.removeRow(model.getRowCount() - 1);
        model.addTableModelListener(listener);
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
