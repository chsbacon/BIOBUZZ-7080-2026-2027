package org.firstinspires.ftc.teamcode;


import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "TeleOp_Test")
public class TeleOp_Test extends LinearOpMode {



    @Override
    public void runOpMode() throws InterruptedException {

        DcMotorEx leftFront, leftRear, rightRear, rightFront;

        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");

        leftRear.setDirection(REVERSE);
        leftFront.setDirection(REVERSE);
        rightFront.setDirection(FORWARD);
        rightRear.setDirection(FORWARD);

        leftRear.setZeroPowerBehavior(BRAKE);
        leftFront.setZeroPowerBehavior(BRAKE);
        rightFront.setZeroPowerBehavior(BRAKE);
        rightRear.setZeroPowerBehavior(BRAKE);

        waitForStart();

        while(opModeIsActive()){



            double max;

            double xInput = gamepad1.left_stick_x;
            double yInput = gamepad1.left_stick_y;
            double rotationalInput = gamepad1.right_stick_x;

            double leftFrontPower, leftRearPower, rightFrontPower, rightRearPower;

            //Mecanum wheel equations
            leftFrontPower = yInput + xInput + rotationalInput;
            rightFrontPower = yInput - xInput - rotationalInput;
            leftRearPower = yInput - xInput + rotationalInput;
            rightRearPower = yInput + xInput - rotationalInput;

            max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftRearPower));
            max = Math.max(max, Math.abs(rightRearPower));
            //Limits power to 1.0 and translates other values relative to the maximum value

            if (max > 1.0) {

                leftFrontPower /= max;
                rightFrontPower /= max;
                leftRearPower /= max;
                rightRearPower /= max;

            }

            leftFront.setPower(leftFrontPower);
            leftRear.setPower(leftRearPower);
            rightFront.setPower(rightFrontPower);
            rightRear.setPower(leftFrontPower);

            telemetry.addData("xInput yInput", "%4.2f, %4.2f", xInput, yInput);

            telemetry.addData("Front Left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
            telemetry.addData("Back  Left/Right", "%4.2f, %4.2f", leftRearPower, rightRearPower);

            telemetry.update();

        }

    }

}
