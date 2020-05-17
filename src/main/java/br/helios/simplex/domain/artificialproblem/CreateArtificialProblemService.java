package br.helios.simplex.domain.artificialproblem;

import static br.helios.simplex.domain.problem.Objective.MINIMIZATION;
import static br.helios.simplex.domain.problem.Operator.EQUAL;
import static br.helios.simplex.domain.problem.Operator.GREATER_EQUAL;
import static br.helios.simplex.domain.problem.Operator.LESS_EQUAL;

import java.util.ArrayList;
import java.util.List;

import br.helios.simplex.domain.problem.Constraint;
import br.helios.simplex.domain.problem.ObjectiveFunction;
import br.helios.simplex.domain.problem.Operator;
import br.helios.simplex.domain.problem.Problem;

public class CreateArtificialProblemService {

	private final LessEqualProcessor lessEqualProcessor;
	private final EqualProcessor equalProcessor;
	private final GreaterEqualProcessor greaterEqualProcessor;
	private final MinimizationProblemConverter minimizationProblemConverter;

	public CreateArtificialProblemService() {
		this.lessEqualProcessor = new LessEqualProcessor();
		this.equalProcessor = new EqualProcessor();
		this.greaterEqualProcessor = new GreaterEqualProcessor();
		this.minimizationProblemConverter = new MinimizationProblemConverter();
	}

	public Problem create(Problem originalProblem) {
		ObjectiveFunction newObjectiveFunction = new ObjectiveFunction(originalProblem.getObjectiveFunction());
		List<Constraint> newConstraints = new ArrayList<>();

		for (int i = 0; i < originalProblem.getConstraints().size(); i++) {
			Constraint originalConstraint = originalProblem.getConstraints().get(i);
			ConstraintProcessor constraintProcessor = getConstraintProcessor(originalConstraint.getOperator());
			constraintProcessor.createConstraint(newObjectiveFunction, newConstraints, originalConstraint);
		}

		if (originalProblem.getObjective() == MINIMIZATION) {
			newObjectiveFunction = minimizationProblemConverter.convert(newObjectiveFunction);
		}

		return new Problem(newObjectiveFunction, newConstraints);
	}

	private ConstraintProcessor getConstraintProcessor(Operator constraintOperator) {
		if (constraintOperator == LESS_EQUAL) {
			return lessEqualProcessor;
		} else if (constraintOperator == EQUAL) {
			return equalProcessor;
		} else if (constraintOperator == GREATER_EQUAL) {
			return greaterEqualProcessor;
		} else {
			throw new IllegalArgumentException("Invalid operator.");
		}
	}

}