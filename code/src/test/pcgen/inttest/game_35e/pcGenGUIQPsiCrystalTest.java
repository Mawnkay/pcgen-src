package pcgen.inttest.game_35e;

import pcgen.inttest.pcGenGUITestCase;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Tests a 35e Psion's PsiCrystal.
 * See the PCG file for details
 */
@SuppressWarnings("nls")
public class pcGenGUIQPsiCrystalTest extends pcGenGUITestCase
{

	/**
	 * 
	 */
	public pcGenGUIQPsiCrystalTest()
	{
		// Empty Constructor
	}

	/**
	 * standard JUnit style constructor
	 * 
	 * @param name
	 */
	public pcGenGUIQPsiCrystalTest(String name)
	{
		super(name);
	}

	/**
	 * @return A <tt>TestSuite</tt>
	 */
	public static Test suite()
	{
		return new TestSuite(pcGenGUIQPsiCrystalTest.class);
	}

	/**
	 * Loads and outputs the character.
	 * 
	 * @throws Exception If an error occurs.
	 */
	public void testQPsiCrystal() throws Exception
	{
		runTest("Q-PsiCrystal", "35e");
	}
}
