package br.helios.simplex.domain.problem.parser;

import static br.helios.simplex.domain.problem.Objective.MAXIMIZATION;
import static br.helios.simplex.domain.problem.Objective.MINIMIZATION;
import static br.helios.simplex.domain.problem.ObjectiveFunctionTestHelper.assertObjective;
import static br.helios.simplex.domain.problem.ObjectiveFunctionTestHelper.assertTermCreated;
import static br.helios.simplex.domain.problem.parser.ParserVariables.getVariables;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class ObjectiveFunctionParserTest {

	@Before
	public void setUp() {
		ParserVariables.clear();
	}

	@Test
	public void testMaximizationProblem() {
		// verify
		assertObjective("max z = x1", MAXIMIZATION);
	}

	@Test
	public void testMinimizationProblem() {
		// verify
		assertObjective("min z = x1", MINIMIZATION);
	}

	@Test
	public void testTerms() {
		// setup
		String inputData = "max z = x1 - 2x2 -0.5x3 +100x4 + 200x5-8x6";
		// verify
		assertTermCreated(inputData, "x1", new BigDecimal("1"), getVariables());
		assertTermCreated(inputData, "x2", new BigDecimal("-2"), getVariables());
		assertTermCreated(inputData, "x3", new BigDecimal("-0.5"), getVariables());
		assertTermCreated(inputData, "x4", new BigDecimal("100"), getVariables());
		assertTermCreated(inputData, "x5", new BigDecimal("200"), getVariables());
		assertTermCreated(inputData, "x6", new BigDecimal("-8"), getVariables());
	}

}
