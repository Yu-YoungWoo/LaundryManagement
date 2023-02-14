package project.laundry.entity;

import jakarta.persistence.*;
import lombok.*;
import project.laundry.entity.status.ClothStatus;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "post")
@Builder
public class post extends TimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "customer_name")
    private String name;

    @Column(name = "customer_phone")
    private String phone;

    private int clothCount;

    private int price;

    @Enumerated(EnumType.STRING)
    private ClothStatus status;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private owner owner;
}
