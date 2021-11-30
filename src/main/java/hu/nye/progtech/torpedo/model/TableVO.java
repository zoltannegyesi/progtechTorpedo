package hu.nye.progtech.torpedo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Value Object for tables.
 */
@Repository
@Setter
@Getter
@Scope("prototype")
public class TableVO {

    private final int tableSize = 10;
    private ArrayList<List<Character>> table = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TableVO tableVO = (TableVO) o;
        return tableSize == tableVO.tableSize && Objects.equals(table, tableVO.table);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableSize, table);
    }

    @Override
    public String toString() {
        return "TableVO{" +
                "tableSize=" + tableSize +
                ", table=" + table +
                '}';
    }
}
