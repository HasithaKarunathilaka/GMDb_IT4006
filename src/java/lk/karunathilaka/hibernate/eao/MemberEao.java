/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.karunathilaka.hibernate.eao;

import java.util.List;
import lk.karunathilaka.hibernate.entity.Member;

/**
 *
 * @author Hasitha
 */
public interface MemberEao {
    
    void create(Member member);
    
    void saveOrUpdate(Member member);
    
    Member getMember(int memberId);
    
    List<Member> getMemberByEmailAndPassword(String email, String password);
    List<Member> getmemberByEmail(String email);
    
}
