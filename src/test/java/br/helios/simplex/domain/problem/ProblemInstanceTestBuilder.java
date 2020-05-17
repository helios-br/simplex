package br.helios.simplex.domain.problem;

import java.util.ArrayList;
import java.util.List;

public class ProblemInstanceTestBuilder {

	/**
	 * Own fake problem
	 */
	public static Problem buildSimpleProblemA() {
		String objectiveFunction = "max z = x1 + 2x2 - 3x3";
		List<String> constraints = new ArrayList<String>();
		constraints.add("x1 - x3 <= 10");
		constraints.add("2x1 - 0.5x2 <= 0.5");
		constraints.add("x1 -2x2 - x3 <= 5");
		return build(objectiveFunction, constraints);
	}

	/**
	 * Hillier book
	 */
	public static Problem buildSimpleProblemB() {
		// z = 36, x1 = 2, x2 = 6
		String objectiveFunction = "max z = 3x1 + 5x2";
		List<String> constraints = new ArrayList<String>();
		constraints.add("x1 <= 4");
		constraints.add("2x2 <= 12");
		constraints.add("3x1 + 2x2 <= 18");
		return build(objectiveFunction, constraints);
	}

	/**
	 * http://fourier.eng.hmc.edu/e176/lectures/NM/node32.html
	 */
	public static Problem buildSimpleProblemC() {
		// z = 28, x1 = 5, x2 = 6
		String objectiveFunction = "max z = 2x1 + 3x2";
		List<String> constraints = new ArrayList<String>();
		constraints.add("2x1 + x2 <= 18");
		constraints.add("6x1 + 5x2 <= 60");
		constraints.add("2x1 + 5x2 <= 40");
		return build(objectiveFunction, constraints);
	}

	/**
	 * Hillier
	 */
	public static Problem buildProblemWithEquityConstraint() {
		// z = 28, x1 = 5, x2 = 6
		String objectiveFunction = "max z = 3x1 + 5x2";
		List<String> constraints = new ArrayList<String>();
		constraints.add("x1 <= 4");
		constraints.add("2x2 <= 12");
		constraints.add("3x1 + 2x2 = 18");
		return build(objectiveFunction, constraints);
	}

	/**
	 * https://www.oocities.org/vuumanj/BusinessAlgebra/SimplexMinimize.html
	 */
	public static Problem buildSimpleMinimizationProblemA() {
		// z = 8, x1 = 4, x2 = 0
		String objectiveFunction = "min z = -2x1 + x2";
		List<String> constraints = new ArrayList<String>();
		constraints.add("x1 + 2x2 <= 6");
		constraints.add("3x1 + 2x2 <= 12");
		return build(objectiveFunction, constraints);
	}

	/**
	 * LINDO
	 */
	public static Problem buildSimpleMinimizationProblemB() {
		// z = -11.333333333333334
		String objectiveFunction = "min z = -2x1 + x2 - 5x3";
		List<String> constraints = new ArrayList<String>();
		constraints.add("x1 + 2x2 <= 6");
		constraints.add("3x1 + 5x3 <= 12");
		constraints.add("x3 <= 2");
		return build(objectiveFunction, constraints);
	}

	public static Problem buildProblemWithEquityAndGreaterEqualConstraints() {
		String objectiveFunction = "min z = 0.4x1 + 0.5x2";
		List<String> constraints = new ArrayList<String>();
		constraints.add("0.3x1 + 0.1x2 <= 2.7");
		constraints.add("0.5x1 + 0.5x2 = 6");
		constraints.add("0.6x1 + 0.4x2 >= 6");
		return build(objectiveFunction, constraints);
	}

	public static Problem build(String objectiveFunction, List<String> constraints) {
		return ProblemTestBuilder.create(objectiveFunction, constraints).build();
	}
}