package yun.jung.kim.Host_Api.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import yun.jung.kim.Host_Api.domain.Host;
import yun.jung.kim.Host_Api.domain.QHost;

@RepositoryRestResource
public interface HostRepository extends
        JpaRepository<Host, Long>,
        QuerydslPredicateExecutor<Host>,//검색 기능
        QuerydslBinderCustomizer<QHost>
{
    @Override
    default void customize(QuerydslBindings bindings, QHost root){
        bindings.excludeUnlistedProperties(true);

        bindings.including(root.name, root.ip, root.createdAt, root.createdBy);
        bindings.bind(root.ip).first(StringExpression::containsIgnoreCase);//Like %${v}%
        bindings.bind(root.name).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
    };

    Page<Host> findByNameContaining(String name, Pageable pageable);
    Page<Host> findByIpContaining(String ip, Pageable pageable);
    Page<Host> findByUserAccount_UserIdContaining(String userId, Pageable pageable);

}