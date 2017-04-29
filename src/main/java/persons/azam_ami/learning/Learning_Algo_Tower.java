package persons.azam_ami.learning;

import persons.azam_ami.knowledge.repr.NeuralNet;

/**
 * Implement Algorithm Tower.
 * 
 * @author azam_ami@yahoo.com
 *
 */
public class Learning_Algo_Tower
{
    public static NeuralNet.Node createNewNode( NeuralNet nn )
    {
        
        int[] weights = new int[1 + nn.getVarCount()];  
        int[] inputVars = new int[ nn.getVarCount() ]; 
        int  outputVar = nn.getVarCount();
        
        // Weights
        for( int i=0; i<nn.getVarCount(); i++ )
        {
            weights[i] = 0;
        }
        
        // Use previous output as it is.
        weights[nn.getVarCount()] = 1;
        
        // InputVars
        for( int i=0; i<nn.getVarCount(); i++ )
        {
            inputVars[i] = i;
        }
        
        NeuralNet.Node ans = new NeuralNet.Node( weights, inputVars, outputVar );
        return ans;
    }
    
    /**
     * Create new Neural-Net by add one new node and put on top previous nodes.
     * 
     * @param nn
     * @return
     */
    public static NeuralNet createNewNeuralNet( NeuralNet nn )
    {
        NeuralNet.Node newNode = createNewNode( nn );
        NeuralNet ans = new NeuralNet( nn );
        ans.addNode( newNode );
        return ans;
    }
}
