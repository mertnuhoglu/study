package factual;

import com.factual.driver.Factual;
import com.factual.driver.Query;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Factual factual = new Factual("rK4XzK5dCkb01fHK7gSHdBOMFELN4rZO2KdxZwYq", "BL93yvA9orEj1X1NdCTQNYuWsbD7bYDzikoC8IZg");

        System.out.println(factual.fetch("places", new Query().limit(3)));
    }
}
