package persons.azam_ami.knowledge.repr;

/**
 * Represent a dataitem.
 * 
 * @author azam_ami@yahoo.com
 *
 */
public class Dataitem
{
    protected int[] input;
    protected int[] output;
    
    public Dataitem( int[] E, int[] C)
    {
        this.input = E;
        this.output = C;
    }
    
    public int[] getInput()
    {
        return input;
    }

    public int[] getOutput()
    {
        return output;
    }
}
