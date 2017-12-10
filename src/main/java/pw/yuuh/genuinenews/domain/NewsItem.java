package pw.yuuh.genuinenews.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewsItem extends AbstractPersistable<Long> {
    @NotNull
    @Size(min = 5, max = 100)
    private String title;

    @NotNull
    @Size(min = 5, max = 100)
    private String lead;

    @NotNull
    @Size(min = 5, max = 10000)
    private String image;

    @NotNull
    @Size(min = 5, max = 10000)
    private String content;

    @NotNull
    private long timesRead;

    @NotNull
    private LocalDateTime publishedAt;

    @NotNull
    @ManyToMany(mappedBy="newsItems")
    private List<Category> categories;

    @NotNull
    @ManyToMany(mappedBy="newsItems")
    private List<Author> authors;
}
