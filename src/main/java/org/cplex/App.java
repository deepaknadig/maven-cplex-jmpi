package org.cplex;

import net.sf.jmpi.main.MpDirection;
import net.sf.jmpi.main.MpProblem;
import net.sf.jmpi.main.MpResult;
import net.sf.jmpi.main.MpSolver;
import net.sf.jmpi.main.expression.MpExpr;
import net.sf.jmpi.solver.cplex.SolverCPLEX;

import static net.sf.jmpi.main.expression.MpExpr.prod;
import static net.sf.jmpi.main.expression.MpExpr.sum;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MpSolver solver = new SolverCPLEX();

        MpProblem problem = new MpProblem();

        problem.addVar("x", Integer.class);
        problem.addVar("y", Integer.class);

        MpExpr objective = sum(prod(143, "x"), prod(60, "y"));
        problem.setObjective(objective, MpDirection.MAX);

        problem.add(sum(prod(100, "x"), prod(20, "x"), prod(210, "y")), "<=", 15000);
        problem.add(sum(prod(110, "x"), prod(30, "y")), "<=", 4000);
        problem.add(sum("x"), "<=", sum(75, prod(-1, "y")));

        solver.add(problem);

        MpResult result = solver.solve();
        System.out.println(result);

    }
}
