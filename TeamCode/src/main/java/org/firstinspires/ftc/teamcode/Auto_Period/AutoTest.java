package org.firstinspires.ftc.teamcode.Auto_Period;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.MecanumDrive;
@Autonomous(name="AutoTest")
public class AutoTest extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException {
        //myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-61, 0, 0))
        //                .splineToLinearHeading(new Pose2d(-35, -60 , Math.toRadians(90)), Math.toRadians(-90))
        //                .build());
        Pose2d beginPose = new Pose2d(61, -12, Math.toRadians(180));
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        waitForStart();
        Actions.runBlocking(
                drive.actionBuilder(beginPose)
                        .strafeTo(new Vector2d(40,-12))
                        //launch()
                        .waitSeconds(2)
                        .setTangent(Math.toRadians(-90))
                        //.turn(Math.toRadians(90))
                        .splineToSplineHeading(new Pose2d(0,-45,Math.toRadians(90)),Math.toRadians(180))
                        .setTangent(Math.toRadians(180))
                        .splineToConstantHeading(new Vector2d(-24,-55),Math.toRadians(180))
                        .build()
        );
    }
}
