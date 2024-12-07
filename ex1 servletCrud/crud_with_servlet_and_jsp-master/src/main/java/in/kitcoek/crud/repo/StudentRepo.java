package in.kitcoek.crud.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.kitcoek.crud.model.Student;


public class StudentRepo {
	private String jdbcURL = "jdbc:postgresql://localhost:5432/student?useSSL=false";
    private String jdbcUsername = "postgres";
    private String jdbcPassword = "postgres";
    private Connection connection = null;

    private static final String INSERT_Student_SQL = "INSERT INTO studentinfo(prn, name, branch) VALUES (?, ?, ?);";
    private static final String SELECT_Student_BY_ID = "select prn,name,branch from studentinfo where prn =?";
    private static final String SELECT_ALL_Students = "select * from studentinfo;";
    private static final String DELETE_Student_SQL = "delete from studentinfo where prn = ?;";
    private static final String UPDATE_Student_SQL = "update studentinfo set name = ?,branch= ? where prn = ?;";
    
    public StudentRepo(){
    	try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void insertStudent(Student s) throws SQLException {
        try (
//        	Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_Student_SQL)) {
            preparedStatement.setInt(1, s.getPrn());
            preparedStatement.setString(2, s.getName());
            preparedStatement.setString(3, s.getBranch());
//            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Student selectStudent(int prn) {
    	Student s = null;
        try (
//        	Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_Student_BY_ID);) {
            preparedStatement.setInt(1, prn);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String branch = rs.getString("branch");
                s = new Student(prn, name, branch);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return s;
    }

    public List<Student> selectAllStudents() {
        List<Student> students = new ArrayList<>();
        try (
//        	Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_Students);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	int prn = Integer.parseInt(rs.getString("prn"));
            	String name = rs.getString("name");
                String branch = rs.getString("branch");
                students.add(new Student(prn, name, branch));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return students;
    }

    public boolean deleteStudent(int prn) throws SQLException {
        boolean rowDeleted;
        try (
//        	Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_Student_SQL);) {
            statement.setInt(1, prn);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateStudent(Student s) throws SQLException {
        boolean rowUpdated;
        try (
//        	Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_Student_SQL);) {
            statement.setInt(1, s.getPrn());
            statement.setString(2, s.getName());
            statement.setString(3, s.getBranch());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
