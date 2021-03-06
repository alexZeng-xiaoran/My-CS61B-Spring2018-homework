public class NBody{
    public NBody() {

    }

    public static double readRadius(String filePath){
        In in=new In(filePath);
        in.readInt();
        double radius=in.readDouble();
        return radius;
    }
    public static Planet[] readPlanets(String filePath){

        In in=new In(filePath);
        int numPlanets=in.readInt();
        in.readDouble();
        Planet[] allPlanets=new Planet[numPlanets];
        for(int i=0;i<numPlanets;i++){
            if(!in.isEmpty()){
                allPlanets[i]=new Planet();
                allPlanets[i].xxPos=in.readDouble();
                allPlanets[i].yyPos=in.readDouble();
                allPlanets[i].xxVel=in.readDouble();
                allPlanets[i].yyVel=in.readDouble();
                allPlanets[i].mass=in.readDouble();
                allPlanets[i].imgFileName=in.readString();
            }
        }
        return allPlanets;
    }

    /**
     * the method to draw the planet and the background
     */
//    public static void main(String[] args) {
//        double T=Double.valueOf(args[0]);
//        double dt=Double.valueOf(args[1]);
//        String filename=args[2];
//        double time=0;
//
//        Planet[] allPlanets=readPlanets(filename);
//        double radius=readRadius(filename);
//
//        double[] xForce;
//        double[] yForce;
//
//        StdDraw.setScale(-radius, radius);
//        StdDraw.picture(0.0,0.0,"images/starfield.jpg");
//
//        for(int i=0;i<allPlanets.length;i++){
//            allPlanets[i].draw();
//        }
//    }


    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();
        double T=Double.valueOf(args[0]);
        double dt=Double.valueOf(args[1]);
        String filePath=args[2];
        double time=0;
        Planet[] allPlanets=readPlanets(filePath);
        double radius=readRadius(filePath);
        double[] xForces=new double[allPlanets.length];
        double[] yForces=new double[allPlanets.length];
        double xForce=0;
        double yForce=0;

        while(time<=T){

            for(int i=0;i<allPlanets.length;i++){
                xForces[i]=allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForces[i]=allPlanets[i].calcNetForceExertedByY(allPlanets);
            }
            /*
            * the following commited code is to print the xForces and yForces because it can help me to debug the code
            * */

//            System.out.print("xForces:");
//            for(int i=0;i<allPlanets.length;i++){
//                System.out.print(xForces[i]+' ');
//            }
//            System.out.println();
//
//            System.out.print("yForces:");
//            for(int i=0;i<allPlanets.length;i++){
//                System.out.print(yForces[i]+' ');
//            }
//            System.out.println();

            for(int i=0;i<allPlanets.length;i++){
                allPlanets[i].update(dt,xForces[i],yForces[i]);
            }

            StdDraw.setScale(-radius, radius);
            StdDraw.picture(0.0,0.0,"images/starfield.jpg");
            for(int i=0;i<allPlanets.length;i++){
                allPlanets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time+=dt;
        }
        StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allPlanets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                    allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
        }
    }
}