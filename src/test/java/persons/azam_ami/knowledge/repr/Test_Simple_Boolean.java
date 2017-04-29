package persons.azam_ami.knowledge.repr;

import static org.junit.Assert.*;

import org.junit.Test;

// "Neural Network Learning and Expert Systems"
// https://mitpress.mit.edu/books/neural-network-learning-and-expert-systems
public class Test_Simple_Boolean
{
    @Test
    public void test_AND()
    {
        NeuralNet.Node node = new NeuralNet.Node( 
                new int[] { -1, 1, 1 }, 
                new int[] { 0, 1 }, 
                2 ); 
        NeuralNet nn = new NeuralNet( 2 );  // 2 input, 1 output
        nn.addNode( node );
        
        nn.displayTo( System.out );
        
        assertEquals( 1, nn.doGetOutput( new int[] { 1, 1 } ));
        assertEquals( -1, nn.doGetOutput( new int[] { 1, -1 } ));
        assertEquals( -1, nn.doGetOutput( new int[] { -1, 1 } ));
        assertEquals( -1, nn.doGetOutput( new int[] { -1, -1 } ));
    }

    @Test
    public void test_OR()
    {
        NeuralNet.Node node = new NeuralNet.Node( 
                new int[] { 1, 1, 1 }, 
                new int[] { 0, 1 }, 
                2 ); 
        NeuralNet nn = new NeuralNet( 2 ); // 2 input, 1 output
        nn.addNode( node );
        
        nn.displayTo( System.out );
        
        assertEquals( 1, nn.doGetOutput( new int[] { 1, 1 } ));
        assertEquals( 1, nn.doGetOutput( new int[] { 1, -1 } ));
        assertEquals( 1, nn.doGetOutput( new int[] { -1, 1 } ));
        assertEquals( -1, nn.doGetOutput( new int[] { -1, -1 } ));
    }

    @Test
    public void test_NOT()
    {
        NeuralNet.Node node = new NeuralNet.Node( 
                new int[] { 0, -1 }, 
                new int[] { 0 }, 
                1 ); 
        NeuralNet nn = new NeuralNet( 1 );  // 1 input, 1 output
        nn.addNode( node );
        
        nn.displayTo( System.out );
        
        assertEquals( -1, nn.doGetOutput( new int[] { 1 } ));
        assertEquals( 1, nn.doGetOutput( new int[] { -1 } ));
    }
}
