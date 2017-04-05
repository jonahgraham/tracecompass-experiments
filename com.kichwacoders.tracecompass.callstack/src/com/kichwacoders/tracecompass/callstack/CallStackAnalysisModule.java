package com.kichwacoders.tracecompass.callstack;

import java.util.Collections;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.tracecompass.tmf.core.analysis.requirements.TmfAbstractAnalysisRequirement;
import org.eclipse.tracecompass.tmf.core.callstack.CallStackAnalysis;
import org.eclipse.tracecompass.tmf.core.exceptions.TmfAnalysisException;
import org.eclipse.tracecompass.tmf.core.parsers.custom.CustomTxtTrace;
import org.eclipse.tracecompass.tmf.core.statesystem.ITmfStateProvider;
import org.eclipse.tracecompass.tmf.core.trace.ITmfTrace;

public class CallStackAnalysisModule extends CallStackAnalysis {
	/**
	 * ID
	 */
	public static final String ID = "com.kichwacoders.tracecompass.callstack.module1"; //$NON-NLS-1$

	private @Nullable Set<@NonNull TmfAbstractAnalysisRequirement> fAnalysisRequirements = null;

	@Override
	public boolean setTrace(ITmfTrace trace) throws TmfAnalysisException {
		if (!(trace instanceof CustomTxtTrace)) {
			return false;
		}
		return super.setTrace(trace);
	}

	@Override
	protected CustomTxtTrace getTrace() {
		return (CustomTxtTrace) super.getTrace();
	}

	@Override
	protected ITmfStateProvider createStateProvider() {
		return new CustomTextCallStackStateProvider(getTrace());
	}

	@Override
	public @NonNull Iterable<@NonNull TmfAbstractAnalysisRequirement> getAnalysisRequirements() {
		// XXX: This should be the classes that verify if trace has ability to
		// actually do call stack analysis
		return Collections.emptySet();
	}
}
