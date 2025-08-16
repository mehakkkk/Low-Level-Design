public class DebugLogProcessor extends AbstractLogProcessor{
    protected DebugLogProcessor(Logger logger) {
        super(logger);
    }

    @Override
    public boolean canLog(Level level) {
        return level==Level.DEBUG;
    }
}
