public class Demo {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance(new ConsoleTarget());

        Thread t1 = new Thread(()->{
            logger.log(Level.DEBUG,new Message(Level.DEBUG,"This is my debug log "+Thread.currentThread().getName()));
            logger.log(Level.WARN,new Message(Level.WARN,"This is my warn log"+Thread.currentThread().getName()));
            logger.log(Level.ERROR,new Message(Level.ERROR,"This is my error log"+Thread.currentThread().getName()));

        });

        Thread t2 = new Thread(()->{
            logger.log(Level.DEBUG,new Message(Level.DEBUG,"This is my debug log "+Thread.currentThread().getName()));
            logger.log(Level.WARN,new Message(Level.WARN,"This is my warn log"+Thread.currentThread().getName()));
            logger.log(Level.ERROR,new Message(Level.ERROR,"This is my error log"+Thread.currentThread().getName()));

        });

        t1.start();
        t2.start();
    }
}
