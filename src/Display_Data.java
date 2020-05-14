import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class Display_Data {
    public static void main(String[] args) throws Exception {
        // The Connection is obtained
        String connectionUrl = "jdbc:mysql://localhost:3306/cars?serverTimezone=UTC";

        Connection conn = DriverManager.getConnection(connectionUrl, "root", "***");

            //create and execute Queries
            Statement stmt= conn.createStatement();

        ResultSet rs = stmt.executeQuery("select * from cars");

        // It creates and displays the table
        JTable table = new JTable(buildTableModel(rs));

        // Closes the Connection

        JOptionPane.showMessageDialog(null, new JScrollPane(table));

    }
    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }
}
