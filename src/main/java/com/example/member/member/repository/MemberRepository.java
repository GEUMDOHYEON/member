package com.example.member.member.repository;

import com.example.member.member.entity.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public Member findByName(String name) {
        return (Member) em.createQuery("select m from Member m where m.name = :name")
                .setParameter("name", name)
                .getSingleResult();
    }
}
