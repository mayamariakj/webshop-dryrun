package no.kristiania.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class productDao {

    private List<String> products = new ArrayList<>();
    private DataSource dataSource;

    public productDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void insertProduct(String productName) {
        products.add(productName);

        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement
                    ("insert into products(name) values(?)"
            );
            statement.setString(1, productName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public List<String> listAll() {
        return products;
    }
}
