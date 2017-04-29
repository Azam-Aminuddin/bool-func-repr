package persons.azam_ami.learning;

import java.util.List;

import persons.azam_ami.knowledge.repr.Dataitem;
import persons.azam_ami.knowledge.repr.NeuralNet;

public class Learning_Base
{
    public static Boolean[] createNewTestResults( final List<Dataitem> dataitems )
    {
        Boolean[] ans = new Boolean[dataitems.size()];
        return ans;
    }
    
    /**
     * Test Neural-Net {@code nn} against all {@code dataitems} and keep the {@code results}
     * 
     * @param nn
     * @param dataitems
     * @param results
     * @return
     */
    public static int testAll(
            final NeuralNet nn,
            final List<Dataitem> dataitems,
            Boolean[] results )
    {
        int correct = 0;
        int results_idx = 0;
        for( Dataitem di : dataitems )
        {
            int m = nn.doGetOutput( di.getInput() );
            
            if( m==di.getOutput()[0] )
            {
                correct++;
                results[results_idx] = Boolean.TRUE;
            }
            else
            {
                results[results_idx] = Boolean.FALSE;
            }
            
            // Next
            results_idx++;
        }
        return correct;        
    }
    
    public static void doUpdate(
            NeuralNet.Node node,
            final int[] output,
            final int[] inputs )
    {
        node.weights[0] += 1 * output[0];
        for( int i=1; i<node.weights.length; i++ )
        {
            node.weights[i] += inputs[node.inputVars[i-1]] * output[0];
        }
    }
    
    
    public static void doLearn( 
            NeuralNet nn, 
            List<Dataitem> dataitems,
            int max_loop_count,
            Learning_Ws ws )
    {
        int data_idx = 0;
        int correct = 0;
        
        Boolean results[] = createNewTestResults( dataitems );
        
        ws.selected = nn;
        ws.success_count = testAll( nn, dataitems, results );
        // System.out.println( "--- success_count: " + ws.success_count );
        
        NeuralNet.Node learning_node = nn.getLastNode();
        while( true )
        {
            Dataitem dataitem = dataitems.get(data_idx);
            int m = nn.doGetOutput( dataitem.getInput() );
            if( m==dataitem.getOutput()[0] )
            {
                correct++;
            }
            else
            {
                doUpdate( learning_node, dataitem.getOutput(), nn.getVars() );
                int nn_success_count = testAll( nn, dataitems, results );
                if( nn_success_count>ws.success_count )
                {
                    ws.selected = new NeuralNet( nn );
                    ws.success_count = nn_success_count;
                    // System.out.println( "--- success_count: " + ws.success_count );
                }
            }
            
            data_idx++;
            if( data_idx>=dataitems.size() )
            {
                data_idx = 0;
                if( correct==dataitems.size() )
                {
                    // System.out.println( "All correct." );
                    break;
                }
                else
                {
                    correct = 0;
                }
            }
            ws.loop_count++;
            if( ws.loop_count>=max_loop_count )
            {
                break;
            }
        }
    }
}
