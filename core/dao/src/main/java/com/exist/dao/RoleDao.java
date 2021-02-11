package com.exist.dao;
import java.util.Optional;
import com.exist.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer>{

	@Query("Select r From Role r Where r.role = ?1")
	Optional<Role> findRoleByRole(String role);
	
	@Query("Select r From Role r Where r.role = ?1 and r.roleId != ?2")
	Optional<Role> findRoleByRoleEdit(String role,int roleId);
}
