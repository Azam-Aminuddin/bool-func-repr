package persons.azam_ami.knowledge.repr;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import persons.azam_ami.learning.Learning_Algo_Tower;
import persons.azam_ami.learning.Learning_Base;
import persons.azam_ami.learning.Learning_Ws;

public class Main
{
    private static String getArg( final String arg, final String startsWith )
    {
        if( arg.startsWith( startsWith ))
        {
            return arg.substring( startsWith.length() );
        }
        return null;
    }
    
    private static void errorMissingArg( final String argName )
    {
        System.out.println( "*** Missing required argument \"" + argName + "\"." );
    }
    
    public static void doLearn( 
            final String nn_filename,
            final String ts_filename,
            int node_tower_count ) throws IOException
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.serializeNulls().create();
        
        String json = FileUtils.readFileToString( new File( nn_filename ), "UTF-8" );
        NeuralNet nn = gson.fromJson(json, NeuralNet.class );
        nn.afterLoad();
        
        json = FileUtils.readFileToString( new File( ts_filename ), "UTF-8" );
        Type listType = new TypeToken<ArrayList<Dataitem>>(){}.getType();
        List<Dataitem> items = gson.fromJson(json, listType );
        
        System.out.println( "--- PREV ---" );
        nn.displayTo( System.out );
        
        Learning_Ws ws = new Learning_Ws();
        Learning_Base.doLearn(nn, items, 30, ws );
        System.out.println( "--- AFTER ---" );
        System.out.println( "--- success_count: " + ws.success_count );
        ws.selected.displayTo( System.out );
        if( items.size()==ws.success_count )
        {
            // Success
            return;
        }
        
        while( true )
        {
            if( node_tower_count<=0 ) break;
            node_tower_count--;
            
            System.out.println( "===== ALGORITHM TOWER =====" );
            NeuralNet nn_1 = Learning_Algo_Tower.createNewNeuralNet( ws.selected );
            nn_1.displayTo( System.out );
            ws = new Learning_Ws();
            Learning_Base.doLearn(nn_1, items, 30, ws );
            System.out.println( "--- AFTER ---" );
            System.out.println( "--- success_count: " + ws.success_count );
            ws.selected.displayTo( System.out );
            if( items.size()==ws.success_count )
            {
                // Success
                return;
            }
        }
        
    }
    
    public static void main( final String[] args ) throws IOException
    {
        String nn = null;
        String ts = null;
        int tower = 0;
        for( String arg : args )
        {
            //  System.out.println( arg );
            String arg_value = getArg( arg, "nn=" );
            if( arg_value!=null ) 
            {
                nn = arg_value;
                continue;
            }

            arg_value = getArg( arg, "ts=" );
            if( arg_value!=null ) 
            {
                ts = arg_value;
                continue;
            }
            
            arg_value = getArg( arg, "tower=" );
            if( arg_value!=null ) 
            {
                tower = Integer.parseInt( arg_value );
                continue;
            }
        }
        
        if( nn==null )
        {
            errorMissingArg( "nn" );
            return;
        }
        if( ts==null )
        {
            errorMissingArg( "ts" );
            return;
        }
        
        doLearn( nn, ts, tower );
    }
}
