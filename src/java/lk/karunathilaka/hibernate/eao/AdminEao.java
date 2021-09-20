/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.karunathilaka.hibernate.eao;

import java.util.List;
import lk.karunathilaka.hibernate.entity.Admin;

/**
 *
 * @author Hasitha
 */
public interface AdminEao {
    
    void create(Admin admin);
    
    void saveOrUpdate(Admin admin);
    
    List<Admin> getAdminByEmailAndPassword(String email, String password);
    List<Admin> getAdminByEmail(String email);
}
