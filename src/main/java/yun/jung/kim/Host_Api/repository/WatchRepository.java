package yun.jung.kim.Host_Api.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.EnumExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import yun.jung.kim.Host_Api.domain.Host;
import yun.jung.kim.Host_Api.domain.QHost;
import yun.jung.kim.Host_Api.domain.QWatch;
import yun.jung.kim.Host_Api.domain.Watch;

@RepositoryRestResource
public interface WatchRepository extends JpaRepository<Watch, Long>,
        QuerydslPredicateExecutor<Watch>,//검색 기능
        QuerydslBinderCustomizer<QWatch>
{
    @Override
    default void customize(QuerydslBindings bindings, QWatch root){
        bindings.excludeUnlistedProperties(true);

        bindings.including(root.eventOccurrence, root.eventResult, root.eventType, root.createdAt, root.createdBy);
        bindings.bind(root.eventOccurrence).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.eventResult).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.eventType).first(EnumExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
    };
}