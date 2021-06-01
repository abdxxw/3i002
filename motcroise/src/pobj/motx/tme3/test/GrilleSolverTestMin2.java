package pobj.motx.tme3.test;

import org.junit.Test;

import pobj.motx.tme3.csp.CSPSolver;
import pobj.motx.tme3.csp.StratAlea;
import pobj.motx.tme3.csp.StratBase;
import pobj.motx.tme3.csp.StratFreq;
import pobj.motx.tme3.csp.StratMin;

public class GrilleSolverTestMin2 {
	CSPSolver solver = new CSPSolver();
	@Test
	public void testLarge_Min_Base() {
		solver.setChoixVarStrat(new StratMin());
		solver.setChoixValStrat(new StratBase());
		SolverTest.Solve(SolverTest.getProblem("data/large3.grl"), solver);
	}		
	@Test
	public void testLarge_Min_Alea() {
		solver.setChoixVarStrat(new StratMin());
		solver.setChoixValStrat(new StratAlea());
		SolverTest.Solve(SolverTest.getProblem("data/large3.grl"), solver);
	}		
	@Test
	public void testLarge_Min_Freq() {
		solver.setChoixVarStrat(new StratMin());
		solver.setChoixValStrat(new StratFreq());
		SolverTest.Solve(SolverTest.getProblem("data/large3.grl"), solver);
	}	
	@Test
	public void testLarge2_Min_Base() {
		solver.setChoixVarStrat(new StratMin());
		solver.setChoixValStrat(new StratBase());
		SolverTest.Solve(SolverTest.getProblem("data/large2.grl"), solver);
	}		
	@Test
	public void testLarge2_Min_Alea() {
		solver.setChoixVarStrat(new StratMin());
		solver.setChoixValStrat(new StratAlea());
		SolverTest.Solve(SolverTest.getProblem("data/large2.grl"), solver);
	}		
	@Test
	public void testLarge2_Min_Freq() {
		solver.setChoixVarStrat(new StratMin());
		solver.setChoixValStrat(new StratFreq());
		SolverTest.Solve(SolverTest.getProblem("data/large2.grl"), solver);
	}	


}
