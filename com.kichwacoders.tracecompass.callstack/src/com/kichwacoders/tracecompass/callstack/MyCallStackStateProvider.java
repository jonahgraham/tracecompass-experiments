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
public class MyCallStackStateProvider extends CallStackStateProvider {

	private static final int VERSION = 1;

	public MyCallStackStateProvider(ITmfTrace trace) {
		super(trace);
	}

	@Override
	public int getVersion() {
		return VERSION;
	}

	@Override
	public CallStackStateProvider getNewInstance() {
		return new MyCallStackStateProvider(getTrace());
	}

	@Override
	protected boolean considerEvent(ITmfEvent event) {
		System.out.println("considerEvent:" + event);
		return true;
	}

	@Override
	protected @Nullable ITmfStateValue functionEntry(ITmfEvent event) {
		if ("enter".equals(event.getType().getName())) {
			ITmfEventField func = event.getContent();
			ITmfEventField field = func.getField("Message");
			Object value = field.getValue();
			field.toString();
			func.toString();
			return TmfStateValue.newValueString(value.toString());
		}
		return null;
	}

	@Override
	protected @Nullable ITmfStateValue functionExit(ITmfEvent event) {
		if ("exit".equals(event.getType().getName())) {
			ITmfEventField func = event.getContent();
			ITmfEventField field = func.getField("Message");
			Object value = field.getValue();
			field.toString();
			func.toString();
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
