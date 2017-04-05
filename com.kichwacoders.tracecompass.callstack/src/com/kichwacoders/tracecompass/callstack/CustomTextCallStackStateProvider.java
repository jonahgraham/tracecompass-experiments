package com.kichwacoders.tracecompass.callstack;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.tracecompass.statesystem.core.statevalue.ITmfStateValue;
import org.eclipse.tracecompass.statesystem.core.statevalue.TmfStateValue;
import org.eclipse.tracecompass.tmf.core.callstack.CallStackStateProvider;
import org.eclipse.tracecompass.tmf.core.event.ITmfEvent;
import org.eclipse.tracecompass.tmf.core.event.ITmfEventField;
import org.eclipse.tracecompass.tmf.core.trace.ITmfTrace;

@NonNullByDefault
public class CustomTextCallStackStateProvider extends CallStackStateProvider {

	private static final int VERSION = 1;

	public CustomTextCallStackStateProvider(ITmfTrace trace) {
		super(trace);
	}

	@Override
	public int getVersion() {
		return VERSION;
	}

	@Override
	public CallStackStateProvider getNewInstance() {
		return new CustomTextCallStackStateProvider(getTrace());
	}

	@Override
	protected boolean considerEvent(ITmfEvent event) {
		return true;
	}

	@Override
	protected @Nullable ITmfStateValue functionEntry(ITmfEvent event) {
		if ("enter".equals(event.getName())) {
			ITmfEventField func = event.getContent();
			ITmfEventField field = func.getField("Message");
			Object value = field.getValue();
			return TmfStateValue.newValueString(value.toString());
		}
		return null;
	}

	@Override
	protected @Nullable ITmfStateValue functionExit(ITmfEvent event) {
		if ("exit".equals(event.getName())) {
			ITmfEventField func = event.getContent();
			ITmfEventField field = func.getField("Message");
			Object value = field.getValue();
			return TmfStateValue.newValueString(value.toString());
		}
		return null;
	}

	@Override
	protected int getProcessId(ITmfEvent event) {
        return UNKNOWN_PID;
	}

	@Override
	protected long getThreadId(ITmfEvent event) {
        return 1;
	}

}
