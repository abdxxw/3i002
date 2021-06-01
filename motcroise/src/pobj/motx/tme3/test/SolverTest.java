package pobj.motx.tme3.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pobj.motx.tme1.Grille;
import pobj.motx.tme1.GrilleLoader;
import pobj.motx.tme1.GrillePlaces;
import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePotentiel;
import pobj.motx.tme3.csp.CSPSolver;
import pobj.motx.tme3.csp.ICSP;
import pobj.motx.tme3.csp.MotX;
import pobj.motx.tme3.csp.StratBase;
import pobj.motx.tme3.csp.StratFirst;

public class SolverTest {


	public static ICSP getProblem(String grilleFile) {
		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille(grilleFile);

		System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);
		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		
		assertTrue(! gp.isDead());

		ICSP problem = new MotX(gp);
		return problem;
	}
	
	
	public static void Solve(ICSP problem,CSPSolver solver) {
		long timestamp = System.currentTimeMillis();
		ICSP solution = solver.solve(problem);
		
		assertEquals(0, solution.getVars().size() );
		
		System.out.println("Solution :  \n" + solution + " \nCalculée en "+ (System.currentTimeMillis() - timestamp) +" ms " );
	}
}
