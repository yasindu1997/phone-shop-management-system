package lk.acpt.demofx.model;

import lk.acpt.demofx.db.DBConnection;
import lk.acpt.demofx.dto.OrderDetailDto;
import lk.acpt.demofx.dto.PhoneDto;
import lk.acpt.demofx.entity.Phone;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.*;
import java.util.ArrayList;

public class PhoneModel {
    public static int savePhone(PhoneDto phoneDto){
        //session factory
        SessionFactory sessionFactory = setUp();

        //new session
        Session session = sessionFactory.openSession();

        //start the transaction
        session.beginTransaction();

        session.persist(new Phone(null,phoneDto.getBrand(),phoneDto.getModel(),phoneDto.getPrice(),phoneDto.getQty()));

        //commit the cache
        session.getTransaction().commit();

        //close the session
        session.close();

        return 1;
    }

    public static void deletePhone(int pid) {
        try {
            DBConnection.getDBConnection().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updatePhone(PhoneDto dto) {

    }

    public static PhoneDto searchPhone(String pid) {

        try {
            //Driver class loaded to the ram
            Class.forName("com.mysql.cj.jdbc.Driver");

            //create a connection with the given database server and database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/afsd_4", "root", "acpt");

            //create a dynamic query
            PreparedStatement stm = connection.prepareStatement("select * from phone where pid=?");
            stm.setObject(1, pid);

            //execute the query
            ResultSet resultSet = stm.executeQuery();

            PhoneDto phoneDto = new PhoneDto();

            while (resultSet.next()) {
                phoneDto.setBrand(resultSet.getString(2));
                phoneDto.setModel(resultSet.getString(3));
                phoneDto.setPrice(resultSet.getDouble(4));
                phoneDto.setQty(resultSet.getInt(5));
            }

            return phoneDto;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<PhoneDto> getAllPhone() {
        try {
            //Driver class loaded to the ram
            Class.forName("com.mysql.cj.jdbc.Driver");

            //create a connection with the given database server and database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/afsd_4", "root", "acpt");

            //create a dynamic query
            PreparedStatement stm = connection.prepareStatement("select * from phone");

            //execute the query
            ResultSet resultSet = stm.executeQuery();

            ArrayList<PhoneDto> phoneDtos = new ArrayList<>();

            while (resultSet.next()) {
                phoneDtos.add(new PhoneDto(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getDouble(4), resultSet.getInt(5)));
            }

            return phoneDtos;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean makeOrder(String orderId, double subTotal, String orderDate, ArrayList<OrderDetailDto> orderDetailDtos) throws SQLException, ClassNotFoundException {
        //connection
        Connection connection = DBConnection.getDBConnection().getConnection();

        //disable auto commit feature
        connection.setAutoCommit(false);

        PreparedStatement stm1 = connection.prepareStatement("insert into orders(oid,order_date,amount) values(?,?,?)");
        stm1.setObject(1, orderId);
        stm1.setObject(2, orderDate);
        stm1.setObject(3, subTotal);
        //execute the query
        int isOrderAdded = stm1.executeUpdate();

        if (isOrderAdded > 0) {
            for (OrderDetailDto dto : orderDetailDtos) {
                PreparedStatement stm2 = connection.prepareStatement("insert into order_details(order_id,phone_id,qty,amount) values(?,?,?,?)");
                stm2.setObject(1, orderId);
                stm2.setObject(2, dto.getId());
                stm2.setObject(3, dto.getQty());
                stm2.setObject(4, dto.getTotal());

                int isAddedOrderDetail = stm2.executeUpdate();

                if (isAddedOrderDetail > 0) {
                    continue;
                } else {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } else {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
    }

    public static SessionFactory setUp() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .build();
        try {
            SessionFactory sessionFactory =
                    new MetadataSources(registry)
                            .addAnnotatedClass(Phone.class)
                            .buildMetadata()
                            .buildSessionFactory();
            return sessionFactory;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}

