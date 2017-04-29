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

public class Test_Dataitem_Store
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
    public void test() throws IOException
    {
        final String json = FileUtils.readFileToString( new File( "dataitem-store/and.json" ), "UTF-8" );
        System.out.println( json );
        
        Type listType = new TypeToken<ArrayList<Dataitem>>(){}.getType();
        List<Dataitem> items = gson.fromJson(json, listType );
    }

}
