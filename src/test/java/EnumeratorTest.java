import net.kravuar.LexicographicalEnumerator;
import net.kravuar.LexicographicalEnumeratorImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class EnumeratorTest {
    private final LexicographicalEnumerator enumerator = new LexicographicalEnumeratorImpl();
    private final List<Character> alphabet = List.of('a', 'b', 'c');
    @Test
    public void shouldBe312() {
        Assert.assertEquals(312, enumerator.enumerate(
                alphabet, "cbaac"
        ));
    }
    @Test
    public void shouldBe0() {
        Assert.assertEquals(0, enumerator.enumerate(
                alphabet, ""
        ));
    }
    @Test
    public void shouldBe321() {
        Assert.assertEquals(321, enumerator.enumerate(
                alphabet, "cbbac"
        ));
    }
    @Test
    public void shouldBeCBBAC() {
        Assert.assertEquals("cbbac", enumerator.denumerate(
                alphabet, 321
        ));
    }
    @Test
    public void shouldBeCBAAC() {
        Assert.assertEquals("cbaac", enumerator.denumerate(
                alphabet, 312
        ));
    }
    @Test
    public void shouldBeEmpty() {
        Assert.assertEquals("", enumerator.denumerate(
                alphabet, 0
        ));
    }





    @Test
    public void shouldBe641() {
        Assert.assertEquals(641, enumerator.enumerate(
                List.of('а', 'з', 'к','т'), "закта"
        ));
    }
    @Test
    public void shouldBeТЗКЗ() {
        Assert.assertEquals("тзкз", enumerator.denumerate(
                List.of('а', 'з', 'к','т'), 302
        ));
    }
    @Test
    public void shouldBeAABBAABA() {
        Assert.assertEquals("aabbaaba", enumerator.denumerate(
                List.of('a', 'b'), 305
        ));
    }
}