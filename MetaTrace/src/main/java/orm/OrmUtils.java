package orm;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import model.Random;
import org.java_websocket.exceptions.InvalidDataException;

public class OrmUtils {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost/demodb";
    private static final String DATABASE_USERNAME = "admin";
    private static final String DATABASE_PASSWORD = "123456!";

    public static boolean isExists(String randomId) throws InvalidDataException {
        try(JdbcPooledConnectionSource connectionSource
                    = new JdbcPooledConnectionSource(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD))
        {
            Dao<Random, String> randoms = DaoManager.createDao(connectionSource, Random.class);
            return randoms.queryForId(randomId) != null;
        }
        catch (Exception e) {
            throw new InvalidDataException(500, e);
        }
    }

    public static void save(Random random) throws InvalidDataException {
        try(JdbcPooledConnectionSource connectionSource
                    = new JdbcPooledConnectionSource(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD))
        {
            Dao<Random, String> randoms = DaoManager.createDao(connectionSource, Random.class);
            randoms.create(random);
        }
        catch (Exception e) {
            throw new InvalidDataException(500, e);
        }
    }

}
