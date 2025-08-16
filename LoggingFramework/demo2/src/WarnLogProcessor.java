public class WarnLogProcessor extends AbstractLogProcessor{
    protected WarnLogProcessor(Logger logger) {
        super(logger);
    }

    @Override
    public boolean canLog(Level level) {
        return level==Level.WARN;
    }
}
