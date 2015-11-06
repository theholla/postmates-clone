import com.epicodus.postmatesclone.BuildConfig;
import com.epicodus.postmatesclone.models.Product;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ProductTest extends TestCase {
    @Test public void productInstantiates() {
        Product product = new Product("JuLatte", "Lattes", 5);
        assertThat(product.getCompany(), is("JuLatte"));
    }

}
