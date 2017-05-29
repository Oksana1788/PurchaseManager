package com.purchase.manager;

import com.purchase.manager.entity.CostsEntity;
import com.purchase.manager.service.CostsService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Application {


    public static void main(String args[]) {
        CostsService costsService = new CostsService();
        System.out.println("MySQL JDBC Connection Testing");

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection("jdbc:h2:mem:AZ", "sa", "");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }


        BufferedReader d = new BufferedReader(new InputStreamReader(System.in));


        try {
            while (true) {
                System.out.println("1 - enter to add costs");
                System.out.println("2 - enter to delete cost");
                System.out.println("3 - enter to show");
                System.out.println("enter something else to stop");

                String s = d.readLine();
                Integer ch = Integer.parseInt(s);

                switch (ch) {
                    case 1:
                        System.out.println("Enter date:");
                        String date1 = d.readLine();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = new Date(simpleDateFormat.parse(date1).getTime());

                        System.out.println("Enter sum: ");
                        String s1 = d.readLine();
                        BigDecimal sum = BigDecimal.valueOf(Long.parseLong(s1));

                        System.out.println("Enter currency: ");
                        String currency = d.readLine();

                        System.out.println("Enter product: ");
                        String product = d.readLine();
                        CostsEntity costsEntity = new CostsEntity();
                        costsEntity.setDate(date);
                        costsEntity.setSum(sum);
                        costsEntity.setCurrency(currency);
                        costsEntity.setProduct(product);
                        costsService.save(costsEntity);
                        List<CostsEntity> list = costsService.findAllCosts();
                        for (CostsEntity c : list) {
                            System.out.println(c.getDate() + " " + c.getSum() + " " + c.getCurrency() + " " + c.getProduct());
                        }
                        break;
                    case 2:
                        System.out.println("Enter Date:");
                        String date2 = d.readLine();
                        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                        Date dateDel = new Date(simpleDateFormat1.parse(date2).getTime());

                        costsService.deleteCosts(dateDel);

                        List<CostsEntity> list1 = costsService.findAllCosts();
                        for (CostsEntity c : list1) {
                            System.out.println(c.getDate() + " " + c.getSum() + " " + c.getCurrency() + " " + c.getProduct());
                        }
                        break;

                    case 3:

                        List<CostsEntity> listLis = costsService.findAllCosts();
                        for (CostsEntity c : listLis) {
                            System.out.println(c.getDate() + " " + c.getSum() + " " + c.getCurrency() + " " + c.getProduct());
                        }

                        break;
                    default:
                        System.out.println("Restart program and enter 1, 2 or 3");
                        return ;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
