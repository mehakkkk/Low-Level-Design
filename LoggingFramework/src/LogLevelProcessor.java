public abstract class LogLevelProcessor {
    private ILogTarget logTarget;
    private LogLevelProcessor nextlogLevelProcessor;

    public LogLevelProcessor(ILogTarget logTarget,LogLevelProcessor nextlogLevelProcessor) {
        this.logTarget = logTarget;
        this.nextlogLevelProcessor = nextlogLevelProcessor;
    }

    protected abstract boolean canLog(Level logLevel);

    public void writeLog(Level logLevel,String message){
        if (canLog(logLevel)) {
            {
                System.out.println("loggedBy:: "+this.getClass());
                logTarget.writeLog(new Message(logLevel,message));
            }
        } else if (nextlogLevelProcessor != null) {
            nextlogLevelProcessor.writeLog(logLevel, message);
        }
    }
}
