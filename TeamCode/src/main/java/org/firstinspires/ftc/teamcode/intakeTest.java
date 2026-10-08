package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "intakeTest")
public class intakeTest extends LinearOpMode {

    public void runOpMode() throws InterruptedException{

        double intakePower = 0.0;

        boolean intakeDebounce = false;

        DcMotorEx intakeMotor;

        intakeMotor = hardwareMap.get(DcMotorEx.class, "intakeMotor");

        waitForStart();

        while(opModeIsActive()){

            boolean faceButtonY = gamepad1.y;
            boolean faceButtonA = gamepad1.a;

            intakeMotor.setPower(-intakePower);

            if (!faceButtonY && !faceButtonA){
                intakeDebounce = false;
            }

            if (intakePower < 1 && faceButtonY && !intakeDebounce){
                intakePower = intakePower + 0.1;
                intakeDebounce = true;
            }

            if(intakePower > 0 && faceButtonA && !intakeDebounce){
                intakePower = intakePower - 0.1;
                intakeDebounce = true;
            }



        }

    }

}
