public class ErrorLogProcessor extends AbstractLogProcessor{
    protected ErrorLogProcessor(Logger logger) {
        super(logger);
    }

    @Override
    public boolean canLog(Level level) {
        return level==Level.ERROR;
    }
}
