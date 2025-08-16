public class Logger {
    private static Logger instance;
    private static AbstractLogProcessor _chainOfLogger;
    private ILogTarget logTarget;
    public Logger(ILogTarget logTarget)
    {
        this.logTarget = logTarget;
        _chainOfLogger = ChainLoggerFactory.getLogger(this);
    }

    public static Logger getInstance(ILogTarget logTarget) {
        if(instance == null)
        {
            synchronized (Logger.class)
            {
                if(instance == null)
                    instance = new Logger(logTarget);
            }
        }
        return instance;
    }

    public void log(Level level,Message message)
    {
        _chainOfLogger.log(level,message);
    }

    public ILogTarget getLogTarget() {
        return logTarget;
    }
}
