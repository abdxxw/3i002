package pobj.motx.tme3.test;

import org.junit.Test;

import pobj.motx.tme3.csp.CSPSolver;
import pobj.motx.tme3.csp.StratAlea;
import pobj.motx.tme3.csp.StratBase;
import pobj.motx.tme3.csp.StratFirst;
import pobj.motx.tme3.csp.StratFreq;

public class GrilleSolverTestFirst2 {
	CSPSolver solver = new CSPSolver();
	@Test
	public void testLarge_First_Base() {
		solver.setChoixVarStrat(new StratFirst());
		solver.setChoixValStrat(new StratBase());
		SolverTest.Solve(SolverTest.getProblem("data/large.grl"), solver);
	}	
	@Test
	public void testLarge_First_Alea() {
		solver.setChoixVarStrat(new StratFirst());
		solver.setChoixValStrat(new StratAlea());
		SolverTest.Solve(SolverTest.getProblem("data/large.grl"), solver);
		
	}	
	@Test
	public void testLarge_First_Freq() {
		solver.setChoixVarStrat(new StratFirst());
		solver.setChoixValStrat(new StratFreq());
		SolverTest.Solve(SolverTest.getProblem("data/large.grl"), solver);
	}	
	@Test
	public void testLarge2_First_Base() {
		solver.setChoixVarStrat(new StratFirst());
		solver.setChoixValStrat(new StratBase());
		SolverTest.Solve(SolverTest.getProblem("data/large2.grl"), solver);
	}	
	@Test
	public void testLarge2_First_Alea() {
		solver.setChoixVarStrat(new StratFirst());
		solver.setChoixValStrat(new StratAlea());
		SolverTest.Solve(SolverTest.getProblem("data/large2.grl"), solver);
	}	
	@Test
	public void testLarge2_First_Freq() {
		solver.setChoixVarStrat(new StratFirst());
		solver.setChoixValStrat(new StratFreq());
		SolverTest.Solve(SolverTest.getProblem("data/large2.grl"), solver);
	}	

	@Test
	public void testLarger_First_Base() {
		solver.setChoixVarStrat(new StratFirst());
		solver.setChoixValStrat(new StratBase());
		SolverTest.Solve(SolverTest.getProblem("data/larger.grl"), solver);
	}	
	@Test
	public void testLarger_First_Alea() {
		solver.setChoixVarStrat(new StratFirst());
		solver.setChoixValStrat(new StratAlea());
		SolverTest.Solve(SolverTest.getProblem("data/larger.grl"), solver);
	}	
	@Test
	public void testLarger_First_Freq() {
		solver.setChoixVarStrat(new StratFirst());
		solver.setChoixValStrat(new StratFreq());
		SolverTest.Solve(SolverTest.getProblem("data/larger.grl"), solver);
	}	
	

}
