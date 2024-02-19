package yun.jung.kim.Host_Api.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "WATCHRESULT", indexes = {
        @Index(columnList = "eventResult"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
public class WatchResult extends AuditingFields{//감시 결과
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Watch watch;

    @Setter
    private  String eventResult;//사건 결과

    private WatchResult(String eventResult) {
        this.eventResult = eventResult;
    }

    public static WatchResult of(String eventResult) {
        return new WatchResult(eventResult);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WatchResult watchResult)) return false;
        return id == watchResult.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}