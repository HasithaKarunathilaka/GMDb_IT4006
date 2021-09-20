/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.karunathilaka.hibernate.eao;

import lk.karunathilaka.hibernate.entity.Review;

/**
 *
 * @author Hasitha
 */
public interface ReviewEao {
    void create(Review review);
    
    void saveOrUpdate(Review review);
}
