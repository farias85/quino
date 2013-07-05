package quino.test.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import static javax.swing.SortOrder.ASCENDING;
import static javax.swing.SortOrder.DESCENDING;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class TestTableSorter {

    public static void main(String[] args) {
        new TestTableSorter();
    }

    public TestTableSorter() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                } catch (InstantiationException ex) {
                } catch (IllegalAccessException ex) {
                } catch (UnsupportedLookAndFeelException ex) {
                }

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
                    final JTable table = new JTable();
                    DefaultTableModel model = new DefaultTableModel(
                                    new Object[][]{
                        {"A", 1, sdf.parse("12/05/2000")},                        
                        {"Y", 5, sdf.parse("12/05/1985")},
                        {"Z", 1, sdf.parse("12/06/1985")},},
                                    new Object[]{"Name", "Number", "Date"});

                    table.setModel(model);
                    table.setAutoCreateRowSorter(true);

                    JPanel panel = new JPanel(new GridLayout(1, 3));
                    panel.add(createButton(table, 0));
                    panel.add(createButton(table, 1));
                    panel.add(createButton(table, 2));

                    JFrame frame = new JFrame("Test");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setLayout(new BorderLayout());
                    frame.add(panel, BorderLayout.NORTH);
                    frame.add(new JScrollPane(table));
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }

            protected JButton createButton(JTable table, int col) {
                JButton btn = new JButton("");
                btn.addActionListener(new SortAction(table, col));
                return btn;
            }

        });
    }

    public class SortAction implements ActionListener {

        private JTable table;
        private int column;

        public SortAction(JTable table, int column) {
            this.table = table;
            this.column = column;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            RowSorter<? extends TableModel> rowSorter = table.getRowSorter();
            List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>(rowSorter.getSortKeys());
            boolean found = false;
            SortOrder order = SortOrder.ASCENDING;
            for (int index = 0; index < sortKeys.size(); index++) {
                RowSorter.SortKey key = sortKeys.get(index);
                if (key.getColumn() == column) {
                    found = true;
                    System.out.println("Found existing sort key for column " + column);
                    switch (key.getSortOrder()) {
                        case ASCENDING:
                            order = SortOrder.DESCENDING;
                            sortKeys.set(index, new RowSorter.SortKey(column, order));
                            break;
                        case DESCENDING:
                            order = SortOrder.UNSORTED;
                            sortKeys.remove(index);
                            break;
                    }
                    break;
                }
            }
            if (!found) {
                System.out.println("Add new sort key for column " + column);
                sortKeys.add(new RowSorter.SortKey(column, order));
            }
            System.out.println("List contains " + sortKeys.size());
            RowSorter newSorter = new TableRowSorter(table.getModel());
            newSorter.setSortKeys(sortKeys);
            table.setRowSorter(newSorter);
            switch (order) {
                case ASCENDING:
                    ((JButton) e.getSource()).setText("+");
                    break;
                case DESCENDING:
                    ((JButton) e.getSource()).setText("-");
                    break;
                default:
                    ((JButton) e.getSource()).setText(" ");
                    break;
            }
        }
    }
}