package project.laundry.entity;

import jakarta.persistence.*;

@Entity
public class customer {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private owner owner;
}
