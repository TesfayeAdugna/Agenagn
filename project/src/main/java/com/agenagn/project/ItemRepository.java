package com.agenagn.project;

import java.util.List;

import com.agenagn.project.security.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Items, Long> {
     // for search
     @Query(value = "select * from Items i where i.name like %:keyword% or i.type like %:keyword% or i.detail_description like %:keyword% or i.price like %:keyword%", nativeQuery = true)
     List<Items> findByKeyword(@Param("keyword") String keyword);
     
     List<Items> findByType(String type);
     List<Items> findByUser(User user);
}
