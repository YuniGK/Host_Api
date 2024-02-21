package yun.jung.kim.Host_Api.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import yun.jung.kim.Host_Api.domain.QWatch;
import yun.jung.kim.Host_Api.domain.UserAccount;
import yun.jung.kim.Host_Api.domain.Watch;

@RepositoryRestResource
public interface UserAccountRepository extends JpaRepository<UserAccount, String>{
}