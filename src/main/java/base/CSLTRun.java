package base;

import lejos.robotics.SampleProvider;
import ev3dev.sensors.BaseSensor;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

//import static base.DeliveryTruck.motorSteer;
//import static base.DeliveryTruck.motorDrive;

/**
 *  Title: CSLTRun thread
 *
 *  This is thread where all truck logic for task execution should be implemented.
 *  Use function method to do that (it can be extended with other functions).
 */

class CSLTRun extends Thread {
    private Thread t;
    private String threadName;


    CSLTRun ( String threadName) {
        this.threadName = threadName;
        System.out.println("Creating " +  this.threadName );
    }

    private boolean runMotors() {

        try {

            while (CSLTruck.isRunning && !CSLTruck.runThreadIsExecuted) {

                //DeliveryTruck.isRunning should be checked as often as possible
                //to allow stop from SCS

                //TODO: YOUR CODE HERE
                //TODO: CHECK THIS DOCUMENTATION TO UNDERSTAND HOW TO RUN THIS TRUCK
                //TODO: AND HOW TO WRITE CODE:
                //https://github.com/CONNEX-AB-Delivery-System/DS-CSLTruck/blob/master/README.md


                CSLTruck.craneLift.setSpeed(400);
                CSLTruck.craneLift.backward();
                Thread.sleep(6000);
                CSLTruck.craneLift.stop(true);

                Thread.sleep(1000);

                CSLTruck.craneGrabber.setSpeed(300);
                CSLTruck.craneGrabber.backward();
                Thread.sleep(5000);
                CSLTruck.craneGrabber.stop(true);


                CSLTruck.craneLift.setSpeed(400);
                CSLTruck.craneLift.forward();
                Thread.sleep(5000);
                CSLTruck.craneLift.stop(true);

                Thread.sleep(2000);

                CSLTruck.craneGrabber.setSpeed(300);
                CSLTruck.craneGrabber.forward();
                Thread.sleep(5000);
                CSLTruck.craneGrabber.stop(true);

                Thread.sleep(2000);

                CSLTruck.craneLift.setSpeed(400);
                CSLTruck.craneLift.backward();
                Thread.sleep(15000);
                CSLTruck.craneLift.stop(true);

                Thread.sleep(4000);

                CSLTruck.craneLift.setSpeed(400);
                CSLTruck.craneLift.forward();
                Thread.sleep(2000);
                CSLTruck.craneLift.stop(true);





                CSLTruck.motorDrive.stop(true);
                CSLTruck.motorDrive.setSpeed(300);
                CSLTruck.motorDrive.forward();
                Thread.sleep(2000);
                CSLTruck.motorDrive.stop(true);
                Thread.sleep(2000);

                CSLTruck.motorSteer.setSpeed(300);
                CSLTruck.motorSteer.rotateTo(40, true);
                Thread.sleep(2000);
                CSLTruck.motorSteer.stop(true);
                Thread.sleep(2000);





                //System.out.println("LineReader value" + CSLTruck.lineReader.getPIDValue());

                CSLTruck.runThreadIsExecuted = true;
                CSLTruck.outputCommandSCS = "FINISHED";
                System.out.println("Task Executed");



            }

        } catch (InterruptedException e) {
            System.out.println("Thread " +  this.threadName + " interrupted.");
        }

        return true;
    }

    public void run() {
        System.out.println("Running " +  this.threadName );

        this.runMotors();

        System.out.println("Thread " +  this.threadName + " exiting.");
    }

    public void start () {
        System.out.println("Starting " +  this.threadName );
        if (t == null) {
            t = new Thread (this, this.threadName);
            t.start ();
        }
    }
}