public class LoggerDemo {
    public static void main(String[] args) {
        ILogTarget logTarget = new ConsoleLogTarget();
        LogLevelProcessor logLevelProcessor = new DebugLogLevelProcessor(logTarget);
        logLevelProcessor.writeLog(Level.DEBUG,"This is my debug log");
        logLevelProcessor.writeLog(Level.WARN,"This is my warn log");
        logLevelProcessor.writeLog(Level.ERROR,"This is my error log");
    }
}
