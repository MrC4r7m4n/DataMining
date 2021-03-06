package pl.edu.agh.ftj.datamining.dbapi.postgresql;

import org.junit.Test;
import static org.junit.Assert.*;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Test obslugi bazy postgresql
 * @author Szymon Stupkiewicz
 * @version 1.0.0
 */
public class PostgreSqlTest {

    /**
     * Standardowy konstruktor - nic nie robi.
     */
    public PostgreSqlTest() {
    }

    /**
     * Test metody getData z klasy Postgresql
     */
    @Test
    public void testGetData() throws Exception {
        System.out.println("getData");
//      TODO choose a file and corresponding table to test
        String uri = "";
        String table = "";
        String file = "";

        Postgresql instance = new Postgresql();
        Instances db_data = instance.getData(uri, table);
        Instances file_data = DataSource.read( file );

        assertEquals(file_data, db_data);
    }
}
