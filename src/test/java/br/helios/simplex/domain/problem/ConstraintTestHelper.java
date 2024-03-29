package br.helios.simplex.domain.problem;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import br.helios.simplex.domain.problem.parser.ConstraintParser;
import br.helios.simplex.domain.problem.variable.Variable;
import br.helios.simplex.domain.problem.variable.Variables;

public class ConstraintTestHelper {

	public static void assertOperator(String inputConstraint, Operator expectedOperator) {
		Constraint constraint = new ConstraintParser().parse(inputConstraint, 1);
		assertOperator(constraint, expectedOperator);
	}

	public static void assertOperator(Constraint constraint, Operator expectedOperator) {
		assertEquals(expectedOperator, constraint.getOperator());
	}

	public static void assertValue(String inputConstraint, BigDecimal expectedValue) {
		Constraint constraint = new ConstraintParser().parse(inputConstraint, 1);
		assertValue(constraint, expectedValue);
	}

	public static void assertValue(Constraint constraint, BigDecimal expectedValue) {
		assertTrue(expectedValue.compareTo(constraint.getConstraintValue()) == 0);
	}

	public static void assertConstraint(Constraint constraint, Operator expectedOperator, BigDecimal expectedValue) {
		assertValue(constraint, expectedValue);
		assertOperator(constraint, expectedOperator);
	}

	public static void assertTermCreated(String inputConstraint, String variableName, BigDecimal coefficient, Variables variables) {
		Constraint constraint = parse(inputConstraint);
		assertTermCreated(constraint, variableName, coefficient, variables);
	}

	public static void assertTermCreated(Constraint constraint, String variableName, BigDecimal coefficient, Variables variables) {
		Variable variable = variables.getByName(variableName);
		Term term = constraint.getTermByVariable(variable);
		assertThat(term.getCoefficient(), is(coefficient));
	}

	private static Constraint parse(String constraint) {
		return new ConstraintParser().parse(constraint, 1);
	}
}
