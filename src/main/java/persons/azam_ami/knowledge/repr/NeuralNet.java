package persons.azam_ami.knowledge.repr;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represent a Neural-Net.
 * 
 * @author azam_ami@yahoo.com
 *
 */
public class NeuralNet
{
    /**
     * Represent a Node.
     * 
     * @author azam_ami@yahoo.com
     *
     */
    public static class Node
    {
        public int[] weights;
        public int[] inputVars;
        public int outputVar;
        
        public Node( 
                final int[] weights, 
                final int[] inputVars, 
                final int  outputVar )
        {
            this.weights = weights;
            this.inputVars = inputVars;
            this.outputVar = outputVar;
        }

        public Node(final Node rhs )
        {
            weights = rhs.weights.clone();
            inputVars = rhs.inputVars.clone();
            outputVar = rhs.outputVar;
        }
    }
    
    protected int inputCount;
    protected List<Node> nodes = new ArrayList<Node>();
    
    // don't serialize this field.
    private transient int varCount;
    private transient int[] vars;
    
    public NeuralNet( int inputCount )
    {
        this.inputCount = inputCount;
    }
    
    public NeuralNet( NeuralNet rhs )
    {
        for( Node node : rhs.nodes )
        {
            addNode( new Node( node ) );
        }
        inputCount = rhs.inputCount;
        varCount = rhs.varCount;
    }

    public void afterLoad()
    {
        varCount = inputCount + nodes.size();
    }
    
    public List<Node> getNodes()
    {
        return nodes;
    }
    
    public Node getLastNode()
    {
        return nodes.get( nodes.size() - 1 );
    }
    
    public int getInputCount()
    {
        return inputCount;
    }
    
    public int getVarCount()
    {
        return varCount;
    }
    
    public int[] getVars()
    {
        return vars;
    }
    
    public NeuralNet addNode( final Node node )
    {
        nodes.add( node );
        varCount = inputCount + nodes.size();
        return this;
    }
    
    private int doNodeCalculate( final Node nodeX )
    {
        int ans = nodeX.weights[0];
        for( int i=1; i<nodeX.weights.length; i++ )
        {
            ans += nodeX.weights[i] * vars[nodeX.inputVars[i-1]];
        }
        return ans;
    }

    private int doNodeActivationFunction( final int sum )
    {
        int ans = 0;
        if( sum>0 )
        {
            ans = 1;
        }
        else if( sum<0 )
        {
            ans = -1;
        }
        return ans;
    }
    
    public int doCalculate( )
    {
        for( Node node : nodes )
        {
            int sum = doNodeCalculate( node );
            int ans = doNodeActivationFunction( sum );
            vars[node.outputVar] = ans;
        }
        return 0;
    }
    
    public void doPrepareVars( final int[] input )
    {
        vars = new int[varCount];
        for( int i=0; i<input.length; i++ )
        {
            vars[i] = input[i];
        }
        for( int i=input.length; i<varCount; i++ )
        {
            vars[i] = 0;
        }
    }
    
    public int doGetOutput( final int[] input )
    {
        doPrepareVars( input );
        doCalculate();

        Node lastNode = nodes.get( nodes.size()-1 );
        int ans = vars[lastNode.outputVar];
        return ans;
    }

    public void displayTo(PrintStream out)
    {
        final String indent_1 = "   ";
        final String indent_2 = indent_1 + indent_1;
        final String indent_3 = indent_2 + indent_1;
        
        out.println( "NeuralNet:");
        out.println( indent_1 + "inputCount: " + inputCount );
        out.println( indent_1 + "nodeCount: " + nodes.size() );
        out.println( indent_1 + "varCount: " + varCount );
        int node_idx = 0;
        for( Node node : nodes )
        {
            out.println( indent_2 + "[" + (node_idx++) + "] outputVar: " + node.outputVar );
            out.println( indent_3 + "inputVars: " + Arrays.toString( node.inputVars ) );
            out.println( indent_3 + "weights: " + Arrays.toString( node.weights ) );
        }
    }

}
