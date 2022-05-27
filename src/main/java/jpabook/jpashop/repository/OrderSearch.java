package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@RequiredArgsConstructor
public class OrderSearch {

    private final EntityManager em;

    private String memberName; // 회원 이름
    private OrderStatus orderStatus; // 주문 상태


    public List<Order> findAll(OrderSearch orderSearch) {

       return em.createQuery("select o from Order o join o.member m" +
                "where o.status = :status" +
                "and m.name like : name", Order.class)
               .setParameter("status", orderSearch.getOrderStatus())
               .setParameter("name", orderSearch.getMemberName())
               .setMaxResults(1000)
               .getResultList();


    }

    //JPA Criteria

    public List<Order> findByCriteria(OrderSearch ordersSearch){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> o = cq.from(Order.class);
        Join<Object,Object> m = o.join("member", JoinType.INNER);
        List<Predicate> criteria = new ArrayList<>();

        if(ordersSearch.getOrderStatus() != null){
            Predicate status = cb.equal(o.get("orderstatus"),ordersSearch.getOrderStatus());
            criteria.add(status);
        }

        if(ordersSearch.getMemberName() != null){
            Predicate status = cb.like(m.get("username"),"%"+ordersSearch.getMemberName()+"%");
            criteria.add(status);
        }

        cq.where(cb.and(
                criteria.toArray(new Predicate[criteria.size()])
        ));
        TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000);
        return query.getResultList();
    }

    public List<Order> findAll(){

        return em.createQuery("select o from Orders o join fetch o.member ",
                Order.class).getResultList();
    }

}
