package org.milianz.yggdrasil_inn.Domain.Entities;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDate issueDate;
    private double total;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
