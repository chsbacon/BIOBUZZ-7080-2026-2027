package org.firstinspires.ftc.teamcode.Auto_Period;
import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ftc.Actions;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

class launch {
    DcMotorEx launchMotor; // launchMotor is a motor

    public launch(HardwareMap hardwareMap) { //launch exists
        launchMotor = hardwareMap.get(DcMotorEx.class,"launchMotor"); //launchMotor is used in launch
    }

    public class spinUp implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            if(!initialized){
                launchMotor.setPower(1);
                initialized = true;
            }

            double vel = launchMotor.getVelocity();
            packet.put("launchMotorVelocity",vel);
            return vel < 10_000.0;
        }
    }

    public Action launchStart(){
        return new spinUp();
    }
}
public class launchParkAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        launch launch1 = new launch(hardwareMap); //make a new launch function and use the launch motor for this one

        waitForStart();

        Actions.runBlocking(launch1.launchStart());
    }

}
