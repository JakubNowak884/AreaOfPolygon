package controllerView;

import javax.swing.table.DefaultTableModel;

/**
 * Class extends DefaultTableModel to get access only to the specific colums.
 *
 * @author Jakub Nowak gr 5
 * @version 3.0
 */
class MyTableModel extends DefaultTableModel {

    /**
     * Class constructor.
     *
     * @param data data in the table
     * @param columnNames names of the columns in the table
     */
    public MyTableModel(Object[][] data, String[] columnNames) {
        super(data, columnNames);
    }

    /**
     * Method returns class of the modified cell
     * 
     * @param c number of the column in the table
     * @return 
     */
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /**
     * Method allows to modify only cells that do not belong to the first column.
     * 
     * @param row number of the modified row
     * @param col number of the modified column
     * @return false is cell belongs to first column otherwise false
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        if (col < 1) {
            return false;
        } else {
            return true;
        }
    }
}
