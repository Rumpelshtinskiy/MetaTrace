package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@DatabaseTable(tableName = "random")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Random implements Serializable {

    @DatabaseField(id = true, columnName = "value")
    private String value;
}
