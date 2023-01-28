package frc.robot.lib.math;

import frc.robot.Constants;

public class Units {
    // Converts from native units into degrees
    public static double NUToDeg(double angle){
        //Account for gear ratio
        angle /= Constants.Swerve.TURN_GEAR_RATIO;

        //Convert from native units into rotations;
        angle /= 2048;

        //Convert to degrees
        angle *= 360;

        return angle;
    }

    // Converts from degrees to native units
    public static double degToNU(double angle){
        //Convert from degrees into rotation
        angle /= 360;

        //Convert from rotations into Native Units
        angle *= 2048;

        //Account for gear ratio
        angle *= Constants.Swerve.TURN_GEAR_RATIO;

        return angle;
    }

    // Constrains the given angle to 180 DEGREES 
    public static double constrainDeg(double angle){
        angle %= 360;

        if(Math.abs(angle) <= 180){
            return angle;
        }

        if(angle > 0){
            return angle - 360;
        }
        else{
            return angle + 360;
        }
    }

    public static double constrainRad(double angle) {
        return constrainDeg(angle * 360 / Constants.TAU) * Constants.TAU / 360;
    }

    //Convert from Native Units to Radians
    public static double NUToRad(double angle){
        //Account for gear ratio
        angle /= Constants.Swerve.TURN_GEAR_RATIO;

        //Convert from native units into rotations;
        angle /= 2048;

        //Convert from rotations into radians
        angle *= 2*Math.PI;

        return angle;
    }

    //Convert from NU/100ms to Meters per Second
    public static double NUToMPS(double speed){
        //Convert from NU/100ms to NU/s
        speed *= 10;

        //Convert from NU/s to Rotations/s
        speed /= 2048;

        speed /= Constants.Swerve.MOVE_GEAR_RATIO;

        //Convert from Rotations/s to Meters/s
        speed *= Constants.TAU*Constants.Swerve.WHEELRADIUS;
        return speed;
    }

    public static double NUToM(double NU) {
        NU /= 2048;

        NU /= Constants.Swerve.MOVE_GEAR_RATIO;

        NU *= Constants.TAU*Constants.Swerve.WHEELRADIUS;
        return NU;
    }

    //Converts to NU/100ms then to 
    public static double toPercentOutput(double mps){
        //Convert to rotations per second
        double speed = mps/(Constants.Swerve.WHEELRADIUS*Constants.TAU);
        //Convert to NU per second
        speed *= 2048;
        speed *= Constants.Swerve.MOVE_GEAR_RATIO;
        //Convert to NU/100ms
        speed /= 10;

        return speed/Constants.Swerve.MAX_NATIVE_VELOCITY;
    }

    public static double radToNUArm(double angle){
        angle /= Constants.TAU;

        angle *= 2048;

        angle *= Constants.Arm.GEARRATIO;

        return angle;
    }
}
