package org.firstinspires.ftc.teamcode.Auto_Period;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@Autonomous(name="intakeAuto")
public class intakeAuto extends LinearOpMode {
    public void runOpMode () throws InterruptedException{

        Pose2d beginPose = new Pose2d(61, -12, Math.toRadians(180));
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        DcMotorEx intakeMotor = hardwareMap.get(DcMotorEx.class, "intakeMotor");
        waitForStart();
        Actions.runBlocking(
                drive.actionBuilder(beginPose)
                        .strafeToLinearHeading(new Vector2d(56, -12), Math.toRadians(-90))
                        .build()
        );
        intakeMotor.setPower(1);
        Actions.runBlocking(
                drive.actionBuilder(new Pose2d (61,-12,Math.toRadians(-90)))
                        .setTangent(Math.toRadians(-90))
                        .splineToConstantHeading(new Vector2d(61, -61), Math.toRadians(-90))
                        .build()
        );
        intakeMotor.setPower(0);
    }
}