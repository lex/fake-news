package pw.yuuh.genuinenews.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category extends AbstractPersistable<Long> {
    @NotNull
    @Column(unique=true)
    @Size(min = 2, max = 100)
    private String name;

    @ManyToMany
    private List<NewsItem> newsItems;
}
