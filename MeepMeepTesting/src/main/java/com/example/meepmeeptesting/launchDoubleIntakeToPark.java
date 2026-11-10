package com.example.meepmeeptesting;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class launchDoubleIntakeToPark {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 18)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(61, -12, Math.toRadians(180)))
                .strafeTo(new Vector2d(40,-12))
                //launch()
                .waitSeconds(1)
                .turnTo(Math.toRadians(-90))
                .splineToConstantHeading(new Vector2d(61,-50), Math.toRadians(-90))
                .waitSeconds(1)
                .lineToY(-60)
                .setTangent(Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(24,-60), Math.toRadians(270))
                .waitSeconds(1)
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-22,-56, Math.toRadians(90)), Math.toRadians(270))
                .build());
        Image customImg = null;
        try {
            customImg = ImageIO.read(new File("FIELD_BIOBUZZ_OFFICAL.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        meepMeep.setBackground(customImg)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}