package co.jcforero.simplestore.model.product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Product {
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private String urlPicture;

}
