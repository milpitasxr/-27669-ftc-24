package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

public class Hanumankind extends LinearOpMode {

    @Override
    public void runOpMode(){
        SampleMecanumDrive dt = new SampleMecanumDrive(hardwareMap);
        waitForStart();

        Trajectory goToSpecimen = dt.trajectoryBuilder(new Pose2d(0,0,0))
                .lineTo(new Vector2d(0,-33))
                .build();
        Trajectory goToScoringZone = dt.trajectoryBuilder(new Pose2d())
                .lineTo(new Vector2d(-0.4, -34))
                .splineToConstantHeading(new Vector2d(-48, -40), Math.toRadians(90))
                .build();
        Trajectory goToBucket = dt.trajectoryBuilder(new Pose2d())
                .lineToLinearHeading(new Pose2d(-52,-52, Math.toRadians(45)))
                .build();
        Trajectory goToSecondSample = dt.trajectoryBuilder(new Pose2d())
                .lineToLinearHeading(new Pose2d(-58,-40, Math.toRadians(90)))
                .build();
        Trajectory goToThirdSample = dt.trajectoryBuilder(new Pose2d())
                .lineToLinearHeading(new Pose2d(-58,-40, Math.toRadians(130)))
                .build();

        waitForStart();
        //STILL NEED TO ADD THE TELEOP MARKER ACTIONS HERE, but for now its fine, lets first finish teleop lol
        dt.followTrajectory(goToSpecimen);
        dt.followTrajectory(goToScoringZone);
        dt.followTrajectory(goToBucket);

        dt.followTrajectory(goToSecondSample);
        dt.followTrajectory(goToBucket);

        dt.followTrajectory(goToThirdSample);
        dt.followTrajectory(goToBucket);


    }
}
