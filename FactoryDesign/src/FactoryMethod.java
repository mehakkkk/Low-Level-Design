public class FactoryMethod {
    public static void main(String[] args) {
        //1: single factory method for all creation
        Point p1 = Point.getPoint(10,20,CoordinateSystem.POLAR);

        //2: using different factory method for different creation types
        Point p2 = Point.newCartesianPoint(2,4);

        //3: creating a factory for creation
        Point p3 = Point.PointFactory.newPolarPoint(4,5);
    }
}


enum CoordinateSystem{
    CARTESIAN,POLAR;
}

class Point{
    double x,y;

    private Point(double x,double y)
    {
        this.x = x;
        this.y = y;
    }

    //way 1: to create factory method, with a single factory method
    public static Point getPoint(double x,double y,CoordinateSystem cs)
    {
        switch (cs)
        {
            case CARTESIAN: return newCartesianPoint(x,y);
            case POLAR:return newPolarPoint(x,y);
        }

        return null;
    }

    //way 2: using different static method for each creation type
    public static Point newCartesianPoint(double x, double y)
    {
        return new Point(x,y);
    }

    public static Point newPolarPoint(double rho, double theta)
    {
        return new Point(rho*Math.cos(theta),rho*Math.sin(theta));
    }

    //way 2: creating factory for creation
    public static class PointFactory{

        public static Point newCartesianPoint(double x, double y)
        {
            return new Point(x,y);
        }

        public static Point newPolarPoint(double rho, double theta)
        {
            return new Point(rho*Math.cos(theta),rho*Math.sin(theta));
        }
    }
}