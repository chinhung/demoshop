package cucumber.glue;

import net.chinhung.application.endpoint.product.ProductDTO;
import net.chinhung.application.endpoint.product.ProductEndpoint;
import net.chinhung.application.endpoint.product.CreateProduct;
import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ScenarioScoped
public class ProductStepdefs {

    private final ProductEndpoint productEndpoint;

    private List<ProductDTO> products;

    @Inject
    public ProductStepdefs(final SharedStates sharedStates) {
        this.productEndpoint = sharedStates.getApplication().getProductEndpoint();
    }

    @DataTableType
    public ProductDTO productEntry(final Map<String, String> entry) {
        final ProductDTO product = new ProductDTO();
        product.setId(entry.get("id"));
        product.setName(entry.get("name"));
        product.setPrice(Integer.valueOf(entry.get("price")));
        product.setStockQuantity(Integer.valueOf(entry.get("stockQuantity")));
        return product;
    }

    @When("query for all products")
    public void queryForAllProducts() {
        products = productEndpoint.findProducts();
    }

    @Then("got a product list")
    public void gotAProdutList() {
        Assertions.assertNotNull(products, "product list not exists");
        Assertions.assertTrue(products.size() > 0, "no product in the product list");
    }

    @Given("^product (.*) not exists$")
    public void productNotExists(final String name) {
        queryForAllProducts();
        Assertions.assertFalse(ProductUtil.containsWithSameName(products, name), name + " already exists");
    }

    @When("^create product (.*) with price (.*)$")
    public void createProductWithPrice(final String name, final int price) {
        final CreateProduct createProduct = new CreateProduct();
        createProduct.setName(name);
        createProduct.setPrice(String.valueOf(price));
        productEndpoint.createProduct(createProduct);
    }

    @Given("^the following products exist:$")
    public void theFollowingProductsExist(final List<ProductDTO> products) {
        queryForAllProducts();
        products.stream().forEach(product -> {
            Assertions.assertTrue(ProductUtil.contains(this.products, product), product.getName() + " not exists");
        });
    }

    @Then("the stock quantities of the following products are excepted:")
    public void theStockQuantitiesOfTheFollowingProductsAreExcepted(final List<ProductDTO> products) {
        theFollowingProductsExist(products);
    }
}

class ProductUtil {

    static boolean containsWithSameName(final List<ProductDTO> products, final String name) {
        final Optional<ProductDTO> filtered = products.stream().filter(product -> product.getName().equals(name)).findAny();
        return filtered.isPresent();
    }

    static boolean contains(final List<ProductDTO> products, final ProductDTO target) {
        final Optional<ProductDTO> filtered = products.stream().filter(product -> {
            final boolean idPass;
            if ("any".equals(target.getId())) {
                idPass = true;
            } else {
                idPass = product.getId().equals(target.getId());
            }
            final boolean namePass = product.getName().equals(target.getName());
            final boolean pricePass = product.getPrice().equals(target.getPrice());
            final boolean stockQuantityPass = product.getStockQuantity().equals(target.getStockQuantity());
            return idPass && namePass && pricePass && stockQuantityPass;
        }).findAny();
        return filtered.isPresent();
    }
}