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
        private boolean initialized = false; //hasnt been spun up yet

        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            if(!initialized){ //if it hasnt been spun up yet
                launchMotor.setPower(1); //spin it up
                initialized = true; //say its been spun up
            }

            double vel = launchMotor.getVelocity(); //how fast is it spinning
            packet.put("launchMotorVelocity",vel); //tell us how fast its spinning
            return vel < 10_000.0; //if velocity > 10_000.0 somethings per i dont know then just say it isnt
        }
    }

    public Action motorStart(){
        return new spinUp(); //runs the spinUp class
    }
}
public class launchParkAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        launch launch1 = new launch(hardwareMap); //launch1 uses launchMotor

        waitForStart();

        Actions.runBlocking(launch1.motorStart()); //spins launchMotor
    }

}
