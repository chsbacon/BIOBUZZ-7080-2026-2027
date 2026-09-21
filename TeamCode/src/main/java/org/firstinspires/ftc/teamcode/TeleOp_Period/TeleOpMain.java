package org.firstinspires.ftc.teamcode.TeleOp_Period;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
@TeleOp(name="TeleOpMain")
public class TeleOpMain extends LinearOpMode{
    private MecanumDrive drive;
    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new MecanumDrive(hardwareMap,new Pose2d(0, 0, 0));
        waitForStart();
        runtime.reset();
        while(opModeIsActive()){
            drive.lazyImu();
        }
    }
}
