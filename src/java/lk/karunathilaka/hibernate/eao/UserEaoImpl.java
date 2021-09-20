/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.karunathilaka.hibernate.eao;

import java.util.List;
import lk.karunathilaka.hibernate.entity.Member;
import lk.karunathilaka.hibernate.entity.User;
import lk.karunathilaka.hibernate.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Hasitha
 */
public class UserEaoImpl implements UserEao{
    
    SessionFactory sessionFactory;
    
    public UserEaoImpl(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    
}
