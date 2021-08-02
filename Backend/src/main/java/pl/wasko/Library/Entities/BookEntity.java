package pl.wasko.Library.Entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private AuthorEntity authorEntity;

    private String title;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private BookTypeEntity type;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate publicationDate;

    private long quantity;

    @Lob
    @Column(length = 512)
    private String description;

    private String imageUrl;

}
