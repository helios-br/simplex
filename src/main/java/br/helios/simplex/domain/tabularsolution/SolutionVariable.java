package br.helios.simplex.domain.tabularsolution;

import static br.helios.simplex.infrastructure.util.StringUtil.toShortString;
import static java.lang.String.format;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import br.helios.simplex.domain.problem.variable.Variable;

public class SolutionVariable {

	public static final int NON_BASIC_LINE = -1;
	private static final BigDecimal NON_BASIC_VALUE = ZERO;

	public final Variable variable;
	public final int index;
	public final int tableLine;
	public final boolean isBasic;

	private SolutionVariable(Variable variable, int tableLine, boolean isBasic, int index) {
		this.variable = variable;
		this.index = index;

		if (isBasic && tableLine <= 0) {
			throw new IllegalArgumentException("Basic variables line not informed");
		}

		this.tableLine = tableLine;
		this.isBasic = isBasic;
	}

	public static SolutionVariable createBasicVariable(Variable variable, int tableLine) {
		return new SolutionVariable(variable, tableLine, true, variable.id() - 1);
	}

	public static SolutionVariable createNonBasicVariable(Variable variable) {
		return new SolutionVariable(variable, NON_BASIC_LINE, false, variable.id() - 1);
	}

	public static SolutionVariable createVariable(SolutionVariable solutionVariable, int newIndex) {
		return new SolutionVariable(solutionVariable.variable, solutionVariable.tableLine, solutionVariable.isBasic, newIndex);
	}

	public String name() {
		return variable.name;
	}

	public BigDecimal value(BigDecimal[][] simplexTable) {
		if (isBasic) {
			return simplexTable[tableLine][simplexTable[tableLine].length - 1];
		}
		return NON_BASIC_VALUE;
	}

	@Override
	public String toString() {
		return format("%s: (index: %d, tableLine: %d, isBasic: %s)", variable.toString(), index, tableLine, toShortString(isBasic));
	}
}
