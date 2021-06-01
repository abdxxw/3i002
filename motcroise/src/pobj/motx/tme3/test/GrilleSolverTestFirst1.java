package pobj.motx.tme3.test;

import org.junit.Test;

import pobj.motx.tme3.csp.CSPSolver;
import pobj.motx.tme3.csp.StratAlea;
import pobj.motx.tme3.csp.StratBase;
import pobj.motx.tme3.csp.StratFirst;
import pobj.motx.tme3.csp.StratFreq;

public class GrilleSolverTestFirst1 {
	CSPSolver solver = new CSPSolver();

	@Test
	public void testEasy_First_Base() {
		solver.setChoixVarStrat(new StratFirst());
		solver.setChoixValStrat(new StratBase());
		SolverTest.Solve(SolverTest.getProblem("data/easy.grl"), solver);
	}	
	@Test
	public void testEasy_First_Alea() {
		solver.setChoixVarStrat(new StratFirst());
		solver.setChoixValStrat(new StratAlea());
		SolverTest.Solve(SolverTest.getProblem("data/easy.grl"), solver);
	}	
	@Test
	public void testEasy_First_Freq() {
		solver.setChoixVarStrat(new StratFirst());
		solver.setChoixValStrat(new StratFreq());
		SolverTest.Solve(SolverTest.getProblem("data/easy.grl"), solver);
	}	
	@Test
	public void testMeduim_First_Base() {
		solver.setChoixVarStrat(new StratFirst());
		solver.setChoixValStrat(new StratBase());
		SolverTest.Solve(SolverTest.getProblem("data/medium.grl"), solver);
	}	
	@Test
	public void testMeduim_First_Alea() {
		solver.setChoixVarStrat(new StratFirst());
		solver.setChoixValStrat(new StratAlea());
		SolverTest.Solve(SolverTest.getProblem("data/medium.grl"), solver);
		
	}	
	@Test
	public void testMeduim_First_Freq() {
		solver.setChoixVarStrat(new StratFirst());
		solver.setChoixValStrat(new StratFreq());
		SolverTest.Solve(SolverTest.getProblem("data/medium.grl"), solver);
	}		@Test
	public void testHard_First_Base() {
		solver.setChoixVarStrat(new StratFirst());
		solver.setChoixValStrat(new StratBase());
		SolverTest.Solve(SolverTest.getProblem("data/hard.grl"), solver);
	}	
	@Test
	public void testHard_First_Alea() {
		solver.setChoixVarStrat(new StratFirst());
		solver.setChoixValStrat(new StratAlea());
		SolverTest.Solve(SolverTest.getProblem("data/hard.grl"), solver);
	}	
	@Test
	public void testHard_First_Freq() {
		solver.setChoixVarStrat(new StratFirst());
		solver.setChoixValStrat(new StratFreq());
		SolverTest.Solve(SolverTest.getProblem("data/hard.grl"), solver);
	}	


		

}
