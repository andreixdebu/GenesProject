package repository;

import domain.Genes;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.Vector;

public class Repository {

    private static final String JDBC_URL = "jdbc:sqlite:/GenesProject/data/test_db.db";

    private static Connection conn = null;

    public void openConnection() {
        try {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection() {
        try {
            conn.close();
            conn = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createSchema() {
        try {
            final Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS databasegenes(name varchar(100), organism varchar(100), function varchar(100), associatedSequence varchar(100))");
            stmt.close();
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
        }
    }

    public void addInSchema(){

        try
        {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO databasegenes VALUES (?, ?, ?, ?),(?, ?, ?, ?),(?, ?, ?, ?),(?, ?, ?, ?),(?, ?, ?, ?)");
            statement.setString(1, "yqgE");
            statement.setString(2, "E_Coli_K12");
            statement.setString(3, "facilitates stress-induced mutagenesis");
            statement.setString(4, "ATGAATTTACAGCAT");
            statement.setString(5, "ppiA");
            statement.setString(6, "M_tuberculosis");
            statement.setString(7, "is thought to facilitate proper protein folding");
            statement.setString(8, "TTTTCATCACCGTCGG");
            statement.setString(9, "Col2a1");
            statement.setString(10, "Human");
            statement.setString(11, "helps in making one component of collagen");
            statement.setString(12, "TTAAAGCATGGCTCTGTG");
            statement.setString(13, "TP53");
            statement.setString(14, "Human");
            statement.setString(15, "codes for a tumor suppressor protein");
            statement.setString(16, "CTCAAAGTCTAGAGCCACCGTC");
            statement.setString(17, "hokC");
            statement.setString(18, "E_Coli_K12");
            statement.setString(19, "synthetizes a host killing protein");
            statement.setString(20, "TTAATGAAGCATAAGCTTGATTTC");


            statement.executeUpdate();
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vector<Genes> getAll(){

        Vector<Genes> genes;
        try {
            genes = new Vector<>();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM databasegenes");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Genes g  = new Genes(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
                genes.add(g);
            }
            rs.close();
            statement.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return genes;
    }

    public void updateSchema(String name, String newFunction, String newSequence){

        try {
            PreparedStatement statement = conn.prepareStatement("UPDATE databasegenes SET function = ? WHERE name = ?");
            statement.setString(1, newFunction);
            statement.setString(2, name);
            statement.executeUpdate();
            statement.close();

            PreparedStatement statement1 = conn.prepareStatement("UPDATE databasegenes SET associatedSequence = ? WHERE name = ?");
            statement1.setString(1, newSequence);
            statement1.setString(2, name);
            statement1.executeUpdate();
            statement1.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
