public class ChainLoggerFactory {
    public static AbstractLogProcessor getLogger(Logger logger) {
        AbstractLogProcessor errorProcessor = new ErrorLogProcessor(logger);
        AbstractLogProcessor warnProcessor = new WarnLogProcessor(logger);
        AbstractLogProcessor debugProcessor = new DebugLogProcessor(logger);
        warnProcessor.setNextLogProcessor(debugProcessor);
        errorProcessor.setNextLogProcessor(warnProcessor);
        return errorProcessor;

    }
}
