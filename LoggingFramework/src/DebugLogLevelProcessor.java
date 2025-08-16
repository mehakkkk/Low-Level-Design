public class DebugLogLevelProcessor extends LogLevelProcessor {
    public DebugLogLevelProcessor(ILogTarget logTarget) {
        super(logTarget, new WarnLogLevelProcessor(logTarget));
    }

    @Override
    protected boolean canLog(Level logLevel) {
        return logLevel == Level.DEBUG;
    }
}

