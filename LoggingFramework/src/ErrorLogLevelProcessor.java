public class ErrorLogLevelProcessor extends LogLevelProcessor {
    public ErrorLogLevelProcessor(ILogTarget logTarget) {
        super(logTarget, null); // No next processor
    }

    @Override
    protected boolean canLog(Level logLevel) {
        return logLevel == Level.ERROR;
    }
}
