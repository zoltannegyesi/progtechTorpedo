package hu.nye.progtech.torpedo.ui;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

/**
 * UI class to print the table to the standard output.
 */

@Service
public class TablePrinter {

    public TablePrinter() {
    }

    /**
     * Prints the given table to the standard input.
     */

    public String drawTable(ArrayList<ArrayList<Character>> table) {
        StringBuilder tableText = new StringBuilder("   ");
        for (char c = 'A'; c < 'A' + table.size(); c++) {
            tableText.append(" ").append(c);
        }
        tableText.append("\n   ");
        tableText.append("_".repeat(Math.max(0, table.size() * 2 + 1)));
        tableText.append("\n");
        AtomicInteger i = new AtomicInteger();
        table.forEach(
                row -> {
                    String temp = i.incrementAndGet() > 9 ? " " : "  ";
                    tableText.append(i.get()).append(temp).append('|');
                    row.forEach(column -> tableText.append(column).append(" "));
                    tableText.deleteCharAt(tableText.length() - 1);
                    tableText.append("|\n");
                }
        );
        tableText.append("   ").append("-".repeat(Math.max(0, table.size() * 2 + 1)));
        return tableText.toString();
    }
}
