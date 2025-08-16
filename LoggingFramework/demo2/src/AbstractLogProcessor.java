public abstract class AbstractLogProcessor {

    private AbstractLogProcessor nextLogProcessor;
    private Logger logger;
    private ILogTarget logTarget;

    protected AbstractLogProcessor(Logger logger) {
        this.logger = logger;
        this.logTarget = logger.getLogTarget();  // Assuming Logger has a method to get log target
    }

    public void setNextLogProcessor(AbstractLogProcessor nextLogProcessor)
    {
        this.nextLogProcessor = nextLogProcessor;
    }

    public abstract boolean canLog(Level level);
    public void log(Level level, Message message) {
        if(canLog(level))
        {
            logTarget.appendMessage(message);
        }
        else
            nextLogProcessor.log(level,message);

    }
}
