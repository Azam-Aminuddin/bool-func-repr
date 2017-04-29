package persons.azam_ami.learning;

import persons.azam_ami.knowledge.repr.NeuralNet;

/**
 * Represent Learning Workspace.
 * 
 * @author azam_ami@yahoo.com
 *
 */
public class Learning_Ws
{
    // Current selected Neural-Net.
    public NeuralNet selected = null;
    
    // Success count of current selected Neural-Net. 
    public int success_count = 0;
    
    // Loop count so far.
    public int loop_count = 0;
}
