package org.firstinspires.ftc.teamcode.TeleOp_Period;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;

import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.ftc.LazyImu;
import com.acmerobotics.roadrunner.ftc.PinpointIMU;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

import org.firstinspires.ftc.teamcode.Drawing;
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
        int modeSwitch=0;
        boolean headingLock = false;
        boolean prevCircle = false;
        waitForStart();
        runtime.reset();
        double targetHeading = 0;
        while(opModeIsActive()) {
            double pCoff = MecanumDrive.PARAMS.pCoff;
            drive.updatePoseEstimate();
            Pose2d pose = drive.localizer.getPose();
            double angle = Math.toDegrees(pose.heading.toDouble());
            if(DpadChecker()) {
                headingLock = true;
                targetHeading = DpadAngle(anglesToCardnal(angle));
            }
            if (!headingLock) {
                targetHeading = anglesToCardnal(angle);
            }
            double error= targetHeading - angle;
            if(Math.abs(error)<0.5){//At the request of alston, this has been changed to 0.5 degrees
                headingLock = false;
            }
            double rotPower;
            if(gamepad1.circle && !prevCircle){
                if(modeSwitch==0){
                    modeSwitch=1;
                }else {
                    modeSwitch = 0;
                }
            }
            prevCircle = gamepad1.circle;
            if(modeSwitch==0){
                rotPower=error*pCoff;
            } else{
                rotPower=-gamepad1.right_stick_x;
            }
            drive.setDrivePowers(new PoseVelocity2d(
                    new Vector2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x
                    ),
                    rotPower
                ));

            telemetry.addData("x", pose.position.x);
            telemetry.addData("y", pose.position.y);
            telemetry.addData("heading (deg)", Math.toDegrees(pose.heading.toDouble()));
            telemetry.addData("ortCard",anglesToCardnal(Math.toDegrees(pose.heading.toDouble())));
            telemetry.addData("error",error);
            telemetry.addData("mode",modeSwitch);
            telemetry.update();

            TelemetryPacket packet = new TelemetryPacket();
            packet.fieldOverlay().setStroke("#3F51B5");
            Drawing.drawRobot(packet.fieldOverlay(), pose);
            FtcDashboard.getInstance().sendTelemetryPacket(packet);
        }
    }
    //returns the clostest
    public double anglesToCardnal(double angle){
        if(angle >= -45 && angle <= 45){
            return 0;
        } else if (angle >= -135 && angle <= 135){
            return 90*Math.signum(angle);
        } else {
            return 180;
        }
    }
    public double DpadAngle (double theOtherOption){
        if(gamepad1.dpad_down) {
            return 180 + 0.0001;
        }else if(gamepad1.dpad_up){
            return 0;
        }else if(gamepad1.dpad_right) {
            return -90;
        }else if(gamepad1.dpad_left){
            return 90;
        } else {
            return theOtherOption;
        }
    }
    public boolean DpadChecker (){
        if(gamepad1.dpad_down) {
            return true;
        }else if(gamepad1.dpad_up){
            return true;
        }else if(gamepad1.dpad_right) {
            return true;
        }else if(gamepad1.dpad_left){
            return true;
        } else{
            return false;
        }
    }
}
