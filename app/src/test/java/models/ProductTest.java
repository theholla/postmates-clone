package models;

import com.epicodus.postmatesclone.models.Product;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml")
public class ProductTest extends TestCase {

    @Test
    public void productInstantiates() {
        Product product = new Product("JuLatte", "Lattes", 5);
        assertThat(product, instanceOf(Product.class));
    }
}
