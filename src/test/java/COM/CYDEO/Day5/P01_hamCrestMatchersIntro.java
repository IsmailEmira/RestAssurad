package COM.CYDEO.Day5;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class P01_hamCrestMatchersIntro {


    @Test
    public void numbers(){
        assertThat(4+6,is(10)); // actual should be equal
        assertThat(4+5,equalTo(9)); // actual == expected
        assertThat(4+8,is(not(equalTo(10)))); // actual != expected

        /**
         * They are all the same for assertion
         */

        assertThat(9+6,is(greaterThanOrEqualTo(15))); // actual great or equal than expected
        assertThat(6+6,greaterThan(9)); // actual greater than expected
        assertThat(4+5,lessThan(10)); // actual less than expected
        assertThat(7+8,lessThanOrEqualTo(15)); // actual less or equal than expected

    }

    @Test
    public void strings(){

        String msg = "API is fun";

        assertThat(msg,is("APi is Fun"));
        assertThat(msg,equalToIgnoringCase("API IS FUN"));
        assertThat(msg,equalTo("APi is Fun"));

        assertThat(msg,startsWith("API IS FUN"));
        assertThat(msg,endsWith("FUN"));

        assertThat(msg,containsString("Fun"));
        assertThat(msg,not(containsString("API")));

        assertThat(msg,startsWithIgnoringCase("api"));
        assertThat(msg,endsWithIgnoringCase("fun"));

    }

    @Test
    public void testCollections(){

        List<Integer> numberList = Arrays.asList(3,5,1,77,44,76); // 6 elements

        //how to check size of elements
        assertThat(numberList,hasSize(6));

        //how to check 77 is into the collection
        assertThat(numberList,hasItem(77));

        // how to check 44 and 76 is into the collection
        assertThat(numberList,hasItems(44,77));
        // loop through each of the element and make sure they are matching with Matchers inside the everyItem
        assertThat(numberList,everyItem(greaterThanOrEqualTo(1)));
        // check if you have values and their position
        assertThat(numberList,containsInRelativeOrder(3,5,1,77));
        // check if you have all the values, position might be different
        assertThat(numberList,containsInAnyOrder(3,5,1,77,44,76));


    }
}
