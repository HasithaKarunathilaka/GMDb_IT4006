/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.karunathilaka.hibernate.eao;

import java.util.List;
import lk.karunathilaka.hibernate.entity.Director;

/**
 *
 * @author Hasitha
 */
public interface DirectorEao {
    
    void creat(Director director);
    List<Director> getAllDirectors();
    
}
