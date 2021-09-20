/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.karunathilaka.hibernate.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import lk.karunathilaka.hibernate.eao.AdminEao;
import lk.karunathilaka.hibernate.eao.AdminEaoImpl;
import lk.karunathilaka.hibernate.eao.MemberEao;
import lk.karunathilaka.hibernate.eao.MemberEaoImpl;
import lk.karunathilaka.hibernate.entity.Admin;
import lk.karunathilaka.hibernate.entity.Member;

/**
 *
 * @author Hasitha
 */
public class UserService {

    public static void initialiseSystem() {

        KeyboardInput keyboardInput = new KeyboardInput();
        AdminEao adminEao = new AdminEaoImpl();

        Admin admin = new Admin();
        admin.setName("admin");
        admin.setEmail("admin@admin.com");

        String dob = "18-12-1995";
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");

        try {
            admin.setDob(sd.parse(dob));
        } catch (ParseException ex) {
            System.out.println(ex.toString());
        }

        admin.setPassword("admin");
        admin.setState("active");

        List<Admin> admins = adminEao.getAdminByEmail(admin.getEmail());
        if (admins.isEmpty()) {
            adminEao.create(admin);

        }

        System.out.println("---------- WELCOME TO GMDb ----------");
        System.out.println("Instructions ----> \nTo Obtain Servic, Please Type the Relevent Service ID then Press Enter");
        System.out.println("1 - Login \n2 - Register");
        String inputValue = keyboardInput.inputString();

        if ("1".equals(inputValue)) {
            userLogin();
        } else if ("2".equals(inputValue)) {
            MemberService.setMember();
        }

    }

    public static void userLogin() {
        
        KeyboardInput keyboardInput = new KeyboardInput();
//        String userName = "admin@123.com";
//        String password = "123";
        
        System.out.println("Enter Your USERNAME: ");
        String userName = keyboardInput.inputString();
        
        System.out.println("Enter Your Password: ");
        String password = keyboardInput.inputString();

        MemberEao memberEao = new MemberEaoImpl();

//        Member member = new Member();
//        member.setUserId(1);
//        member.setName("Hasitha Karunathilaka");
//        memberEao.create(member);
        List<Member> members = memberEao.getMemberByEmailAndPassword(userName, password);

        if (members.isEmpty()) {
//            System.out.println("empty");
            AdminEao adminEao = new AdminEaoImpl();
            List<Admin> admins = adminEao.getAdminByEmailAndPassword(userName, password);

            if (admins.isEmpty()) {
                System.out.println("Invalid UserName or Password");
                initialiseSystem();
            } else {
                for (Admin admin : admins) {
                    System.out.println("Login Success");
                    System.out.println("WELCOME " + admin.getName());
                    AdminService.adminHome(admin);
                }
            }

        } else {
            for (Member member : members) {
                System.out.println("Login Success");
                System.out.println("WELCOME " + member.getName());
                MemberService.memberHome(member);
            }
        }

    }

}
