package yun.jung.kim.Host_Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yun.jung.kim.Host_Api.domain.Host;
import yun.jung.kim.Host_Api.domain.WatchResult;

public interface WatchResultRepository extends JpaRepository<WatchResult, Long> {
}
