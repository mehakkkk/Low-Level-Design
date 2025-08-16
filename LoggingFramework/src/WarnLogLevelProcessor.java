public class WarnLogLevelProcessor extends LogLevelProcessor {
    public WarnLogLevelProcessor(ILogTarget logTarget) {
        super(logTarget, new ErrorLogLevelProcessor(logTarget));
    }

    @Override
    protected boolean canLog(Level logLevel) {
        return logLevel == Level.WARN;
    }
}

