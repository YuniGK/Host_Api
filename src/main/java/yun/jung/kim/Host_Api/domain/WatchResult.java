package yun.jung.kim.Host_Api.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

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
public class WatchResult {//감시 결과
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Host host;

    @Setter @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Watch watch;

    @Setter
    private  String eventResult;//사건 결과

    @CreatedDate @Column(nullable = false)
    private LocalDateTime createdAt;//사건 발생 일시
    @CreatedBy @Column(nullable = false)
    private String createdBy;//사건 발생 주체의 시원
    @LastModifiedDate @Column(nullable = false)
    private LocalDateTime modifiedAt;//사건 수정 일시
    @LastModifiedBy @Column(nullable = false)
    private String modifiedBy;//사건 발생 주체 수정자

    public WatchResult(String eventResult) {
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
