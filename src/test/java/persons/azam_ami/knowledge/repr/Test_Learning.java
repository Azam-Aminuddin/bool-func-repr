package persons.azam_ami.knowledge.repr;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import persons.azam_ami.learning.Learning_Base;
import persons.azam_ami.learning.Learning_Algo_Tower;
import persons.azam_ami.learning.Learning_Ws;

public class Test_Learning
{
    Gson gson;
    
    @Before
    public void before()
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.serializeNulls().create();
    }

    @Test
    public void test_simple_boolean() throws IOException
    {
        final String json = FileUtils.readFileToString( new File( "dataitem-store/and.json" ), "UTF-8" );
        // final String json = FileUtils.readFileToString( new File( "dataitem-store/or.json" ), "UTF-8" );
        // final String json = FileUtils.readFileToString( new File( "dataitem-store/xor.json" ), "UTF-8" );
        System.out.println( json );
        
        Type listType = new TypeToken<ArrayList<Dataitem>>(){}.getType();
        List<Dataitem> items = gson.fromJson(json, listType );

        // NULL NEURAL-NET
        NeuralNet.Node node = new NeuralNet.Node( 
                new int[] { 0, 0, 0 }, 
                new int[] { 0, 1 }, 
                2 ); 
        NeuralNet nn = new NeuralNet( 2 );  // 2 input, 1 output
        nn.addNode( node );
        System.out.println( "--- PREV ---" );
        nn.displayTo( System.out );
        
        Learning_Ws ws = new Learning_Ws();
        Learning_Base.doLearn(nn, items, 30, ws );
        NeuralNet saved = ws.selected;
        System.out.println( "--- AFTER ---" );
        System.out.println( "--- success_count: " + ws.success_count );
        saved.displayTo( System.out );
    }

    @Test
    public void test_xor() throws IOException
    {
        // final String json = FileUtils.readFileToString( new File( "dataitem-store/and.json" ), "UTF-8" );
        // final String json = FileUtils.readFileToString( new File( "dataitem-store/or.json" ), "UTF-8" );
        final String json = FileUtils.readFileToString( new File( "dataitem-store/xor.json" ), "UTF-8" );
        System.out.println( json );
        
        Type listType = new TypeToken<ArrayList<Dataitem>>(){}.getType();
        List<Dataitem> items = gson.fromJson(json, listType );

        // NULL NEURAL-NET
        NeuralNet.Node node = new NeuralNet.Node( 
                new int[] { 0, 0, 0 }, 
                new int[] { 0, 1 }, 
                2 ); 
        NeuralNet nn = new NeuralNet( 2 );  // 2 input, 1 output
        nn.addNode( node );
        System.out.println( "--- PREV ---" );
        nn.displayTo( System.out );
        
        Learning_Ws ws = new Learning_Ws();
        Learning_Base.doLearn(nn, items, 30, ws );
        // NeuralNet saved = ws.selected;
        System.out.println( "--- AFTER ---" );
        System.out.println( "--- success_count: " + ws.success_count );
        ws.selected.displayTo( System.out );
        
        System.out.println( "=== ALGORITHM TOWER ===" );
        NeuralNet nn_1 = Learning_Algo_Tower.createNewNeuralNet( ws.selected );
        nn_1.displayTo( System.out );
        ws = new Learning_Ws();
        Learning_Base.doLearn(nn_1, items, 30, ws );
        System.out.println( "--- AFTER ---" );
        System.out.println( "--- success_count: " + ws.success_count );
        ws.selected.displayTo( System.out );
        
    }
}
