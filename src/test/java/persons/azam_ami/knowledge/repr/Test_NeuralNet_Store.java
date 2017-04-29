package persons.azam_ami.knowledge.repr;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Test_NeuralNet_Store
{
    Gson gson;
    
    @Before
    public void before()
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        //gson = gsonBuilder.setPrettyPrinting().serializeNulls().create();
        gson = gsonBuilder.serializeNulls().create();
    }
    
    @Test
    public void test_serde()
    {
        NeuralNet.Node node = new NeuralNet.Node( 
                new int[] { -1, 1, 1 }, 
                new int[] { 0, 1 }, 
                2 ); 
        NeuralNet nn = new NeuralNet( 2 );  // 2 input, 1 output
        nn.addNode( node );
        
        // Serialize
        final String json = gson.toJson( nn );
        System.out.println( json );
        
        // Deserialize
        NeuralNet nn_2 = gson.fromJson(json, NeuralNet.class );
        final String json_2 = gson.toJson( nn_2 );
        // System.out.println( json_2 );
        
        // Check
        assertEquals( json, json_2 );
    }

    @Test
    public void test_load() throws IOException
    {
        final String json = FileUtils.readFileToString( new File( "neural-net-store/a.json" ), "UTF-8" );
        System.out.println( json );
        
        NeuralNet nn_2 = gson.fromJson(json, NeuralNet.class );
        final String json_2 = gson.toJson( nn_2 );
        System.out.println( json_2 );
    }
    
}
